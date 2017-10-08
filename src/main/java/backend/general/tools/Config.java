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

public class Config implements backend.general.abstractTools.Config {

    String CONFIG_PATH= "conf/controller.json";

    private int maxVLCConnectionThreads;

    public Config(){
        readData();
    }

    private void readData(){
        String fileContent = readFile(CONFIG_PATH);
        setConfigProperties(fileContent);
    }

    private String readFile(String configPath){
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(configPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private void setConfigProperties(String fileContent){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(fileContent, JsonObject.class);
        maxVLCConnectionThreads = jsonObject.get("maxVLCConnectionThreads").getAsInt();
    }

    @Override
    public int getMaxVLCConnectionThreads() {
        return maxVLCConnectionThreads;
    }
}
