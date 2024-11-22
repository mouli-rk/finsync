package in.syncuser.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import in.syncuser.constants.Role;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.dto.UserApiDTO;
import in.syncuser.dto.UserDTO;
import in.syncuser.entity.Address;
import in.syncuser.entity.City;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;
import in.syncuser.repository.AddressRepository;
import in.syncuser.repository.CityRepository;
import in.syncuser.repository.RoleRepository;
import in.syncuser.repository.UserRepository;
import in.syncuser.service.AuthService;
import in.syncuser.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AddressRepository addressRepository;
	private final CityRepository cityRepository;
	private final AuthService authService;

	@Override
	@Transactional
	public CommonModel createUser(CommonModel userModel) {
		City city = cityRepository.findById(userModel.getCityId())
	            .orElseThrow();
		Address address = new Address(userModel.getDrNo(), userModel.getStreet(), userModel.getLandmark(),
				userModel.getPincode(), city);
		address = addressRepository.save(address);
		User user = new User(userModel.getFirstName(), userModel.getLastName(), userModel.getGender(),
				userModel.getFullName(), userModel.getEmail(), userModel.getPhoneNo(), userModel.getAlternativePhnNo(),
				userModel.getUsername(), authService.passwordEncoder(userModel.getPassword()), address);
		user = userRepository.save(user);
		userModel.setUserId(user.getId());
		userModel.setFullName(user.getFullName());
		userModel.setEmail(user.getEmail());
		userModel.setPhoneNo(user.getPhoneNo());
		userModel.setDrNo(address.getDrNo());
		userModel.setStreet(address.getStreet());
		userModel.setPincode(address.getPincode());
		userModel.setAddressId(address.getId());
		userModel.setCityId(city.getId());
		/*userModel.setDistrictId(city.getDistrict().getId());
		userModel.setStateId(city.getDistrict().getState().getId());
		userModel.setCountryId(city.getDistrict().getState().getCountry().getId());
		userModel.setCityName(city.getCityName());
		userModel.setDistrictName(city.getDistrict().getDistrictName());
		userModel.setStateName(city.getDistrict().getState().getStateName());
		userModel.setCountryName(city.getDistrict().getState().getCountry().getCountryName());*/
		return userModel;
	}
	
	@Override
	public User fetchById(Long id){
		User user = userRepository.findById(id).orElse(null);
		return user;
	}
	
	@Override
	public List<UserDTO> findByCode(String code){
		List<UserDTO> users = userRepository.findByCode(code);
		return users;
	}
	
	@Override
	public List<UserDTO> findByAllName(String name){
		List<UserDTO> users = userRepository.findByAllName(name);
		return users;
	}
	
	@Override
	public List<User> fetchAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public Optional<UserApiDTO> fetchUserDetails(String username) {
		Optional<UserApiDTO> apiModel = userRepository.fetchUserDetails(username);
		List<RoleApiDTO> roles = roleRepository.findByUserId(apiModel.get().getId());
		List<Role> rolesList = roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
		apiModel.get().setRoles(rolesList);
		return apiModel;
	}
	

}
