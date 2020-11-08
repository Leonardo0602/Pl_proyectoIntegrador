package com.facaaprende.PI.service;

import java.util.List;

import com.facaaprende.PI.entity.Publicacion;

public interface IPublicacionService {
	public Publicacion insertarPublicacion(Publicacion p);
	public List<Publicacion> listarPublicacion();
	public void eliminarPublicacion(Integer id);
}
