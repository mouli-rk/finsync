package in.syncuser.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	ApplicationContext context;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.debug("AuthTokenFilter called for URI : {}", request.getRequestURI());
		try {
			String jwt = parseJwt(request);
			if (jwt != null) {
				String username = jwtUtils.getUsernameFromJwt(jwt);
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					if (jwtUtils.validToken(jwt, userDetails)) {
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						logger.debug("Roles from JWT : {}", userDetails.getAuthorities());
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication : {}", e);
		}
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request){
		String jwt = jwtUtils.getJwtFromHeader(request);
		return jwt;
	}

}
