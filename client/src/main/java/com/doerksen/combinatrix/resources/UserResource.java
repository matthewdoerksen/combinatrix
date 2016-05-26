package com.doerksen.combinatrix.resources;

import com.doerksen.combinatrix.dto.UserDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/user_test")
public interface UserResource {
    @GET
    @Path("/{id}")
    UserDto getUser(@PathParam("id") long id);
}
