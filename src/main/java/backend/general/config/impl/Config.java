package backend.general.config.impl;

/**
 * Created by Felix on 08.10.2017.
 */

public class Config extends backend.general.config.Config {

    String CONFIG_PATH= "conf/controller.json";

    private int maxVLCConnectionThreads;

    protected Config(){
    }

    @Override
    public int getMaxVLCConnectionThreads() {
        return maxVLCConnectionThreads;
    }
}
