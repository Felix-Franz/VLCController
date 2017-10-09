package backend.general.settings;

/**
 * Created by Felix on 08.10.2017.
 */
public abstract class Settings {
    private static Settings instance;

    public static Settings getInstance(){
        if (instance==null) instance = new backend.general.settings.impl.SettingsBuilder().build();
        return instance;
    }

    public abstract int getPort();
    public abstract int getMaxVLCConnectionThreads();

}
