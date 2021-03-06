package backend.general.connector.universalConnector;

import backend.general.connector.enums.Command;
import backend.general.connector.enums.PlayerState;
import backend.general.connector.universalConnector.Impl.UniversalConnectorHolderBuilder;

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
     * Disconnects all UniversalConnector instances
     */
    public abstract void disconnect();

    /**
     * Runs a command on all UniversalConnector instances
     * @param command
     */
    public abstract void runCommand(Command command);

    /**
     * get UniversalConnector instance by name
     * @param name of the instance
     * @return the instance
     */
    public abstract UniversalConnector getUniversalConnectorInstance(String name);

    /**
     *
     * @return all universalConnector instances
     */
    public abstract UniversalConnector[] getUniversalConnectorInstances();

    /**
     *
     * @return master state
     */
    public abstract PlayerState getState();

    /**
     * sets the volume of all instances
     */
    public abstract void setVolume(int volume);

    /**
     *
     * @return master volume
     */
    public abstract int getVolume();
}
