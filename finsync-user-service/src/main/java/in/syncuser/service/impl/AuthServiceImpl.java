package in.syncuser.service.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import in.syncuser.config.JwtUtil;
import in.syncuser.constants.FinSyncConstants;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;
import in.syncuser.model.EmailDetails;
import in.syncuser.model.LoginModel;
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
	private final JwtUtil jwtUtil;
	private Integer expirationTime = 60;

	public AuthServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService,
			BCryptPasswordEncoder passwordEncoder, AuthenticationManager authManager, JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public CommonModel authenticateUser(LoginModel login, HttpServletResponse response) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		CommonModel model = new CommonModel();
		if (authentication.isAuthenticated()) {
			String jwtToken = jwtUtil.generateAuthenticationToken(login.getUsername(), expirationTime);
			if (jwtToken != null) {
				User user = userRepository.findByUsername(login.getUsername());
				model.setId(user.getId());
				model.setFullName(user.getFullName());
				model.setEmail(user.getEmail());
				model.setPhoneNo(user.getPhoneNo());
				model.setFirstName(user.getFirstName());
				model.setLastName(user.getLastName());
				model.setMessage(FinSyncConstants.SUCCESS);
				model.setJwtToken(jwtToken);
				// EmailDetails mailParmas = emailSenderService.configureEmailParams(model,
				// FynSyncConstants.LOGIN_ALERT);
				// emailSenderService.sendEmailWithAttachment(mailParmas);
				configureTokenInCookie(jwtToken, response);
				return model;
			}
			model.setMessage(FinSyncConstants.JWT_TOKEN_NOT_AVAILABLE);
			return model;
		}
		model.setMessage(FinSyncConstants.AUTHENTICATION_FAILED);
		return model;
	}

	public void configureTokenInCookie(String jwtToken, HttpServletResponse response) {
		Cookie cookie = new Cookie("authToken", jwtToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(9 * 60 * 60);
		response.addCookie(cookie);
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
		Boolean isTokenExpired = jwtUtil.isTokenExpired(login.getJwtToken());
		if (!isTokenExpired) {
			String username = jwtUtil.extractUsername(login.getJwtToken());
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
