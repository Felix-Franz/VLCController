package backend.general.config;

/**
 * Created by Felix on 08.10.2017.
 */
public abstract class Config {
    private static Config instance;

    public static Config getInstance(){
        if (instance==null) instance = new backend.general.config.impl.ConfigBuilder().build();
        return instance;
    }

    public abstract int getMaxVLCConnectionThreads();

}
