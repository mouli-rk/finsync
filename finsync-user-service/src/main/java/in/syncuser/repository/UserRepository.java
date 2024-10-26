package in.syncuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.syncuser.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

	public User findByUsername(String username);

}
