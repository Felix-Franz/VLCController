package backend.general.vlcHolder;

/**
 * Created by Felix on 09.10.2017.
 */
public abstract class VLCHolder {

    private static VLCHolder instance;

    public static VLCHolder getInstance(){
        if (instance==null) instance = new backend.general.vlcHolder.impl.VLCHolderBuilder().build();
        return instance;
    }

    public abstract void connect();
}
