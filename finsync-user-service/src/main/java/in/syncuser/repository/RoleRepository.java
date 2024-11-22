package in.syncuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.syncuser.constants.Role;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.GrantedAuthority;

public interface RoleRepository extends JpaRepository<GrantedAuthority, Integer> {

	@Query("SELECT new in.syncuser.dto.RoleApiDTO(ga.id, roleType.role) FROM GrantedAuthority ga JOIN ga.roleType roleType WHERE ga.user.id =?1")
	List<RoleApiDTO> findByUserId(Integer userId);
	
	@Query("SELECT authority FROM User u JOIN u.roles authority JOIN authority.roleType roleType WHERE u.username=?1 AND roleType.role =?2 ")
	List<GrantedAuthority> findRequiredRolesByUsername(String username, Role role);
	
	@Query("SELECT roleType.role FROM User u JOIN u.roles authority JOIN authority.roleType roleType WHERE u.id=?1")
	List<Role> findRolesByUserId(Integer userId);

	@Query("SELECT authority FROM User u JOIN u.roles authority JOIN authority.roleType roleType WHERE roleType.role=?1")
	public GrantedAuthority findByRole(Role role);

}
