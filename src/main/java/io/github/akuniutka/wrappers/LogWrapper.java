package io.github.akuniutka.wrappers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

public class LogWrapper extends ListAppender<ILoggingEvent> {
    private Logger logger;

    public void attach(Class<?> clazz) {
        list.clear();
        start();
        logger = (Logger) LoggerFactory.getLogger(clazz);
        logger.addAppender(this);
    }

    public void detach() {
        logger.detachAppender(this);
        stop();
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String getMessage(int i) {
        return list.get(i).getFormattedMessage();
    }

    public Level getLevel(int i) {
        return list.get(i).getLevel();
    }
}
