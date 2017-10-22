import backend.CONFIG;
import backend.general.Factory;
import backend.rest.services.DispatcherService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Felix on 08.10.2017.
 */
public class Start {
    static Tomcat tomcat = new Tomcat();

    public static void main(String[] args) throws InterruptedException {
        Factory.getLogger().setLevel(Factory.getSettings().getLoggingLevel());
        Factory.getLogger().log(Level.INFO, "Starting webserver..");
        addShutdownEvent();
        startWebserver();
    }

    private static void startWebserver(){
        try {

            //set tomcat logging level
            Handler[] handlers = Logger.getLogger("").getHandlers();
            handlers[0].setLevel(Factory.getSettings().getLoggingLevel());

            //start tomcat
            tomcat.setPort(Factory.getSettings().getPort());

            Context context = tomcat.addWebapp(CONFIG.WEB_APP_CONTEXT_PATH, new File(CONFIG.WEB_APP_LOCATION).getAbsolutePath());
            ServletContainer application = new ServletContainer(new ResourceConfig().register(DispatcherService.class));
            tomcat.addServlet(context,"jersey-container-servlet", application);//ToDo okay?
            context.findServletMapping("/");

            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e){
            //ToDo handle exception
        }
    }

    private static void addShutdownEvent(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Factory.getLogger().log(Level.INFO, "Shutdown webserver...");
            tomcat.getServer().setShutdown(null);
            Factory.getLogger().log(Level.INFO, "Done!");

            try {
                int closeTime = Factory.getSettings().getShutdownTime();
                System.out.print("Closing window in " + closeTime + " seconds");

                new Thread(() -> {
                    try {
                        System.in.read();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();    //ToDo handle exception
                    }
                }).start();

                for (closeTime--;closeTime>=0; closeTime--){
                    Thread.currentThread().sleep(1000);
                    System.out.print("\rClosing window in " + closeTime + " seconds");
                }
                System.out.println("\nBye!");
                Thread.currentThread().sleep(1000);
                Runtime.getRuntime().halt(0);
            } catch (InterruptedException e) {
                e.printStackTrace();    //ToDo handle exception
            }
        }));
    }
}
