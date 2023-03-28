package tmy.utils;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerUtility {
    private static final Logger LOGGER = Logger.getLogger(LoggerUtility.class.getName());

    static {
        Logger mainLogger = Logger.getLogger("tmy");
        mainLogger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord logRecord) {
                String formattedMessage = formatMessage(logRecord);
                return String.format(FORMAT, new Date(logRecord.getMillis()), logRecord.getLevel(),
                        formattedMessage);
            }
        });

        mainLogger.addHandler(handler);
    }

    private LoggerUtility() {
    }

    public static void info(String message, Object... args) {
        LOGGER.log(Level.INFO, message, args);
    }

    public static void warning(String message, Object... args) {
        LOGGER.log(Level.WARNING, message, args);
    }

    public static void severe(String message, Object... args) {
        LOGGER.log(Level.SEVERE, message, args);
    }
}