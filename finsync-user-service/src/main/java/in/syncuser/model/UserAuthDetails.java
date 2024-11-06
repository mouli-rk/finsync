package in.syncuser.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.syncuser.dto.UserApiDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDetails implements UserDetails{

	/**
	 * When a class implements Serializable, it's a good practice to declare a
	 * serialVersionUID to ensure that serialized objects can be deserialized
	 * correctly, especially if the class definition changes over time.
	 */
	private static final long serialVersionUID = 1L;
	
	private UserApiDTO user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var roles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
		return roles;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
