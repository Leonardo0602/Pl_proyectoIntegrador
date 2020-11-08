package com.facaaprende.PI.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.DTO.ConsultasIndiceDTO;
import com.facaaprende.PI.entity.Consultas;
import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.repository.IConsultaRepo;
import com.facaaprende.PI.repository.INucleoTRepo;
import com.facaaprende.PI.service.IConsultaService;

@Service
public class ConsultaService implements IConsultaService{

	@Autowired
	private IConsultaRepo repo;
	
	@Autowired
	private INucleoTRepo repo2;
	
	@Override
	public Consultas insertarConsulta(Consultas c) {
		NucleoTematico n = this.repo2.findById(c.getNucleo_consulta().getId()).orElse(null);
		
		if(n != null) {
			n.getConsulta().add(c);
		}
		return this.repo.save(c);
	}

	@Override
	public List<ConsultasIndiceDTO> listarConsultas() {
		return this.repo.obtenerIndices();
	}

	

}
