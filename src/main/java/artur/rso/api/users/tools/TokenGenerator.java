package artur.rso.api.users.tools;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
	
	public String generateToken() {
		
	    SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[48];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    String token = encoder.encodeToString(bytes);
	    return token;
	}
}
