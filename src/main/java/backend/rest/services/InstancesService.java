package backend.rest.services;

import backend.general.Factory;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapper;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapperBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api").build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().path("reconnect").build(), "reconnects all instances")
                .build();
    }

    @POST
    @Path("reconnect")
    @Produces(MediaType.TEXT_PLAIN)
    public Response playVLC(){
        Factory.getUniversalConnectorHolder().connect();
        return Response
                .ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api").build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api/instances").build(), "get all instance information")
                .build();
    }
}
