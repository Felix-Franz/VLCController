import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * Created by braunpet on 10.04.17.
 */
public class StartWebserver implements Runnable
{
	private static final String CONTEXT_PATH = ""; //run everything in a sub folder e.g. /demo
	private static final String WEB_APP_LOCATION = "src/main/ressources/frontend.webapp/";
	private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
	private static final String WEB_APP_CLASSES = "target/classes";

	public static void startWebserver(){
		Thread thread = new Thread(new StartWebserver());
		thread.start();
	}

	@Override
	public void run() {
		try {
			Tomcat tomcat = new Tomcat();
			tomcat.setPort(8080);

			Context context = tomcat.addWebapp(CONTEXT_PATH, new File(WEB_APP_LOCATION).getAbsolutePath());
			String pathToClasses = new File(WEB_APP_CLASSES).getAbsolutePath();
			WebResourceRoot resources = new StandardRoot(context);
			DirResourceSet dirResourceSet = new DirResourceSet(resources, WEB_APP_MOUNT, pathToClasses, "/");

			resources.addPreResources(dirResourceSet);
			context.setResources(resources);

			tomcat.start();
			tomcat.getServer().await();
		} catch (Exception e){
			//ToDo handle exception
		}
	}
}
