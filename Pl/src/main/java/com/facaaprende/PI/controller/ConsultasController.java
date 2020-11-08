package com.facaaprende.PI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facaaprende.PI.DTO.ConsultasIndiceDTO;
import com.facaaprende.PI.entity.Consultas;
import com.facaaprende.PI.service.IConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

	@Autowired
	private IConsultaService service;
	
	@PreAuthorize("hasAuthority('Alumno')")
	@PostMapping("/subir")
	public ResponseEntity<Consultas> guardarConsulta(@RequestBody Consultas a){
		Consultas p = this.service.insertarConsulta(a);
		return new ResponseEntity<Consultas>(p , HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Alumno')")
	@GetMapping("/todo")
	public ResponseEntity<List<ConsultasIndiceDTO>> obtenerNucleosAll(){
		List<ConsultasIndiceDTO> r = this.service.listarConsultas();
		// List<NucleoTematico> r = this.service.traerNucleosPorProfesorId(id);
		return new ResponseEntity<List<ConsultasIndiceDTO>>(r , HttpStatus.OK);
	}
}
