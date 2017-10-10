package backend.general.vlcHolder.impl;

import backend.general.Factory;
import backend.general.vlc.VLC;

import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLCHolder extends backend.general.vlcHolder.VLCHolder {

    private VLC[] vlcs;

    protected VLCHolder(VLC[] vlcs){
        this.vlcs = vlcs;
    }

    public void connect(){
        for (VLC vlc : vlcs){
            if (!vlc.connect())
                Factory.getLogger().log(Level.CONFIG, "Could not connect to " + vlc.getName());
        }
    }
}
