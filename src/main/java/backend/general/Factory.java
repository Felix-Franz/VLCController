package backend.general;

import backend.general.settings.Settings;
import backend.general.connector.universalConnector.UniversalConnectorHolder;
import backend.utils.Logger;

/**
 * Created by Felix on 08.10.2017.
 */
public class Factory {

    public static Settings getSettings(){
        return Settings.getInstance();
    }

    public static UniversalConnectorHolder getVLCHolder(){
        return UniversalConnectorHolder.getInstance();
    }

    public static Logger getLogger(){
        return Logger.getInstance();
    }
}
