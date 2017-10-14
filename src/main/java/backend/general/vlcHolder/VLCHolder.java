package backend.general.vlcHolder;

import backend.general.vlc.VLC;
import backend.general.vlc.VLCCommand;

/**
 * Created by Felix on 09.10.2017.
 */
public abstract class VLCHolder {

    private static VLCHolder instance;

    public static VLCHolder getInstance(){
        if (instance==null) instance = new backend.general.vlcHolder.impl.VLCHolderBuilder().build();
        return instance;
    }

    /**
     * Connects to all VLC instances
     */
    public abstract void connect();

    /**
     * Runs a command on all VLC instances
     * @param command
     */
    public abstract void runCommand(VLCCommand command);

    /**
     *
     * @return all vlc instances
     */
    public abstract VLC[] getVLCInstances();
}
