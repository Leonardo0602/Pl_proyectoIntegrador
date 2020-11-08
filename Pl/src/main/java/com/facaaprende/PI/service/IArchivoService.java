package com.facaaprende.PI.service;

import org.springframework.data.domain.Page;

import com.facaaprende.PI.entity.Archivo;

public interface IArchivoService {
	public Page<Archivo> archivosXProfesor(String idP, Integer page);
	public Page<Archivo> archivosXNucleo(Integer idN, Integer page);
	public Archivo crearArchivo(Archivo a);
	public Archivo editarArchivo(Archivo a);
	public void eliminarArchivo(Integer id);
}
