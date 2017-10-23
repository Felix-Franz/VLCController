package backend.general.settings;

import backend.CONFIG;
import backend.general.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Created by Felix on 14.10.2017.
 */
public class SettingsTest {

    @Test
    public void testPort() throws FileNotFoundException {
        String content = new Scanner(new File(CONFIG.SETTINGS_PATH)).useDelimiter("\\Z").next();
        int startIndex = content.indexOf("\"port\": ") + "\"port\": ".length();
        int endIndex = content.indexOf(",", startIndex);
        String rawPort = content.substring(startIndex, endIndex);
        int port = Integer.valueOf(rawPort);
        Assert.assertEquals(port, Factory.getSettings().getPort());
    }

    @Test
    public void testLoggingLevel() throws FileNotFoundException {
        String content = new Scanner(new File(CONFIG.SETTINGS_PATH)).useDelimiter("\\Z").next();
        int startIndex = content.indexOf("\"loggingLevel\": \"") + "\"loggingLevel\": \"".length();
        int endIndex = content.indexOf("\",", startIndex);
        String rawLoggingLevel = content.substring(startIndex, endIndex);
        Level loggingLevel;
        switch (rawLoggingLevel){
            case "OFF":
                loggingLevel = Level.OFF;
                break;
            case "SEVERE":
                loggingLevel = Level.SEVERE;
            break;
            case "WARNING":
                loggingLevel = Level.WARNING;
            break;
            case "INFO":
                loggingLevel = Level.INFO;
            break;
            case "CONFIG":
                loggingLevel = Level.CONFIG;
            break;
            case "FINE":
                loggingLevel = Level.FINE;
            break;
            case "FINER":
                loggingLevel = Level.FINER;
            break;
            case "FINEST":
                loggingLevel = Level.FINEST;
            break;
            case "All":
                loggingLevel = Level.ALL;
            break;
            default:
                Factory.getLogger().log(Level.WARNING, "Wrong loggingLevel in settings.json! use INFO as default level");
                loggingLevel = Level.INFO;
        }
        Assert.assertEquals(loggingLevel, Factory.getSettings().getLoggingLevel());
    }
}
