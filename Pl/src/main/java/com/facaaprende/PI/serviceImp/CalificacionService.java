package com.facaaprende.PI.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.entity.Calificacion;
import com.facaaprende.PI.entity.Persona;
import com.facaaprende.PI.repository.IPersonaRepo;
import com.facaaprende.PI.service.ICalificacionService;

@Service
public class CalificacionService implements ICalificacionService{

	@Autowired
	private IPersonaRepo repop;
	
	@Override
	public Persona Calificar(Integer id_p, Calificacion c) {
		Persona p = this.repop.findById(id_p).orElse(null);
		System.out.println(p.getNombre());
		if(p != null) {
			p.getCalificacion().add(c);
			c.setPersona_profesor(p);
		}
		return this.repop.save(p);
	}

}
