package backend.rest.services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Felix on 11.10.2017.
 */
public class AbstractService {

    @Context
    protected UriInfo uriInfo;

}
