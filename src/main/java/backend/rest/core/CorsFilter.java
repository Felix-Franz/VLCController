/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package backend.rest.core;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by braunpet on 17.04.16.
 */
public class CorsFilter implements ContainerResponseFilter
{
	@Override public void filter( final ContainerRequestContext containerRequestContext,
		final ContainerResponseContext containerResponseContext ) throws IOException
	{
		containerResponseContext.getHeaders( ).add( "Access-Control-Allow-Origin", "*" );
		containerResponseContext.getHeaders( ).add( "Access-Control-Allow-Headers",
			"origin, content-type, accept, authorization, link, location" );
		containerResponseContext.getHeaders( ).add( "Access-Control-Allow-Credentials", "true" );
		containerResponseContext.getHeaders( ).add( "Access-Control-Allow-Methods",
			"GET, POST, PUT, DELETE, OPTIONS, HEAD" );
		containerResponseContext.getHeaders( ).add( "Access-Control-Expose-Headers", "Link,Location" );
	}

}
