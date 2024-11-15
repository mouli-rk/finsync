package in.syncuser.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.syncuser.config.UserAuthDetails;
import in.syncuser.constants.FinSyncConstants;
import in.syncuser.constants.Role;
import in.syncuser.dto.UserApiDTO;
import in.syncuser.repository.RoleRepository;
import in.syncuser.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApiDTO dto = fetchUserDetails(username).orElse(null);
		if (dto != null) {
			return new UserAuthDetails(dto);
		}
		throw new UsernameNotFoundException(FinSyncConstants.USER_NOT_FOUND);
	}

	private Optional<UserApiDTO> fetchUserDetails(String username) {
		Optional<UserApiDTO> apiModel = userRepository.fetchUserDetails(username);
		List<Role> roles = roleRepository.findRolesByUserId(apiModel.get().getId());
		apiModel.get().setRoles(roles);
		return apiModel;
	}

}
