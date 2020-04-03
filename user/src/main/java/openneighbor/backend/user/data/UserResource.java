package openneighbor.backend.user.data;

import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.security.RolesAllowed;

@RequestScoped
@Path("properties")
public class UserResource {
  @GET
  @RolesAllowed({ "volunteer", "hotlineVolunteer" })
  @Produces(MediaType.APPLICATION_JSON)
  public Properties getProperties() {
    return System.getProperties();
  }
}