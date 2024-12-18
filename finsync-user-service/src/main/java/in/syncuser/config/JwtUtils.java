package in.syncuser.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import in.syncuser.constants.Role;
import in.syncuser.repository.TokenRepository;

@Service
@PropertySource("classpath:application.yaml")
public class JwtUtils {

	private static String jwtToken;

	@Value("${spring.application.jwtSecret}")
	private String jwtSecret;
		
	@Autowired
	private TokenRepository tokenRepository;

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	public String getJwtFromHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		logger.debug("Autherization Header : {}", bearerToken);
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.substring(7);
			return token;
		}
		return null;
	}

	public String generateJwtToken(String username, Role role, Integer jwtExpirationMins) throws InvalidKeyException {
		Map<String, Object> claims = new HashMap<>();
		jwtToken = Jwts.builder().claims().add(claims).subject(username).add("role", role)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationMins * 60 * 1000)).and().signWith(key())
				.compact();
		logger.debug("JWT Token Generated : {}", jwtToken);
		return jwtToken;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public static String getJwtToken() {
		return jwtToken;
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	public String extractClaim(String token, String claimKey) {
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().get(claimKey,
				String.class);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload();
	}

	public String getUsernameFromJwt(String token) {
		String userName = Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload()
				.getSubject();
		return userName;
	}

	public boolean validToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromJwt(token);
		Boolean isActive = tokenRepository.checkTokenActiveStatus(token).orElse(Boolean.FALSE);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && isActive);
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
