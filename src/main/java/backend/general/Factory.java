package backend.general;

import backend.general.abstractTools.Config;

/**
 * Created by Felix on 08.10.2017.
 */
public class Factory {

    public static Config getConfig(){
        return backend.general.abstractTools.Config.getInstance();
    }
}
