package backend.general.vlcHolder.impl;

import backend.CONFIG;
import backend.general.Factory;
import backend.general.vlc.VLC;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLCHolderBuilder implements backend.general.vlcHolder.VLCHolderBuilder {

    @Override
    public backend.general.vlcHolder.VLCHolder build() {
        try {
            Factory.getLogger().log(Level.INFO, "reading vlc.json");
            FileReader reader = new FileReader(CONFIG.VLC_CONFIG_PATH);
            Gson gson = new Gson();
            VLC[] vlcs = gson.fromJson(reader, VLC[].class);
            return new VLCHolder(vlcs);
        } catch (FileNotFoundException e) {
            Factory.getLogger().log(Level.WARNING, "No vlc.json found, creating it!");
            return new VLCHolder(new VLC[0], true);
        }
    }
}
