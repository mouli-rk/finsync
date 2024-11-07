package in.syncuser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.syncuser.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>{
	
	@Query("SELECT t FROM Token t WHERE t.user.id = ?1")
	Optional<Token> fetchActiveToken(Long userId);
	
	Optional<Token> findByToken(String token);

}
