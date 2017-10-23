package backend;

/**
 * Created by Felix on 14.10.2017.
 */
public class CONFIG {
    public final static String SETTINGS_PATH = "conf/settings.json";    //path to settings
    public final static String VLC_CONFIG_PATH = "conf/player.json";   //path to player settings
    public static final String WEB_APP_CONTEXT_PATH = "";   //path to the webservice (here 127.0.0.1:8080/, but if you set this to /demo then you have to user 127.0.0.1:8080/demo)
    public static final String WEB_APP_API_PATH = "api";
    public static final String WEB_APP_PATH = WEB_APP_CONTEXT_PATH + "/" + WEB_APP_API_PATH;
    public static final String WEB_APP_LOCATION = "frontend.webapp/";   //frontend path
}
