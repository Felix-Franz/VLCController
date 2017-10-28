package backend.general.settings.impl;

import backend.CONFIG;
import backend.general.Factory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Felix on 08.10.2017.
 */

public class Settings extends backend.general.settings.Settings {

    private String loggingLevel = "INFO";
    private int port = 8080;
    private int connectorTimeout = 1000;
    private boolean openBrowserOnStart = true;
    private int maxVLCConnectionThreads = 10;
    private int shutdownTime = 30;

    /**
     * creates a setting object
     */
    protected Settings(){
    }

    /**
     * saves settings to config file
     */
    protected void saveSettings(){
        try {
            Factory.getLogger().log(Level.INFO, "writing settings to file!");
            FileWriter writer = new FileWriter(CONFIG.SETTINGS_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, Settings.class, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Factory.getLogger().log(Level.WARNING, "Could not save settings file!");
        }
    }

    @Override
    public Level getLoggingLevel() {
        switch (loggingLevel){
            case "OFF":
                return Level.OFF;
            case "SEVERE":
                return Level.SEVERE;
            case "WARNING":
                return Level.WARNING;
            case "INFO":
                return Level.INFO;
            case "CONFIG":
                return Level.CONFIG;
            case "FINE":
                return Level.FINE;
            case "FINER":
                return Level.FINER;
            case "FINEST":
                return Level.FINEST;
            case "All":
                return Level.ALL;
            default:
                Factory.getLogger().log(Level.WARNING, "Wrong loggingLevel in settings.json! use INFO as default level");
                return Level.INFO;
        }
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int getConnectorTimeout() {
        return connectorTimeout;
    }

    @Override
    public boolean getOpenBrowserOnStart() {
        return openBrowserOnStart;
    }

    @Override
    public int getMaxVLCConnectionThreads() {
        return maxVLCConnectionThreads;
    }

    @Override
    public int getShutdownTime() {
        return shutdownTime;
    }
}
