package in.syncuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.syncuser.constants.Role;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.GrantedAuthority;

public interface RoleRepository extends JpaRepository<GrantedAuthority, Integer> {

	@Query("SELECT new in.syncuser.dto.RoleApiDTO(r.id, r.role) FROM GrantedAuthority r WHERE r.user.id =?1")
	List<RoleApiDTO> findByUserId(Long userId);
	
	@Query("SELECT authority FROM User u JOIN u.roles authority WHERE u.username=?1 AND authority.role =?2 ")
	List<GrantedAuthority> findRequiredRolesByUsername(String username, Role role);

	public GrantedAuthority findByRole(String role);

}
