package backend.general.settings;

import backend.CONFIG;
import backend.general.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Created by Felix on 14.10.2017.
 */
public class SettingsTest {

    @Test
    public void testPort() throws FileNotFoundException {
        String content = new Scanner(new File(CONFIG.SETTINGS_PATH)).useDelimiter("\\Z").next();
        int startIndex = content.indexOf("\"port\": ") + "\"port\": ".length();
        int endIndex = content.indexOf(",\n", startIndex);
        String rawPort = content.substring(startIndex, endIndex);
        int port = Integer.valueOf(rawPort);
        Assert.assertEquals(port, Factory.getSettings().getPort());
    }

    @Test
    public void testLoggingLevel() throws FileNotFoundException {
        String content = new Scanner(new File(CONFIG.SETTINGS_PATH)).useDelimiter("\\Z").next();
        int startIndex = content.indexOf("\"loggingLevel\": \"") + "\"loggingLevel\": \"".length();
        int endIndex = content.indexOf("\",\n", startIndex);
        String loggingLevel = content.substring(startIndex, endIndex);
        Assert.assertEquals(loggingLevel, Factory.getSettings().getLoggingLevel());
    }
}
