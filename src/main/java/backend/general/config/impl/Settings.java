package backend.general.config.impl;

/**
 * Created by Felix on 08.10.2017.
 */

public class Settings extends backend.general.config.Settings {

    private int maxVLCConnectionThreads;

    protected Settings(){
    }

    @Override
    public int getMaxVLCConnectionThreads() {
        return maxVLCConnectionThreads;
    }
}
