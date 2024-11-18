package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.syncuser.config.JwtUtils;
import in.syncuser.constants.FinSyncConstants;
import in.syncuser.constants.Role;
import in.syncuser.dto.UserApiDTO;
import in.syncuser.entity.GrantedAuthority;
import in.syncuser.entity.Token;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;
import in.syncuser.model.EmailDetails;
import in.syncuser.model.LoginModel;
import in.syncuser.repository.RoleRepository;
import in.syncuser.repository.TokenRepository;
import in.syncuser.repository.UserRepository;
import in.syncuser.service.EmailSenderService;
import jakarta.servlet.http.HttpServletResponse;
import in.syncuser.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final EmailSenderService emailSenderService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final TokenRepository tokenRepository;
	private final RoleRepository roleRepository;
	private final JwtUtils jwtUtils;
	
	@Value("${spring.application.cookieAge}")
	private String cookieAge;
	
	@Value("${spring.application.jwtExpirationMins}")
	private String jwtExpirationMins;
	
	@Value("${spring.application.jwtSalt}")
	private String jwtSalt;

	public AuthServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService,
			BCryptPasswordEncoder passwordEncoder, AuthenticationManager authManager, TokenRepository tokenRepository, RoleRepository roleRepository, JwtUtils jwtUtils) {
		super();
		this.userRepository = userRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
		this.jwtUtils = jwtUtils;
		this.tokenRepository = tokenRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public CommonModel authenticate(LoginModel apiRequest, HttpServletResponse httpResponse) {
		CommonModel apiModel = new CommonModel();
		Role role = Role.valueOf(apiRequest.getRole());
		List<GrantedAuthority> roles = roleRepository.findRequiredRolesByUsername(apiRequest.getUsername(), role);
		if (roles != null && !roles.isEmpty()) {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					apiRequest.getUsername(), apiRequest.getPassword());
			Authentication authentication = authManager.authenticate(authToken);
			if (authentication.isAuthenticated()) {
				Integer jwtExpiration = Integer.parseInt(jwtExpirationMins);
				String jwtToken = jwtUtils.generateJwtToken(apiRequest.getUsername(), role, jwtExpiration);
				configureLoginDetails(apiModel, apiRequest, jwtToken);
				configureToken(apiModel.getUserId(), jwtToken);
				/*configureCookies("Bearer", jwtToken, httpResponse);*/
				return apiModel;
			}
			return apiModel;
		}
		throw new AccessDeniedException(FinSyncConstants.UNAUTHORIZED);
	}
	
	private void configureLoginDetails(CommonModel apiModel, LoginModel apiRequest, String jwtToken) {
		if (jwtToken != null && !jwtToken.isBlank()) {
			UserApiDTO user = userRepository.fetchUserDetails(apiRequest.getUsername()).orElse(null);
			apiModel.setUsername(apiRequest.getUsername());
			apiModel.setRole(apiRequest.getRole());
			apiModel.setJwtToken(jwtToken);
			/*apiModel.setFirstName(user.getFirstName());
			apiModel.setLastName(user.getLastName());
			apiModel.setEmail(user.getEmail());
			apiModel.setPhoneNo(user.getPhoneNo());*/
			apiModel.setUserId(user.getId());
			/*
			 * EmailDetails mailParmas = emailSenderService.configureEmailParams(model,
			 * FynSyncConstants.LOGIN_ALERT);
			 * emailSenderService.sendEmailWithAttachment(mailParmas);
			 */
		}
	}
	
	private void configureToken(Long userId, String jwtToken) {
		Token token = tokenRepository.fetchActiveToken(userId)
				.orElse(new Token(new User(userId)));
		token.setToken(jwtToken);
		token.setIsActive(true);
		tokenRepository.save(token);
	}

	public void configureCookie(String name, String jwtToken, HttpServletResponse httpResponse) {
		int cookieExpiry = Integer.parseInt(cookieAge);
		ResponseCookie cookie = ResponseCookie.from(name, jwtToken).sameSite("None").secure(true).httpOnly(true)
				.path("/").maxAge(cookieExpiry).build();
		httpResponse.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
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
