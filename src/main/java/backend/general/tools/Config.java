package backend.general.tools;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Felix on 08.10.2017.
 */

public class Config extends backend.general.abstractTools.Config {

    String CONFIG_PATH= "conf/controller.json";

    private int maxVLCConnectionThreads;

    protected Config(){
    }

    @Override
    public int getMaxVLCConnectionThreads() {
        return maxVLCConnectionThreads;
    }
}
