package in.syncuser.model;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import in.syncuser.entity.User;
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
	
	private User user;
	
//	private String userName;
//	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

}
