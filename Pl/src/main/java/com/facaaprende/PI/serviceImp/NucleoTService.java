package com.facaaprende.PI.serviceImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.entity.Persona;
import com.facaaprende.PI.repository.INucleoTRepo;
import com.facaaprende.PI.repository.IPersonaRepo;
import com.facaaprende.PI.service.INucleosService;

@Service
public class NucleoTService implements INucleosService{
	
	@Autowired
	private INucleoTRepo repo;
	
	@Autowired
	private IPersonaRepo repo2;
	
	@Override
	public List<NucleoTematico> traerNucleos(Integer id) {
		Persona p = this.repo2.findById(id).orElse(null);
		List<NucleoTematico> nu = new ArrayList<NucleoTematico>();
		if(p != null) {
			for (NucleoTematico nucleoTematico : p.getNucleo()) {
				nu.add(nucleoTematico);
			}
		}
		
		List<NucleoTematico> n = this.repo.findAll();
		n.removeAll(new HashSet<>(nu));
		return n;
	}

	@Override
	public List<NucleoTematico> traerNucleosPorProfesorId(Integer id) {
		// TODO Auto-generated method stub
		return this.repo.traerNucleosProf(id);
	}

	@Override
	public List<NucleoTematico> traerNucleosAll() {
		return this.repo.findAll();
	}

	@Override
	public Page<NucleoTematico> traerNucleosPag(Integer page) {
		// TODO Auto-generated method stub
		return this.repo.findAll(PageRequest.of(page, 5));
	}

	@Override
	public NucleoTematico guardarNucleo(NucleoTematico n){
			return this.repo.save(n);
	}

}
