package backend;

import backend.general.Factory;
import backend.utils.IPFinder;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

/**
 * Created by Felix on 12.10.2017.
 */
public class Init implements Runnable{
    @Override
    public void run() {
        Factory.getLogger().setLevel(Factory.getSettings().getLoggingLevel());
        Factory.getUniversalConnectorHolder().connect();

        runUserStartup();
    }

    private void runUserStartup(){
        try {
            Thread.currentThread().sleep(2000);
            IPFinder finder = new IPFinder().addSettingsPort();
            String[] ips = finder.getIps();
            String ipString = finder.toString();
            if (ips.length<1) {
                Factory.getLogger().log(Level.WARNING, "Could not get local host address!");
                return;
            }
            Factory.getLogger().log(Level.INFO, "You can access the webapp using one of following IP-Addresses: " + ipString);
            if(Factory.getSettings().getOpenBrowserOnStart() && Desktop.isDesktopSupported()){
                Factory.getLogger().log(Level.INFO, "Starting Webbrowser..");
                Desktop.getDesktop().browse(new URI(ips[0]));
            } else if (Factory.getSettings().getOpenBrowserOnStart()) {
                Factory.getLogger().log(Level.WARNING, "Could not open Browser");
            }

        } catch (InterruptedException | URISyntaxException | IOException e) {
            Factory.getLogger().log(Level.WARNING, "Could not display host addresses or open Browser");
        }
    }
}
