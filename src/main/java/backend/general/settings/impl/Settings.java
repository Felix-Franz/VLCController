package backend.general.settings.impl;

import backend.CONFIG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Felix on 08.10.2017.
 */

public class Settings extends backend.general.settings.Settings {

    private String loggingLevel = "INFO";
    private int port = 8080;
    private int maxVLCConnectionThreads = 10;

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
            FileWriter writer = new FileWriter(CONFIG.SETTINGS_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, Settings.class, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();        //ToDo handle exception
        }
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
