package openneighbor.backend.user.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.ext.Provider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;

@Provider
public class Firebase {

	public static void setCustomUserClaims(
		String uid) throws InterruptedException, ExecutionException {
	
	  Map<String, Object> claims = new HashMap<>();
	  claims.put("userId", true);
	  FirebaseAuth.getInstance().setCustomUserClaimsAsync(uid, claims).get();

	
	  String idToken = "id_token";
	 
	  FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
	  if (Boolean.TRUE.equals(decoded.getClaims().get("userId"))) {
	  }
	
	  UserRecord user = FirebaseAuth.getInstance().getUserAsync(uid).get();
	  System.out.println(user.getCustomClaims().get("userId"));
	}
}
