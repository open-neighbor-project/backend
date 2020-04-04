package openneighbor.backend.user.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;

@RequestScoped
@Path("secure")
public class SecureResource {
  
  @Inject
  private JsonWebToken jwtPrincipal;

  @GET
  @RolesAllowed({ "requester", "volunteer" })
  @Path("/userid")
  public Response getJwtId() {
    return Response.ok(this.jwtPrincipal.getName()).build();
  }
}
