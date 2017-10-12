package backend.rest.core;

import backend.Init;
import backend.rest.services.ControllerService;
import backend.rest.services.DispatcherService;
import backend.rest.services.UserService;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jaxrs.GensonJaxRSFeature;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by braunpet on 24.04.17.
 */
@ApplicationPath( "api" )
public class Application extends ResourceConfig
{
	public Application( )
	{
		super( );
		registerClasses( getServiceClasses( ) );
		packages( "org.glassfish.jersey.examples.linking" );
		register( DeclarativeLinkingFeature.class );
		register( MultiPartFeature.class );
		register( CorsFilter.class );
		register( new GensonJaxRSFeature( ).use(
			new GensonBuilder( ).setSkipNull( true )
								.useIndentation( true )
								.useDateAsTimestamp( false )
								.useDateFormat( new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" ) )
								.create( ) ) );

		runInit();
	}

	private void runInit(){
		Thread thread = new Thread(new Init());
		thread.start();
	}

	protected Set<Class<?>> getServiceClasses( )
	{
		Set<Class<?>> returnValue = new HashSet<>( );

		returnValue.add(DispatcherService.class);
		returnValue.add(ControllerService.class);

		return returnValue;
	}
}
