package backend;

import backend.general.Factory;

/**
 * Created by Felix on 12.10.2017.
 */
public class Init implements Runnable{
    @Override
    public void run() {
        Factory.getLogger().setLevel(Factory.getSettings().getLoggingLevel());
        Factory.getVLCHolder().connect();
    }
}
