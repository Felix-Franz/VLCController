package backend.general.settings.impl;

import java.util.logging.Level;

/**
 * Created by Felix on 08.10.2017.
 */

public class Settings extends backend.general.settings.Settings {

    private String loggingLevel;
    private int port;
    private int maxVLCConnectionThreads;

    protected Settings(){
    }

    @Override
    public String getLoggingLevel() {
        return loggingLevel;
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
