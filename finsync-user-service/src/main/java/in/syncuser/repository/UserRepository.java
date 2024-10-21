package in.syncuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.syncuser.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserName(String username);
	
	public User findByEmail(String email);

}
