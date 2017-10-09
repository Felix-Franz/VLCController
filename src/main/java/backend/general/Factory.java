package backend.general;

import backend.general.settings.Settings;
import backend.general.vlcHolder.VLCHolder;

/**
 * Created by Felix on 08.10.2017.
 */
public class Factory {

    public static Settings getSettings(){
        return Settings.getInstance();
    }

    public static VLCHolder getVLCHolder(){
        return VLCHolder.getInstance();
    }
}
