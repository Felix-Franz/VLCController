package backend.general.vlcHolder.impl;

import backend.general.vlc.VLC;

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
            vlc.connect();
        }
    }
}
