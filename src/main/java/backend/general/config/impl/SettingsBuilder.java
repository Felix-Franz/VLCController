package backend.general.config.impl;


import backend.general.config.Settings;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Felix on 09.10.2017.
 */
public class SettingsBuilder implements backend.general.config.SettingsBuilder {

    String CONFIG_PATH= "conf/settings.json";

    @Override
    public Settings build() {
        try {
            FileReader reader = new FileReader(CONFIG_PATH);
            Gson gson = new Gson();
            return gson.fromJson(reader, backend.general.config.impl.Settings.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //ToDo handle exception
        }
        return null;    //ToDo handle exception
    }
}
