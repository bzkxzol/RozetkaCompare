package helpers;

import org.apache.logging.log4j.LogManager;

public class Logger {

    /**
     * Messages are printed in STDOUT
     */
    public static final String CONSOLE = "console";

    public static void info(String category, Object message) {
        getLogger(category).info(message);
    }
    private static org.apache.logging.log4j.core.Logger getLogger(String name) {
        return (org.apache.logging.log4j.core.Logger) LogManager.getLogger(name);
    }

}

