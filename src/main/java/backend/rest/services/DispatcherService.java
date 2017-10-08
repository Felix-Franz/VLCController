package backend.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Felix on 08.10.2017.
 */

@Path("")
public class DispatcherService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response showInfo(){
        return Response.ok("Hello World!").build();
    }
}
