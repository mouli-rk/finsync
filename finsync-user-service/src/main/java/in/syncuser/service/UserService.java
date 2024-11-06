package in.syncuser.service;

import java.util.List;
import java.util.Optional;

import in.syncuser.dto.UserApiDTO;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;

public interface UserService {

	public CommonModel createUser(CommonModel userModel);

	List<User> fetchAllUsers();

	User fetchById(Long id);

	Optional<UserApiDTO> fetchUserDetails(String username);

}
