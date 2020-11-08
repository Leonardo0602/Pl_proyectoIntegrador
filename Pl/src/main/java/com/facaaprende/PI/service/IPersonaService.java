package com.facaaprende.PI.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.facaaprende.PI.DTO.IPersonaBestDTO;
import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.entity.Persona;

public interface IPersonaService {
	public Persona CrearPersona(Persona persona);
	public Persona Editarpersona(Persona persona);
	public Persona AgregarNucleoPersona(Integer id, List<NucleoTematico> nucleo);
	public Persona EliminarPersona(int id);
	public Persona PersonaXId(int id);
	public Persona PersonaXCorreo(String correo);
	public Persona eliminarRelacion(Integer idP, Integer IdN);
	public List<IPersonaBestDTO> obetenrTodas();
	public Persona editCalificacion(Integer id, Double total);
	public Page<Persona>obtenerPersonasXRol(Integer rol, Integer pagina); 
}
