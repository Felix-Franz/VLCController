package backend.general.settings;

/**
 * Created by Felix on 09.10.2017.
 */
public interface SettingsBuilder {

    /**
     * creates a settings object from config file
     *
     * @return Settings
     */
    public Settings build();
}
