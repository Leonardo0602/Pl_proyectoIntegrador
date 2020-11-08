package com.facaaprende.PI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facaaprende.PI.entity.Archivo;
import com.facaaprende.PI.service.IArchivoService;

@RestController
@RequestMapping("/archivos")
public class ArchivoController {
	
	@Autowired
	private IArchivoService as;
	
	@PreAuthorize("hasAuthority('Profesor')")
	@PostMapping("/profesor/subir")
	public ResponseEntity<Archivo> guardarPublicacion(@RequestBody Archivo a){
		Archivo p = this.as.crearArchivo(a);
		return new ResponseEntity<Archivo>(p , HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Profesor')")
	@GetMapping("/profesor/{user}/{page}")
	public ResponseEntity<Page<Archivo>> obtenerNucleos(@PathVariable String user, @PathVariable Integer page){
		Page<Archivo> r = this.as.archivosXProfesor(user, page);
		// List<NucleoTematico> r = this.service.traerNucleosPorProfesorId(id);
		return new ResponseEntity<Page<Archivo>>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@GetMapping("/profesor/nucleo/{id}/{page}")
	public ResponseEntity<Page<Archivo>> obtenerXNucleos(@PathVariable Integer id, @PathVariable Integer page){
		Page<Archivo> r = this.as.archivosXNucleo(id, page);
		return new ResponseEntity<Page<Archivo>>(r , HttpStatus.OK);
	}

}
