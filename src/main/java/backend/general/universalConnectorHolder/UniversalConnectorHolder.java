package backend.general.universalConnectorHolder;

import backend.general.universalConnector.Command;
import backend.general.universalConnectorHolder.impl.UniversalConnectorHolderBuilder;
import backend.general.universalConnector.UniversalConnector;

/**
 * Created by Felix on 09.10.2017.
 */
public abstract class UniversalConnectorHolder {

    private static UniversalConnectorHolder instance;

    public static UniversalConnectorHolder getInstance(){
        if (instance==null) instance = new UniversalConnectorHolderBuilder().build();
        return instance;
    }

    /**
     * Connects to all UniversalConnector instances
     */
    public abstract void connect();

    /**
     * Runs a command on all UniversalConnector instances
     * @param command
     */
    public abstract void runCommand(Command command);

    /**
     *
     * @return all universalConnector instances
     */
    public abstract UniversalConnector[] getVLCInstances();
}
