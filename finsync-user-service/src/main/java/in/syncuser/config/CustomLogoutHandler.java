package in.syncuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.syncuser.constants.FinSyncConstants;
import in.syncuser.entity.Token;
import in.syncuser.model.LoginModel;
import in.syncuser.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		try {
			String jwt = jwtUtils.getJwtFromHeader(request);
			if (jwt != null && !jwt.isBlank()) {
				Token token = tokenRepository.findByToken(jwt).orElseThrow();
				token.setIsActive(false);
				tokenRepository.save(token);
				LoginModel model = new LoginModel();
				model.setStatus(HttpStatus.OK.value());
				model.setMessage(FinSyncConstants.SUCCESS);
				mapper.writeValue(response.getWriter(), model);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
