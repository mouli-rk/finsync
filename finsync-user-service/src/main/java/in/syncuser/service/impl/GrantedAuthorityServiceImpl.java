package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.syncuser.entity.GrantedAuthority;
import in.syncuser.repository.GrantedAuthorityRepository;
import in.syncuser.service.GrantedAuthorityService;

@Service
public class GrantedAuthorityServiceImpl implements GrantedAuthorityService{
	
	@Autowired
	private GrantedAuthorityRepository grantedAuthorityRepository;

	@Override
	public GrantedAuthority findById(Integer id) {
		// TODO Auto-generated method stub
		return grantedAuthorityRepository.findById(id).orElse(null);
	}

	@Override
	public GrantedAuthority findByRole(String role) {
		// TODO Auto-generated method stub
		return grantedAuthorityRepository.findByRole(role);
	}
	
	@Override
	public List<GrantedAuthority> fetchByUID(Long id){
		return grantedAuthorityRepository.findByUserId(id);
	}
	
	@Override
	public List<GrantedAuthority> fetchAll(){
		return grantedAuthorityRepository.findAll();
	}

}
