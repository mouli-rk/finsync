package in.syncuser.service;

import java.util.List;

import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.GrantedAuthority;

public interface RoleService {
	
	public GrantedAuthority findById(Integer id);

	public GrantedAuthority findByRole(String username);

	List<GrantedAuthority> fetchAll();

	List<RoleApiDTO> fetchByUID(Long id);

	GrantedAuthority insertRole(RoleApiDTO apiModel);

}
