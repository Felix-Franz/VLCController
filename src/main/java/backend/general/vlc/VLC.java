package backend.general.vlc;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLC {
    private String name;
    private String host;
    private int port;
    private String password;

    @Override
    public String toString() {
        return name + host + port + password;
    }
}
