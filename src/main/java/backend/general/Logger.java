package backend.general;

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

    public void setLevel(Level level){
        log.setLevel(level);
    }

    public void setLevel(String level){
        switch (level){
            case "OFF":
                setLevel(Level.OFF);
                break;
            case "SEVERE":
                setLevel(Level.SEVERE);
                break;
            case "WARNING":
                setLevel(Level.WARNING);
                break;
            case "INFO":
                setLevel(Level.INFO);
                break;
            case "CONFIG":
                setLevel(Level.CONFIG);
                break;
            case "FINE":
                setLevel(Level.FINE);
                break;
            case "FINER":
                setLevel(Level.FINER);
                break;
            case "FINEST":
                setLevel(Level.FINEST);
                break;
            case "All":
                setLevel(Level.ALL);
                break;
                default:
                    setLevel(Level.INFO);
                    log(Level.WARNING, "Wrong loggingLevel in settings.json! use INFO as default level");
        }
    }

    public void log(Level level, String message){
        log.log(level, message);
    }

}
