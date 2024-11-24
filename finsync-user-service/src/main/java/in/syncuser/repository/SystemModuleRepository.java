package in.syncuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.syncuser.constants.Role;
import in.syncuser.dto.ModuleDTO;
import in.syncuser.entity.SystemModule;

public interface SystemModuleRepository extends JpaRepository<SystemModule, Integer> {

	@Query("""
			SELECT sysmodule.module FROM SystemModule sysmodule JOIN sysmodule.modulePrivilege privilege JOIN privilege.roleType roleType 
			WHERE roleType.role = ?1
			""")
	public List<String> findModulesByRoleType(Role roleType);
 
	@Query("""
			SELECT systemModule.module FROM User user JOIN user.roles roles JOIN roles.roleType roleType JOIN roleType.modulePrivilege JOIN 
			modulePrivilege.systemModule systemModule WHERE user.username = :username AND  roleType.role = :role
			""")
	public List<String> findModulesByUsernameAndRole(@Param("username") String username, @Param("role") Role role);
	
	@Query("""
			SELECT module.id AS id, module.module AS module, role.role AS role FROM GrantedAuthority authority JOIN authority.roleType role 
			JOIN role.modulePrivilege privilege JOIN privilege.systemModule module WHERE (:user IS NULL OR authority.user.id = :user) AND (:role 
			IS NULL OR role.role = :role) AND (:parent IS NULL OR module.parent.id = :parent)
			""")
	List<ModuleDTO> findChildModuleByUser(@Param("user") Integer user, @Param("role") Role role, @Param("parent") Integer parent);
}
