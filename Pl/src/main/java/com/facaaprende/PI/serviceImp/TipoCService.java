package com.facaaprende.PI.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.entity.TipoComentario;
import com.facaaprende.PI.repository.ITipoCRepo;
import com.facaaprende.PI.service.ITipoCService;

@Service
public class TipoCService implements ITipoCService{
	
	@Autowired
	private ITipoCRepo repo;

	@Override
	public List<TipoComentario> traerTipos() {
		return this.repo.findAll();
	}

}
