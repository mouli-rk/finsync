package in.syncuser.service;

import java.util.List;

import in.syncuser.entity.GrantedAuthority;

public interface GrantedAuthorityService {
	
	public GrantedAuthority findById(Integer id);

	public GrantedAuthority findByRole(String username);

	List<GrantedAuthority> fetchAll();

	List<GrantedAuthority> fetchByUID(Long id);

}
