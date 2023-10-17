package io.github.akuniutka.wrappers;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Log Wrapper")
class LogWrapperTest {
    private static class Stub {
        private static final Logger LOGGER = LoggerFactory.getLogger(Stub.class);

        void run() {
            LOGGER.debug("Debug message");
            LOGGER.warn("Warn message");
            LOGGER.error("Error message");
        }
    }

    @Test
    @DisplayName("Fail to grab log when the object type and the method reference are null")
    void testGrabLogWhenBothClazzAndRunnableAreNull() {
        LogWrapper wrapper = new LogWrapper();
        Exception e = assertThrows(IllegalArgumentException.class, () -> wrapper.grabLog(null, null));
        assertEquals("The object type and the method reference cannot be null.", e.getMessage());
    }

    @Test
    @DisplayName("Fail to grab log when the object type is null")
    void testGrabLogWhenClazzIsNull() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        Exception e = assertThrows(IllegalArgumentException.class, () -> wrapper.grabLog(null, stub::run));
        assertEquals("The object type cannot be null.", e.getMessage());
    }

    @Test
    @DisplayName("Fail to grab log when the method reference is null")
    void testGrabLogWhenRunnableIsNull() {
        LogWrapper wrapper = new LogWrapper();
        Exception e = assertThrows(IllegalArgumentException.class, () -> wrapper.grabLog(Stub.class, null));
        assertEquals("The method reference cannot be null.", e.getMessage());
    }

    @Test
    @DisplayName("Fail to grab log when the method reference is null")
    void testGrabLogWhenNeitherClazzNorRunnableIsNull() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        assertSame(wrapper, wrapper.grabLog(Stub.class, stub::run));
    }

    @Test
    @DisplayName("Find that the size differs from the expected")
    void testSizeEqualsWhenActualSizeDoesNotEqualExpected() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        try {
            wrapper.grabLog(Stub.class, stub::run).sizeEquals(2);
        } catch (AssertionError e) {
            assertEquals("expected: 2 But was: 3", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find that the size equals expected")
    void testSizeEqualsWhenActualSizeEqualsExpected() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        wrapper.grabLog(Stub.class, stub::run);
        assertSame(wrapper, wrapper.grabLog(Stub.class, stub::run).sizeEquals(3));
    }

    @Test
    @DisplayName("Find that the level and the message differ from expected")
    void testEntryEqualsWhenBothLevelAndMessageDifferFromExpected() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        int index = 1;
        Level expectedLevel = Level.DEBUG;
        String expectedMessage = "Debug message";
        try {
            wrapper.grabLog(Stub.class, stub::run).entryEquals(index, expectedLevel, expectedMessage);
        } catch (AssertionError e) {
            assertEquals("expected: DEBUG Debug message But was: WARN Warn message", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find that the level differs from expected")
    void testEntryEqualsWhenLevelDiffersFromExpectedAndMessageEqualsExpected() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        int index = 1;
        Level expectedLevel = Level.DEBUG;
        String expectedMessage = "Warn message";
        try {
            wrapper.grabLog(Stub.class, stub::run).entryEquals(index, expectedLevel, expectedMessage);
        } catch (AssertionError e) {
            assertEquals("expected: DEBUG But was: WARN", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find that the message differs from expected")
    void testEntryEqualsWhenLevelEqualsExpectedAndMessageDiffersFromExpected() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        int index = 1;
        Level expectedLevel = Level.WARN;
        String expectedMessage = "Debug message";
        try {
            wrapper.grabLog(Stub.class, stub::run).entryEquals(index, expectedLevel, expectedMessage);
        } catch (AssertionError e) {
            assertEquals("expected: Debug message But was: Warn message", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find that the level and the message equal expected")
    void testEntryEqualsWhenBothLevelAndMessageEqualExpected() {
        LogWrapper wrapper = new LogWrapper();
        Stub stub = new Stub();
        int index = 1;
        Level expectedLevel = Level.WARN;
        String expectedMessage = "Warn message";
        assertSame(wrapper, wrapper.grabLog(Stub.class, stub::run).entryEquals(index, expectedLevel, expectedMessage));
    }
}