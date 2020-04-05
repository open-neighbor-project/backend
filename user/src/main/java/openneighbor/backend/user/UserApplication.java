package openneighbor.backend.user;

// JAX-RS
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import openneighbor.backend.user.model.*;

@ApplicationPath("user")
public class UserApplication extends Application {

public class User{
    @JsonbProperty("userId")
    public String UserId;

    @JsonbProperty("Address")
    public String Address;

    @JsonbProperty("phoneNumber")
    public String phoneNumber;

    }

    @JsonbCreator
    public UserApplication(
      @JsonbProperty("userId") String userId,
      @JsonbProperty("Address") String Address,
      @JsonbProperty("phoneNumber") int phoneNumber) {
          
    }
}
