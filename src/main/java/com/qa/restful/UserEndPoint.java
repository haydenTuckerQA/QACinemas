package com.qa.restful;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}
