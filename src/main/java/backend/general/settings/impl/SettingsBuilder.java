package backend.general.settings.impl;


import backend.CONFIG;
import backend.general.Factory;
import backend.general.settings.Settings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class SettingsBuilder implements backend.general.settings.SettingsBuilder {

    @Override
    public Settings build() {
        try {
            Factory.getLogger().log(Level.INFO, "reading settings.json");
            FileReader reader = new FileReader(CONFIG.SETTINGS_PATH);
            Gson gson = new Gson();
            backend.general.settings.impl.Settings settings = gson.fromJson(reader, backend.general.settings.impl.Settings.class);
            reader.close();
            settings.saveSettings();
            return settings;
        } catch (FileNotFoundException e) {
            Factory.getLogger().log(Level.WARNING, "No settings.json found, creating it!");
            backend.general.settings.impl.Settings settings = new backend.general.settings.impl.Settings();
            settings.saveSettings();
            return settings;
        } catch (IOException e){
            Factory.getLogger().log(Level.WARNING,"Could not access settings configuration file!");
            return null;
        }
    }
}
