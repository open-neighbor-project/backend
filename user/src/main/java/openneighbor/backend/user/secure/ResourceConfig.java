package openneighbor.backend.user.secure;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

@LoginConfig(authMethod = "mpjwt")
@ApplicationPath("resources")
@DeclareRoles({"requester", "volunteer"})
public class ResourceConfig extends Application {

}
