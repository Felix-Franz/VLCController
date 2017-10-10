import backend.general.Factory;

import java.util.logging.Level;

/**
 * Created by Felix on 08.10.2017.
 */
public class Start {
    public static void main(String[] args) {
        Factory.getLogger().setLevel(Factory.getSettings().getLoggingLevel());
        Factory.getVLCHolder().connect();

        Factory.getLogger().log(Level.INFO, "Started everything!");
    }
}
