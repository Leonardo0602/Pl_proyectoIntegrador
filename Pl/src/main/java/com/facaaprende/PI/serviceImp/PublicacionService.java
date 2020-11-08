package com.facaaprende.PI.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.entity.Persona;
import com.facaaprende.PI.entity.Publicacion;
import com.facaaprende.PI.exception.NotFoundModelException;
import com.facaaprende.PI.repository.IPersonaRepo;
import com.facaaprende.PI.repository.IPublicacionRepo;
import com.facaaprende.PI.service.IPublicacionService;

@Service
public class PublicacionService implements IPublicacionService{

	@Autowired
	private IPublicacionRepo repo;
	
	@Autowired
	private IPersonaRepo repo2;
	
	@Override
	public Publicacion insertarPublicacion(Publicacion p) {
		Persona pe = this.repo2.findById(p.getPersona_publicacion().getId()).orElseThrow(
				() -> new NotFoundModelException("Consulta no encontrada"));
		if(pe != null) {
			p.setPersona_publicacion(pe);
		}
		return this.repo.save(p);
	}

	@Override
	public List<Publicacion> listarPublicacion() {
		return this.repo.findByOrderByFechaDesc();
	}

	@Override
	public void eliminarPublicacion(Integer id) {
		Publicacion p = this.repo.findById(id).orElse(null);
		this.repo.delete(p);
	}

}
