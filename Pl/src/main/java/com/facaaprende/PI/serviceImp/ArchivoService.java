package com.facaaprende.PI.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.entity.Archivo;
import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.entity.Persona;
import com.facaaprende.PI.repository.IArchivoRepo;
import com.facaaprende.PI.repository.INucleoTRepo;
import com.facaaprende.PI.repository.IPersonaRepo;
import com.facaaprende.PI.service.IArchivoService;


@Service
public class ArchivoService implements IArchivoService{

	@Autowired
	private IPersonaRepo pr;
	
	@Autowired
	private INucleoTRepo nr;
	
	@Autowired
	private IArchivoRepo ar;
	
	@Override
	public Page<Archivo> archivosXProfesor(String idP, Integer page) {
		return this.ar.archivosProfesor(idP, PageRequest.of(page, 5, Sort.by("fecha").descending()));
	}

	@Override
	public Archivo crearArchivo(Archivo a) {
		Persona p = this.pr.findById(a.getPersona_archivo().getId()).orElse(null);
		NucleoTematico n = this.nr.findById(a.getNucleo_archivo().getId()).orElse(null);
		
		Archivo arc = null;
		
		if(p != null && n != null) {
			arc = this.ar.findByUrl(a.getUrl());
			if(arc == null) {
				arc = new Archivo();
				arc.setPersona_archivo(p);
				arc.setNucleo_archivo(n);
				arc.setFecha(a.getFecha());
				arc.setUrl(a.getUrl());
				arc.setTitulo(a.getTitulo());
			}else {
				return arc;
			}
		}
		
		return this.ar.save(arc);
	}

	@Override
	public Archivo editarArchivo(Archivo a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarArchivo(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Archivo> archivosXNucleo(Integer idN, Integer page) {
		return this.ar.archivosNucleo(idN, PageRequest.of(page, 7, Sort.by("fecha").descending()));
	}

}
