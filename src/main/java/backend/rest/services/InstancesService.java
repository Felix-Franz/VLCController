package backend.rest.services;

import backend.general.Factory;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapper;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapperBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Felix on 21.10.2017.
 */

@Path("instances")
public class InstancesService extends AbstractService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstances(){

        ConnectorInfoWrapper[] wrapper = ConnectorInfoWrapperBuilder.createAllAutomatically(Factory.getVLCHolder().getUniversalConnectorInstances());
        return Response.ok(wrapper)
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api").build(), "start point of the api")
                .build();
    }
}
