package in.syncuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.syncuser.entity.GrantedAuthority;

public interface GrantedAuthorityRepository extends JpaRepository<GrantedAuthority, Integer> {

	List<GrantedAuthority> findByUserId(Long userId);
	
	public GrantedAuthority findByRole(String role);

}
