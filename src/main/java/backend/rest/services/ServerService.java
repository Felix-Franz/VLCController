package backend.rest.services;

import backend.CONFIG;
import backend.rest.wrapper.ServerHostWrapper.ServerHostWrapperBuilder;
import backend.utils.IPFinder;
import backend.utils.QRGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Felix on 26.10.2017.
 */

@Path("server")
public class ServerService extends AbstractService {

    //GET http://127.0.0.1:8080/api/server
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getServerInfo(){
        return Response.ok("Visit https://github.com/Felix-Franz/VLCController for more information!")
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH).build(), "start point of the api")
                .link(uriInfo.getAbsolutePathBuilder().path("host").build(), "get host information")
                .build();
    }

    //GET http://127.0.0.1:8080/api/server/host
    @GET
    @Path("host")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHost(){
        String[] ips = new IPFinder().addSettingsPort().getIps();
        return Response.ok(new ServerHostWrapperBuilder(ips, uriInfo.getAbsolutePath().toString()).create())
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/server").build(), "get all server information")
                .build();
    }

    //GET http://127.0.0.1:8080/api/server/host/0
    @GET
    @Path("host/{qr}")
    @Produces("image/png")
    public Response getHostQR(@PathParam("qr") int qr){
        String[] ips = new IPFinder().addSettingsPort().getIps();
        if ( qr < 0 || qr >= ips.length) return Response.status(404)
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/server/host").build(), "get host information")
                .build();
        return Response.ok(new QRGenerator(ips[qr]).create())
                .link(uriInfo.getAbsolutePathBuilder().replacePath(CONFIG.WEB_APP_API_PATH + "/server/host").build(), "get host information")
                .build();
    }
}
