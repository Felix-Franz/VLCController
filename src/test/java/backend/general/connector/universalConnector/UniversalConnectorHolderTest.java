package backend.general.connector.universalConnector;

import backend.CONFIG;
import backend.general.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Felix on 14.10.2017.
 */
public class UniversalConnectorHolderTest {

    @Test
    public void testName() throws FileNotFoundException {
        String content = new Scanner(new File(CONFIG.VLC_CONFIG_PATH)).useDelimiter("\\Z").next();
        int startIndex = content.indexOf("\"name\": \"") + "\"name\": \"".length();
        int endIndex = content.indexOf("\",", startIndex);
        String name = content.substring(startIndex, endIndex);
        Assert.assertEquals(name, Factory.getUniversalConnectorHolder().getUniversalConnectorInstances()[0].getName());
    }

    @Test
    public void testPort() throws FileNotFoundException {
        String content = new Scanner(new File(CONFIG.VLC_CONFIG_PATH)).useDelimiter("\\Z").next();
        int startIndex = content.indexOf("\"port\": ") + "\"port\": ".length();
        int endIndex = content.indexOf(",", startIndex);
        String rawPort = content.substring(startIndex, endIndex);
        int port = Integer.valueOf(rawPort);
        Assert.assertEquals(port, Factory.getUniversalConnectorHolder().getUniversalConnectorInstances()[0].getPort());
    }
}
