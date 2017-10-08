package backend.general;

import backend.general.abstractTools.Config;

/**
 * Created by Felix on 08.10.2017.
 */
public class Builder {
    private static Config config;

    public static Config getConfig(){
        if (config==null) config = new backend.general.tools.Config();
        return config;
    }
}
