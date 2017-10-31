package backend.rest.services;

import backend.CONFIG;
import backend.general.Factory;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapperBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Felix on 31.10.2017.
 */
@Path("instances/{name}")
public class SpecialInstanceService extends AbstractService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstance(@PathParam("name") String name){
        return Response
                .ok(ConnectorInfoWrapperBuilder.createAutomatically(Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name)))
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH).build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/instances").build(), "get all instance information")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/instances/<playerName>/volume").build(), "change volume")
                .build();
    }
}
