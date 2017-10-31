package backend.rest.services;

import backend.CONFIG;
import backend.general.Factory;
import backend.general.connector.enums.Command;
import backend.rest.wrapper.ConnectorInfoWrapper.ConnectorInfoWrapperBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Felix on 31.10.2017.
 */
@Path("instances/{name}")
public class SpecialInstanceService extends AbstractService {

    //GET http://127.0.0.1:8080/api/instances/<instanceName>
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

    private Response createPOSTResponse(){
        return Response
                .ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH).build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/instances").build(), "get all instance information")
                .link(uriInfo.getAbsolutePathBuilder().path("<playerName>").build(), "get a special instance")
                .build();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/backward
    @POST
    @Path("backward")
    @Produces(MediaType.TEXT_PLAIN)
    public Response backwardVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.BACKWARD);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/play
    @POST
    @Path("play")
    @Produces(MediaType.TEXT_PLAIN)
    public Response playVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.PLAY);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/pause
    @POST
    @Path("pause")
    @Produces(MediaType.TEXT_PLAIN)
    public Response pauseVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.PAUSE);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/stop
    @POST
    @Path("stop")
    @Produces(MediaType.TEXT_PLAIN)
    public Response stopVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.STOP);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/reset
    @POST
    @Path("reset")
    @Produces(MediaType.TEXT_PLAIN)
    public Response resetVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.RESET);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/forward
    @POST
    @Path("forward")
    @Produces(MediaType.TEXT_PLAIN)
    public Response forwardVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.FORWARD);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/shuffle
    @POST
    @Path("shuffle")
    @Produces(MediaType.TEXT_PLAIN)
    public Response shuffleVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.SHUFFLE);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/fullscreen
    @POST
    @Path("fullscreen")
    @Produces(MediaType.TEXT_PLAIN)
    public Response fullscreenVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.FULLSCREEN);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/repeat
    @POST
    @Path("repeat")
    @Produces(MediaType.TEXT_PLAIN)
    public Response repeatVLC(@PathParam("name") String name){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).runCommand(Command.REPEAT);
        return createPOSTResponse();
    }

    //POST http://127.0.0.1:8080/api/instances/<instanceName>/volume
    @POST
    @Path("volume")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postInstanceVolume(@PathParam("name") String name, int volume){
        Factory.getUniversalConnectorHolder().getUniversalConnectorInstance(name).setVolume(volume);
        return createPOSTResponse();
    }
}
