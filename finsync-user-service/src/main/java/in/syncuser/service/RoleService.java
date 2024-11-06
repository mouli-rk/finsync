package in.syncuser.service;

import java.util.List;

import in.syncuser.entity.GrantedAuthority;
import in.syncuser.model.RoleModel;

public interface RoleService {
	
	public GrantedAuthority findById(Integer id);

	public GrantedAuthority findByRole(String username);

	List<GrantedAuthority> fetchAll();

	List<GrantedAuthority> fetchByUID(Long id);

	GrantedAuthority insertRole(RoleModel apiModel);

}
