package backend.general.vlcHolder.impl;

import backend.general.vlc.VLC;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLCHolderBuilder implements backend.general.vlcHolder.VLCHolderBuilder {

    private final static String VLC_PATH = "conf/vlc.json";

    @Override
    public backend.general.vlcHolder.VLCHolder build() {
        try {
            FileReader reader = new FileReader(VLC_PATH);
            Gson gson = new Gson();
            VLC[] vlcs = gson.fromJson(reader, VLC[].class);
            return new VLCHolder(vlcs);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //ToDo handle exception
        }
        return null;    //ToDo handle exception
    }
}
