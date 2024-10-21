package in.syncuser.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

	private static String jwtToken;
	private static SecretKey key;

	public JwtUtil() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
	    keyGenerator.init(256);
	    key = keyGenerator.generateKey();	
	}

	public String generateAuthenticationToken(String userName, Integer expirationTime) throws InvalidKeyException {
		Map<String, Object> claims = new HashMap<>();
		jwtToken = Jwts.builder().claims().add(claims).subject(userName).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + (expirationTime * 60 * 60 * 100))).and().signWith(key).compact();
		return jwtToken;
	}

	public static String getJwtToken() {
		return jwtToken;
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
	
	public String extractUsername(String token) {
		String userName = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
		return userName;
	}
	
	public boolean validToken(String token, UserDetails userDetails) {
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
