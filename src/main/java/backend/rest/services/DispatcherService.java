package backend.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Felix on 08.10.2017.
 */

@Path("")
public class DispatcherService extends AbstractService {

    //GET http://127.0.0.1:8080/api
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response showInfo(){
        return Response.
                ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().path("control").build(), "controle universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("instances").build(), "get all instance info")
                .build();
    }
}
