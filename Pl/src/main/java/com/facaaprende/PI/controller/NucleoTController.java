package com.facaaprende.PI.controller;

import java.util.List;

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

import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.service.INucleosService;

@RestController
@RequestMapping("/nucleos")
public class NucleoTController {

	@Autowired
	private INucleosService service;
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@GetMapping("/nucleo/{id}")
	public ResponseEntity<List<NucleoTematico>> obtenerNucleos(@PathVariable Integer id){
		List<NucleoTematico> r = this.service.traerNucleos(id);
		// List<NucleoTematico> r = this.service.traerNucleosPorProfesorId(id);
		return new ResponseEntity<List<NucleoTematico>>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@GetMapping("/nucleo")
	public ResponseEntity<List<NucleoTematico>> obtenerNucleosAll(){
		List<NucleoTematico> r = this.service.traerNucleosAll();
		// List<NucleoTematico> r = this.service.traerNucleosPorProfesorId(id);
		return new ResponseEntity<List<NucleoTematico>>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/nucleo/admin/{page}")
	public ResponseEntity<Page<NucleoTematico>> obtenerNucleosAllPage(@PathVariable Integer page){
		Page<NucleoTematico> r = this.service.traerNucleosPag(page);
		// List<NucleoTematico> r = this.service.traerNucleosPorProfesorId(id);
		return new ResponseEntity<Page<NucleoTematico>>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/nucleo/guardar")
	public ResponseEntity<NucleoTematico> guardarPersona(@RequestBody NucleoTematico pe){
		NucleoTematico persona = this.service.guardarNucleo(pe);
		return new ResponseEntity<NucleoTematico>(persona , HttpStatus.CREATED);
	}
}
