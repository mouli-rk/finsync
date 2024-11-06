package in.syncuser.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.syncuser.constants.FinSyncConstants;
import in.syncuser.constants.Role;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.dto.UserApiDTO;
import in.syncuser.model.UserAuthDetails;
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
		List<RoleApiDTO> roles = roleRepository.findByUserId(apiModel.get().getId());
		List<Role> rolesList = roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
		apiModel.get().setRoles(rolesList);
		return apiModel;
	}

}
