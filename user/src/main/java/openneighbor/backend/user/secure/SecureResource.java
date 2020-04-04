package openneighbor.backend.user.secure;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("secure")
@Stateless
public class SecureResource {

	@Inject
	@ConfigProperty(name = "message")
	private String message;

	@Inject
	private JsonWebToken callerPrincipal;

	@GET
	@RolesAllowed("Volunteer")
	public Response message() {

		System.out.println(callerPrincipal.getIssuer());
		System.out.println(callerPrincipal.getRawToken());
		System.out.println(callerPrincipal.getTokenID());

		return Response.ok(callerPrincipal.getName() + " is allowed to read message: " + message).build();
	}
}

