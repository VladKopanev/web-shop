package ua.nure.kopaniev.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    private static final String DEFAULT_LOG_DIR = System.getProperty("user.dir") + "/logs";

    public static final String LOG_DIR_SYSTEM_PROPERTY_NAME = "osm.log.dir";

    public static final String LOG_DIR_PROPERTY = "LOG_DIR";

    private boolean started = false;

    @Override
    public void start() {
        if (started) {
            return;
        }

        String logDirectory = System.getProperty(LOG_DIR_SYSTEM_PROPERTY_NAME);

        logDirectory = (logDirectory != null && logDirectory.length() > 0) ? logDirectory : DEFAULT_LOG_DIR;

        System.out.println("Logback log directory is set to " + logDirectory);

        Context context = getContext();
        context.putProperty(LOG_DIR_PROPERTY, logDirectory);

        started = true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {

    }

    @Override
    public void onReset(LoggerContext loggerContext) {

    }

    @Override
    public void onStop(LoggerContext loggerContext) {

    }

    @Override
    public void onLevelChange(Logger logger, Level level) {

    }
}