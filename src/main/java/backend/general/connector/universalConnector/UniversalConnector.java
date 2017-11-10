package backend.general.connector.universalConnector;

import backend.general.connector.AbstractConnector;

/**
 * Created by Felix on 10.11.2017.
 */
public interface UniversalConnector extends AbstractConnector {

    /**
     * Get the response time of the player
     * @return response time in ms (-1 if no connection)
     */
    public long getResponseTime();

    /**
     *
     * @return Name of player instance
     */
    public String getName();

    /**
     *
     * @return host of player instance
     */
    public String getHost();

    /**
     *
     * @return port of player instance
     */
    public int getPort();
}
