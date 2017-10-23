package backend.rest.wrapper.ConnectorInfoWrapper;

/**
 * Created by Felix on 21.10.2017.
 */
public class ConnectorInfoWrapper {

    private String name;
    private String host;
    private int port;

    private String title;
    private String state;
    private int volume;
    private long responseTime;

    protected ConnectorInfoWrapper(String name, String host, int port, String title, String state, int volume, long responseTime) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.title = title;
        this.state = state;
        this.volume = volume;
        this.responseTime = responseTime;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public int getVolume() {
        return volume;
    }

    public long getResponseTime() {
        return responseTime;
    }
}
