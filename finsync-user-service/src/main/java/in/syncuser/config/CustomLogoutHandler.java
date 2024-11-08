package in.syncuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import in.syncuser.entity.Token;
import in.syncuser.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String jwt = jwtUtils.getJwtFromHeader(request);
		if (jwt != null && !jwt.isBlank()) {
			Token token = tokenRepository.findByToken(jwt).orElseThrow();
			token.setIsActive(false);
			tokenRepository.save(token);
		}
	}
}
