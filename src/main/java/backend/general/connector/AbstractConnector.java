package backend.general.connector;

import backend.general.connector.enums.Command;
import backend.general.connector.enums.PlayerState;

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
     * Disconnects a Player instance
     */
    public void disconnect();

    /**
     * Runs a command on this player instance
     *
     * @param command
     * @return true: command was successful, false: any exception
     */
    public boolean runCommand(Command command);

    /**
     * displays the current title
     *
     * @return current title
     */
    public String getTitle();

    /**
     * gets volume of instance
     * @return volume
     */
    public int getVolume();

    /**
     * gets state
     * @return
     */
    public PlayerState getState();

    /**
     * sets volume
     */
    public void setVolume(int volume);
}
