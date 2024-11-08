package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import in.syncuser.config.JwtUtils;
import in.syncuser.constants.FinSyncConstants;
import in.syncuser.dto.UserApiDTO;
import in.syncuser.entity.Token;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;
import in.syncuser.model.EmailDetails;
import in.syncuser.model.LoginModel;
import in.syncuser.repository.TokenRepository;
import in.syncuser.repository.UserRepository;
import in.syncuser.service.EmailSenderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import in.syncuser.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final EmailSenderService emailSenderService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final TokenRepository tokenRepository;
	private final JwtUtils jwtUtils;
	
	@Value("${spring.application.cookieAge}")
	private String cookieAge;
	
	@Value("${spring.application.jwtExpirationMins}")
	private String jwtExpirationMins;

	public AuthServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService,
			BCryptPasswordEncoder passwordEncoder, AuthenticationManager authManager, TokenRepository tokenRepository, JwtUtils jwtUtils) {
		super();
		this.userRepository = userRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
		this.jwtUtils = jwtUtils;
		this.tokenRepository = tokenRepository;
	}

	@Override
	public CommonModel authenticate(LoginModel apiRequest, HttpServletResponse httpResponse) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(apiRequest.getUsername(), apiRequest.getPassword()));
		CommonModel apiModel = new CommonModel();
		if (authentication.isAuthenticated()) {
			Integer jwtExpiration = Integer.parseInt(jwtExpirationMins);
			String jwtToken = jwtUtils.generateJwtToken(apiRequest.getUsername(), jwtExpiration);
			if (jwtToken != null && !jwtToken.isBlank()) {
				UserApiDTO user = userRepository.fetchUserDetails(apiRequest.getUsername()).orElse(null);
				apiModel.setEmail(user.getEmail());
				apiModel.setPhoneNo(user.getPhoneNo());
				apiModel.setFirstName(user.getFirstName());
				apiModel.setLastName(user.getLastName());
				/* EmailDetails mailParmas = emailSenderService.configureEmailParams(model,
				FynSyncConstants.LOGIN_ALERT);
				emailSenderService.sendEmailWithAttachment(mailParmas);*/
				configureToken(user.getId(), jwtToken);
				configureCookies(jwtToken, httpResponse);
				return apiModel;
			}
			return apiModel;
		}
		return apiModel;
	}
	
	private void configureToken(Long userId, String jwtToken) {
		Token token = tokenRepository.fetchActiveToken(userId)
				.orElse(new Token(new User(userId)));
		token.setToken(jwtToken);
		token.setIsActive(true);
		tokenRepository.save(token);
	}

	private void configureCookies(String jwtToken, HttpServletResponse httpResponse) {
		Cookie cookie = new Cookie("Bearer", jwtToken);
		int cookieExpiry = Integer.parseInt(cookieAge);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(cookieExpiry * 60);
		httpResponse.addCookie(cookie);
	}

	@Override
	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public Boolean secureAllPasswords() {
		List<User> users = userRepository.findAll();
		users.stream().forEach(user -> {
			user.setUsername("fsync" + user.getFirstName().substring(0, 1) + user.getId());
			user.setCode("fsync@" + user.getFirstName() + user.getId());
			user.setPassword(passwordEncoder("fsync@" + user.getFirstName() + user.getId())); // Hash the new password
		});
		userRepository.saveAll(users);
		return true;
	}

	@Override
	public String sendResetPassword(LoginModel login) {
		User user = login.getUsername() != null ? userRepository.findByUsername(login.getUsername())
				: userRepository.findByEmail(login.getEmail());
		if (user != null) {
			CommonModel model = new CommonModel();
			model.setFullName(user.getFullName());
			model.setUsername(user.getUsername());
			model.setEmail(user.getEmail());
			EmailDetails mailParmas = emailSenderService.configureEmailParams(model,
					FinSyncConstants.SEND_RESET_SUBJECT);
			Boolean response = emailSenderService.sendEmailWithAttachment(mailParmas);
			return response ? FinSyncConstants.SUCCESS : FinSyncConstants.EMAIL_SEND_FAILURE;
		}
		return FinSyncConstants.USER_NOT_FOUND;
	}

	@Override
	public String resetPassword(LoginModel login) {
		Boolean isTokenExpired = jwtUtils.isTokenExpired(login.getJwtToken());
		if (!isTokenExpired) {
			String username = jwtUtils.getUsernameFromJwt(login.getJwtToken());
			User user = userRepository.findByUsername(username);
			if (user != null) {
				user.setPassword(passwordEncoder.encode(login.getPassword()));
				userRepository.save(user);
				return FinSyncConstants.SUCCESS;
			}
			return FinSyncConstants.USER_NOT_FOUND;
		}
		return FinSyncConstants.TOKEN_EXPIRED;
	}
}
