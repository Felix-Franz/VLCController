package backend.general.connector;

import backend.general.connector.universalConnector.Command;

import java.io.*;
import java.net.Socket;

/**
 * Created by Felix on 21.10.2017.
 */
public interface AbstractConnector {
    /**
     * Connects to a Player instance
     *
     * @return true: connection established, false: any exception
     */
    public boolean connect();

    /**
     * Runs a command on this player instance
     *
     * @param command
     * @return true: command was successful, false: any exception
     */
    public boolean runCommand(Command command);
}
