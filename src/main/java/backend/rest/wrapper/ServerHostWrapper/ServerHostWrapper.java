package backend.rest.wrapper.ServerHostWrapper;

/**
 * Created by Felix on 26.10.2017.
 */
public class ServerHostWrapper {
    private String host;
    private String qr;

    public ServerHostWrapper(String host, String qr) {
        this.host = host;
        this.qr = qr;
    }

    public String getHost() {
        return host;
    }

    public String getQr() {
        return qr;
    }
}
