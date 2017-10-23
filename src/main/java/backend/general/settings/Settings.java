package backend.general.settings;

import java.util.logging.Level;

/**
 * Created by Felix on 08.10.2017.
 */
public abstract class Settings {
    private static Settings instance;

    public static Settings getInstance(){
        if (instance==null) instance = new backend.general.settings.impl.SettingsBuilder().build();
        return instance;
    }

    public abstract Level getLoggingLevel();
    public abstract int getPort();
    public abstract int getConnectorTimeout();
    public abstract int getMaxVLCConnectionThreads();
    public abstract int getShutdownTime();

}
