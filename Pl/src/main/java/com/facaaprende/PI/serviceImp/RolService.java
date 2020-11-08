package com.facaaprende.PI.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.entity.Rol;
import com.facaaprende.PI.repository.IRolRepo;
import com.facaaprende.PI.service.IRolService;

@Service
public class RolService implements IRolService{
	
	@Autowired
	private IRolRepo repo;

	@Override
	public List<Rol> traerRoles() {
		List<Rol> roles = this.repo.findByVer(true);
		return roles;
	}
	
	

}
