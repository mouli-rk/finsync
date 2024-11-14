package in.syncuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.syncuser.constants.Role;
import in.syncuser.entity.SystemModule;

public interface SystemModuleRepository extends JpaRepository<SystemModule, Integer> {

	@Query("SELECT sysmodule.module FROM SystemModule sysmodule JOIN sysmodule.modulePrivilege privilege JOIN privilege.roleType roleType WHERE roleType.role = ?1")
	public List<String> findModulesByRoleType(Role roleType);

}
