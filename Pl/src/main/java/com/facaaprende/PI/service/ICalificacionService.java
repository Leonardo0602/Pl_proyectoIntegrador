package com.facaaprende.PI.service;


import com.facaaprende.PI.entity.Calificacion;
import com.facaaprende.PI.entity.Persona;

public interface ICalificacionService {
	public Persona Calificar(Integer id_p, Calificacion c);
	
}
