package backend.rest.services;

import backend.general.Factory;
import backend.general.vlc.VLCCommand;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Felix on 11.10.2017.
 */

@Path("control")
public class ControllerService extends AbstractService {

    private Response createResponse(){
        return Response
                .ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api/control").build(), "controle vlc instances")
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getInformation(){
        return Response
                .ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api").build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().path("play").build(), "uses the play control on all vlc instances")  //ToDo add more links
                .build();
    }

    @POST
    @Path("play")
    @Produces(MediaType.TEXT_PLAIN)
    public Response playVLC(){
        Factory.getVLCHolder().runCommand(VLCCommand.PLAY);
        return createResponse();
    }
}
