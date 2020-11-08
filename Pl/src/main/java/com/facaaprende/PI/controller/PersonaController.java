package com.facaaprende.PI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facaaprende.PI.DTO.IPersonaBestDTO;
import com.facaaprende.PI.entity.Calificacion;
import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.entity.Persona;
import com.facaaprende.PI.service.ICalificacionService;
import com.facaaprende.PI.service.IPersonaService;



@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private ICalificacionService calificacionService;
	
	@PostMapping("/registrar")
	public ResponseEntity<Persona> guardarPersona(@RequestBody Persona pe){
		Persona persona = this.personaService.CrearPersona(pe);
		return new ResponseEntity<Persona>(persona , HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno') or hasAuthority('Admin')")
	@GetMapping("/persona/usuario/{username}")
	public ResponseEntity<Persona> obtenerPersona(@PathVariable String username){
		Persona r = this.personaService.PersonaXCorreo(username);
		return new ResponseEntity<Persona>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@PutMapping("/persona/editar")
	public ResponseEntity<Persona> editarPersona(@RequestBody Persona pe){
		Persona persona = this.personaService.Editarpersona(pe);
		return new ResponseEntity<Persona>(persona , HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('Profesor') or hasAuthority('Alumno')")
	@GetMapping("/persona/perfil/{id}")
	public ResponseEntity<Persona> obtenerPersonaXId(@PathVariable int id){
		Persona r = this.personaService.PersonaXId(id);
		return new ResponseEntity<Persona>(r , HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('Profesor')")
	@PutMapping("/persona/agregarnucleo/{id}")
	public ResponseEntity<Persona> agregarPersonaNucleo(@PathVariable Integer id, @RequestBody List<NucleoTematico> n){
		Persona persona = this.personaService.AgregarNucleoPersona( id, n);
		return new ResponseEntity<Persona>(persona , HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Profesor')")
	@DeleteMapping("/persona/eliminar/{idp}/{idn}")
	public ResponseEntity<Persona> eliminarPersonaNucleo(@PathVariable Integer idp, @PathVariable Integer idn){
		Persona persona = this.personaService.eliminarRelacion( idp, idn);
		return new ResponseEntity<Persona>(persona , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Alumno')")
	@PutMapping("/persona/calificar/{id}")
	public ResponseEntity<Persona> calificarProfesor(@PathVariable Integer id, @RequestBody Calificacion c){
		Persona persona = this.calificacionService.Calificar(id, c);
		return new ResponseEntity<Persona>(persona , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Alumno')")
	@PutMapping("/persona/total/{id}")
	public ResponseEntity<Persona> calificaciontotalProfesor(@PathVariable Integer id, @RequestBody Double c){
		Persona persona = this.personaService.editCalificacion(id, c);
		return new ResponseEntity<Persona>(persona , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Alumno')")
	@GetMapping("/persona/best")
	public ResponseEntity<List<IPersonaBestDTO>> obtenerBestPersona(){
		List<IPersonaBestDTO> r = this.personaService.obetenrTodas();
		return new ResponseEntity<List<IPersonaBestDTO>>(r , HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/persona/admin/{rol}/{page}")
	public ResponseEntity<Page<Persona>> obtenerPersonaXRol(@PathVariable Integer rol, @PathVariable Integer page){
		Page<Persona> r = this.personaService.obtenerPersonasXRol(rol, page);
		return new ResponseEntity<Page<Persona>>(r , HttpStatus.OK);
	}
}
