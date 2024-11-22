package in.syncuser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.syncuser.dto.UserApiDTO;
import in.syncuser.dto.UserDTO;
import in.syncuser.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	@Query("SELECT new in.syncuser.dto.UserApiDTO(u.id, u.username, u.password, u.firstName, u.lastName, u.email, u.phoneNo) FROM User u WHERE u.username =?1")
	public Optional<UserApiDTO> fetchUserDetails(String username);

	public User findByUsername(String username);

	/*
	 * This method is written for users list irrespective of active status,
	 * developed for user management filter api
	 */

	@Query("SELECT u.id AS id, u.code AS code, u.firstName AS firstName FROM User u "
			+ "WHERE (:code IS NULL OR :code = '' OR u.code LIKE %:code%)")
	public List<UserDTO> findByCode(@Param("code") String code);

	@Query("SELECT u.id AS id, u.firstName AS firstName, u.fullName AS fullName FROM User u "
			+ "WHERE (:name IS NULL OR :name = '' OR u.firstName LIKE %:name% OR u.lastName LIKE %:name% OR u.fullName LIKE %:name%)")
	public List<UserDTO> findByAllName(@Param("name") String name);

}
