package backend.general.settings.impl;

/**
 * Created by Felix on 08.10.2017.
 */

public class Settings extends backend.general.settings.Settings {

    private int port;
    private int maxVLCConnectionThreads;

    protected Settings(){
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int getMaxVLCConnectionThreads() {
        return maxVLCConnectionThreads;
    }
}
