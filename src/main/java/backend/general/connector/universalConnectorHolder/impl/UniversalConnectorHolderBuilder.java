package backend.general.connector.universalConnectorHolder.impl;

import backend.CONFIG;
import backend.general.Factory;
import backend.general.connector.universalConnector.UniversalConnector;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class UniversalConnectorHolderBuilder implements backend.general.connector.universalConnectorHolder.UniversalConnectorHolderBuilder {

    @Override
    public backend.general.connector.universalConnectorHolder.UniversalConnectorHolder build() {
        try {
            Factory.getLogger().log(Level.INFO, "reading player.json");
            FileReader reader = new FileReader(CONFIG.VLC_CONFIG_PATH);
            Gson gson = new Gson();
            UniversalConnector[] universalConnectors = gson.fromJson(reader, UniversalConnector[].class);
            UniversalConnectorHolder holder = new UniversalConnectorHolder(universalConnectors);
            holder.saveVLCs();
            return holder;
        } catch (FileNotFoundException e) {
            Factory.getLogger().log(Level.WARNING, "No universalConnector.json found, creating it!");
            UniversalConnectorHolder holder = new UniversalConnectorHolder(new UniversalConnector[0], true);
            holder.saveVLCs();
            return holder;
        }
    }
}
