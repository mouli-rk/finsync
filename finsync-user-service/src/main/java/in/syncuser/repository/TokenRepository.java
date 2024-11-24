package in.syncuser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.syncuser.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>{
	
	@Query("SELECT t FROM Token t WHERE t.user.id = ?1")
	Optional<Token> fetchActiveToken(Integer userId);
	
	@Query("SELECT t FROM Token t WHERE t.token =?1")
	Optional<Token> findByToken(String token);
	
	@Query("SELECT t.isActive FROM Token t WHERE t.token=?1")
	Optional<Boolean> checkTokenActiveStatus(String token);

}
