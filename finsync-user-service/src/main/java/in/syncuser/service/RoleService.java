package in.syncuser.service;

import java.util.List;

import in.syncuser.constants.Role;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.GrantedAuthority;
import in.syncuser.entity.RoleType;

public interface RoleService {
	
	public GrantedAuthority findById(Integer id);

	List<GrantedAuthority> fetchAll();

	List<RoleApiDTO> fetchByUID(Integer id);

	GrantedAuthority insertRole(RoleApiDTO apiModel);

	RoleType insertRoleType(RoleApiDTO apiModel);
	
	public List<RoleType> fetchAllRoleTypes();

	GrantedAuthority findByRole(Role role);

}
