package com.facaaprende.PI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.facaaprende.PI.entity.Publicacion;
import com.facaaprende.PI.service.IPublicacionService;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
	
	@Autowired
	private IPublicacionService service;
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@PostMapping("/publicar")
	public ResponseEntity<Publicacion> guardarPublicacion(@RequestBody Publicacion pe){
		Publicacion p = this.service.insertarPublicacion(pe);
		return new ResponseEntity<Publicacion>(p , HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@GetMapping("/publico")
	public ResponseEntity<List<Publicacion>> obtenerPersona(){
		List<Publicacion> r = this.service.listarPublicacion();
		return new ResponseEntity<List<Publicacion>>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable Integer id){
		this.service.eliminarPublicacion(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);		
	}	
}
