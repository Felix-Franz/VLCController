import backend.general.Factory;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.util.logging.Level;

/**
 * Created by Felix on 08.10.2017.
 */
public class Start {

    private static final String CONTEXT_PATH = ""; //run everything in a sub folder e.g. /demo
    private static final String WEB_APP_LOCATION = "frontend.webapp/";
    private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
    private static final String WEB_APP_CLASSES = "target/classes";

    public static void main(String[] args) throws InterruptedException {
        Factory.getLogger().setLevel(Factory.getSettings().getLoggingLevel());
        Factory.getLogger().log(Level.INFO, "Starting webserver..");
        startWebserver();
    }

    private static void startWebserver(){
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8080);

            Context context = tomcat.addWebapp(CONTEXT_PATH, new File(WEB_APP_LOCATION).getAbsolutePath());
            String pathToClasses = new File(WEB_APP_CLASSES).getAbsolutePath();
            WebResourceRoot resources = new StandardRoot(context);
            DirResourceSet dirResourceSet = new DirResourceSet(resources, WEB_APP_MOUNT, pathToClasses, "/");

            resources.addPreResources(dirResourceSet);
            //context.setResources(resources);      //ToDo remove!

            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e){
            //ToDo handle exception
        }
    }
}
