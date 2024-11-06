package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.syncuser.constants.Role;
import in.syncuser.entity.GrantedAuthority;
import in.syncuser.entity.User;
import in.syncuser.model.RoleModel;
import in.syncuser.repository.RoleRepository;
import in.syncuser.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public GrantedAuthority insertRole(RoleModel apiModel) {
		GrantedAuthority role = new GrantedAuthority(new User(apiModel.getUserId()), Role.valueOf(apiModel.getRole()));
		return roleRepository.save(role);
	}

	@Override
	public GrantedAuthority findById(Integer id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public GrantedAuthority findByRole(String role) {
		// TODO Auto-generated method stub
		return roleRepository.findByRole(role);
	}
	
	@Override
	public List<GrantedAuthority> fetchByUID(Long id){
		return roleRepository.findByUserId(id);
	}
	
	@Override
	public List<GrantedAuthority> fetchAll(){
		return roleRepository.findAll();
	}

}
