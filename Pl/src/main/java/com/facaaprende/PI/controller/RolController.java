package com.facaaprende.PI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facaaprende.PI.entity.Rol;
import com.facaaprende.PI.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {
	
	@Autowired
	private IRolService service;
	
	@GetMapping("/rol")
	public ResponseEntity<List<Rol>> obtenerPersona(){
		List<Rol> r = this.service.traerRoles();
		return new ResponseEntity<List<Rol>>(r , HttpStatus.OK);
	}

}
