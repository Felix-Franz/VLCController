package backend.general.config.impl;


import backend.general.config.Config;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Felix on 09.10.2017.
 */
public class ConfigBuilder implements backend.general.config.ConfigBuilder {

    String CONFIG_PATH= "conf/controller.json";

    @Override
    public Config build() {
        try {
            FileReader reader = new FileReader(CONFIG_PATH);
            Gson gson = new Gson();
            return gson.fromJson(reader, backend.general.config.impl.Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //ToDo handle exception
        }
        return null;    //ToDo handle exception
    }
}
