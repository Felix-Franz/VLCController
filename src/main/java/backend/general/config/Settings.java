package backend.general.config;

/**
 * Created by Felix on 08.10.2017.
 */
public abstract class Settings {
    private static Settings instance;

    public static Settings getInstance(){
        if (instance==null) instance = new backend.general.config.impl.SettingsBuilder().build();
        return instance;
    }

    public abstract int getMaxVLCConnectionThreads();

}
