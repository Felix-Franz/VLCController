package backend.general;

import backend.general.settings.Settings;

/**
 * Created by Felix on 08.10.2017.
 */
public class Factory {

    public static Settings getConfig(){
        return Settings.getInstance();
    }
}
