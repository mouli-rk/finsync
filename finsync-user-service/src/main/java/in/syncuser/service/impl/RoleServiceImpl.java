package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.GrantedAuthority;
import in.syncuser.entity.RoleType;
import in.syncuser.entity.User;
import in.syncuser.repository.RoleRepository;
import in.syncuser.repository.RoleTypeRepository;
import in.syncuser.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleTypeRepository roleTypeRepository;
	
	@Override
	public GrantedAuthority insertRole(RoleApiDTO apiModel) {
		GrantedAuthority role = new GrantedAuthority(new User(apiModel.getUserId()), apiModel.getRole());
		return roleRepository.save(role);
	}
	
	@Override
	public RoleType insertRoleType(RoleApiDTO apiModel) {
		RoleType roleType = new RoleType(apiModel.getRole());
		return roleTypeRepository.save(roleType);
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
	public List<RoleApiDTO> fetchByUID(Long id){
		return roleRepository.findByUserId(id);
	}
	
	@Override
	public List<GrantedAuthority> fetchAll(){
		return roleRepository.findAll();
	}
	
	@Override
	public List<RoleType> fetchAllRoleTypes(){
		return roleTypeRepository.findAll();
	}

}
