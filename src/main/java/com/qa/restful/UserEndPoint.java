package com.qa.restful;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.IUserService;

@Path("/user")
@DeclareRoles({"ADMIN", "USER"})
public class UserEndPoint {
	
	@Inject
	private IUserService userService;

	@POST
	@Path("/json")
	@Produces({ "application/json" })
	@RolesAllowed("ADMIN")
	public String addAdmin(String admin)
	{
		return userService.addAdmin(admin);
	}
	
	@DELETE
	@Path("json/{username}")
	@Produces({ "application/json" })
	@RolesAllowed( "ADMIN")
	public String deleteAdmin(@PathParam("username") String username) {
		return userService.deleteAdmin(username);
	}
	
	@GET
	@Path("/json/{username}")
	@Produces({ "application/json" })
	@RolesAllowed( "ADMIN")
	public String getAdmin(@PathParam("username") String username) {
		return userService.getAdmin(username);
	}
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	@RolesAllowed( "ADMIN")
	public String getAllAdmins() {
		return userService.getAllAdmins();
	}
}
