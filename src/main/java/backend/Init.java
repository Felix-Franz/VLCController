package backend;

import backend.general.Factory;
import backend.utils.IPFinder;

import java.util.logging.Level;

/**
 * Created by Felix on 12.10.2017.
 */
public class Init implements Runnable{
    @Override
    public void run() {
        Factory.getLogger().setLevel(Factory.getSettings().getLoggingLevel());
        Factory.getVLCHolder().connect();

        String ips = new IPFinder().addPort(Factory.getSettings().getPort()).toString();
        Factory.getLogger().log(Level.INFO, "You can access the webapp using one of following IP-Addresses: " + ips);
    }
}
