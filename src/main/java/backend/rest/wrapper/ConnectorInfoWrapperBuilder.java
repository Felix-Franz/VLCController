package backend.rest.wrapper;

import backend.general.connector.AbstractConnector;
import backend.general.connector.universalConnector.UniversalConnector;

import java.util.ArrayList;

/**
 * Created by Felix on 21.10.2017.
 */
public class ConnectorInfoWrapperBuilder {
    private String name;
    private String host;
    private int port;

    private String title;
    private String state;
    private int volume;
    private long responseTime;

    public ConnectorInfoWrapperBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConnectorInfoWrapperBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public ConnectorInfoWrapperBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public ConnectorInfoWrapperBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ConnectorInfoWrapperBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public ConnectorInfoWrapperBuilder setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public ConnectorInfoWrapperBuilder setResponseTime(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public ConnectorInfoWrapper create(){
        return new ConnectorInfoWrapper(name, host, port, title, state, volume, responseTime);
    }


    /**
     * Creates a ConnectorInfoWrapper from a UniversalConnector
     * @return ConnectorInfoWrapper
     */
    public static ConnectorInfoWrapper createAutomatically(UniversalConnector connector){
        return new ConnectorInfoWrapperBuilder()
                .setName(connector.getName())
                .setHost(connector.getHost())
                .setPort(connector.getPort())
                .setTitle(connector.getTitle())
                .setState(connector.getState().toString())
                .setVolume(connector.getVolume())
                .setResponseTime(connector.getResponseTime())
                .create();
    }

    /**
     * Creates an array of ConnectorInfoWrapper from a UniversalConnector[]
     *
     * @return ConnectorInfoWrapper[]
     */
    public static ConnectorInfoWrapper[] createAllAutomatically(UniversalConnector[] connectors){
        ConnectorInfoWrapper[] wrappers = new ConnectorInfoWrapper[connectors.length];
        for (int i=0; i<connectors.length; i++){
            wrappers[i] = createAutomatically(connectors[i]);
        }
        return wrappers;
    }
}
