package io.github.akuniutka.wrappers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

public class LogWrapper extends ListAppender<ILoggingEvent> {
    public LogWrapper grabLog(Class<?> clazz, Runnable runnable) {
        if (clazz == null && runnable == null) {
            throw new IllegalArgumentException("The object type and the method reference cannot be null.");
        } else if (clazz == null) {
            throw new IllegalArgumentException("The object type cannot be null.");
        } else if (runnable == null) {
            throw new IllegalArgumentException("The method reference cannot be null.");
        }
        list.clear();
        start();
        Logger logger = (Logger) LoggerFactory.getLogger(clazz);
        logger.addAppender(this);
        try {
            runnable.run();
        } finally {
            logger.detachAppender(this);
            stop();
        }
        return this;
    }

    public LogWrapper sizeEquals(int expected) {
        int actual = list.size();
        if (actual != expected) {
            throw new AssertionError("expected: " + expected + " But was: " + actual);
        }
        return this;
    }

    public LogWrapper entryEquals(int index, Level expectedLevel, String expectedMessage) {
        ILoggingEvent entry = list.get(index);
        Level actualLevel = entry.getLevel();
        String actualMessage = entry.getFormattedMessage();
        if (!actualLevel.equals(expectedLevel) && !actualMessage.equals(expectedMessage)) {
            throw new AssertionError("expected: " + expectedLevel + " " + expectedMessage +
                    " But was: " + actualLevel + " " + actualMessage
            );
        } else if (!actualLevel.equals(expectedLevel)) {
            throw new AssertionError("expected: " + expectedLevel + " But was: " + actualLevel);
        } else if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("expected: " + expectedMessage + " But was: " + actualMessage);
        }
        return this;
    }
}
