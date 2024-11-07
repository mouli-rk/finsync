package in.syncuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.syncuser.dto.UserApiDTO;
import in.syncuser.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	@Query("SELECT new in.syncuser.dto.UserApiDTO(u.id, u.username, u.password, u.firstName, u.lastName, u.email, u.phoneNo) FROM User u WHERE u.username =?1")
	public Optional<UserApiDTO> fetchUserDetails(String username);
	
	public User findByUsername(String username);

}
