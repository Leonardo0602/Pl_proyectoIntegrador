package com.facaaprende.PI.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.facaaprende.PI.entity.NucleoTematico;

public interface INucleosService {
	public List<NucleoTematico> traerNucleos(Integer id);
	
	public List<NucleoTematico> traerNucleosPorProfesorId(Integer id);
	
	public List<NucleoTematico> traerNucleosAll();
	
	public Page<NucleoTematico> traerNucleosPag(Integer page);
	
	public NucleoTematico guardarNucleo(NucleoTematico n);
}
