package backend.rest.services;

import backend.CONFIG;
import backend.general.Factory;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapper;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapperBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Felix on 21.10.2017.
 */

@Path("instances")
public class InstancesService extends AbstractService {

    //GET http://127.0.0.1:8080/api/instances
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstances(){

        ConnectorInfoWrapper[] wrapper = ConnectorInfoWrapperBuilder.createAllAutomatically(Factory.getUniversalConnectorHolder().getUniversalConnectorInstances());
        return Response.ok(wrapper)
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH).build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().path("<playerName>").build(), "get a special instance")
                .link(uriInfo.getAbsolutePathBuilder().path("reconnect").build(), "reconnects all instances")
                .build();
    }

    //POST http://127.0.0.1:8080/api/instances/reconnect
    @POST
    @Path("reconnect")
    @Produces(MediaType.TEXT_PLAIN)
    public Response playVLC(){
        Factory.getUniversalConnectorHolder().connect();
        return Response
                .ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH).build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/instances").build(), "get all instance information")
                .build();
    }
}
