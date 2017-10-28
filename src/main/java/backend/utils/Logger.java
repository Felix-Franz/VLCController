package backend.utils;

import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 *
 * Logs with same or higher levels will be displayed!
 *
 * OFF
 * SEVERE (highest)
 * WARNING
 * INFO
 * CONFIG
 * FINE
 * FINER
 * FINEST (lowest)
 * ALL
 */
public class Logger {
    private static Logger instance;
    private java.util.logging.Logger log = java.util.logging.Logger.getLogger("VLCController System Logger");

    public static Logger getInstance(){
        if (instance == null) instance = new Logger();
        return instance;
    }

    private Logger(){
        log.setLevel(Level.CONFIG);
    }

    /**
     * sets log level, which messages will be logged
     *
     * @param level
     */
    public void setLevel(Level level){
        log.setLevel(level);
    }

    /**
     * creates a log
     * @param level log level
     * @param message log message
     */
    public void log(Level level, String message){
        log.log(level, message);
    }

}
