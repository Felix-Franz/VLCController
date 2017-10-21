package backend.rest.services;

import backend.general.Factory;
import backend.general.connector.enums.Command;

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
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api/control").build(), "controle universalConnector instances")
                .build();
    }

    //GET http://127.0.0.1:8080/api/control
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getInformation(){
        return Response
                .ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath("api").build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().path("play").build(), "plays media on all universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("pause").build(), "pauses playing media on all universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("stop").build(), "stops playing media on all universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("backward").build(), "plays the previous item of the playlist on all universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("shuffle").build(), "toggles shuffle on all universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("repeat").build(), "toggles repeat the playlist on all universalConnector instances")
                .link(uriInfo.getAbsolutePathBuilder().path("forward").build(), "plays the next item of the playlist on all universalConnector instances")
                .build();
    }

    //POST http://127.0.0.1:8080/api/control/play
    @POST
    @Path("play")
    @Produces(MediaType.TEXT_PLAIN)
    public Response playVLC(){
        Factory.getVLCHolder().runCommand(Command.PLAY);
        return createResponse();
    }

    @POST
    @Path("pause")
    @Produces(MediaType.TEXT_PLAIN)
    public Response pauseVLC(){
        Factory.getVLCHolder().runCommand(Command.PAUSE);
        return createResponse();
    }

    @POST
    @Path("stop")
    @Produces(MediaType.TEXT_PLAIN)
    public Response stopVLC(){
        Factory.getVLCHolder().runCommand(Command.STOP);
        return createResponse();
    }

    @POST
    @Path("backward")
    @Produces(MediaType.TEXT_PLAIN)
    public Response backwardVLC(){
        Factory.getVLCHolder().runCommand(Command.BACKWARD);
        return createResponse();
    }

    @POST
    @Path("shuffle")
    @Produces(MediaType.TEXT_PLAIN)
    public Response shuffleVLC(){
        Factory.getVLCHolder().runCommand(Command.SHUFFLE);
        return createResponse();
    }

    @POST
    @Path("fullscreen")
    @Produces(MediaType.TEXT_PLAIN)
    public Response fullscreenVLC(){
        Factory.getVLCHolder().runCommand(Command.FULLSCREEN);
        return createResponse();
    }

    @POST
    @Path("repeat")
    @Produces(MediaType.TEXT_PLAIN)
    public Response repeatVLC(){
        Factory.getVLCHolder().runCommand(Command.REPEAT);
        return createResponse();
    }

    @POST
    @Path("forward")
    @Produces(MediaType.TEXT_PLAIN)
    public Response forwardVLC(){
        Factory.getVLCHolder().runCommand(Command.FORWARD);
        return createResponse();
    }

}
