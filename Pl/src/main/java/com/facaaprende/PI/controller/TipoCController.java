package com.facaaprende.PI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.facaaprende.PI.entity.TipoComentario;
import com.facaaprende.PI.service.ITipoCService;

@RestController
@RequestMapping("/tipos")
public class TipoCController {
	
	@Autowired
	private ITipoCService service;
	
	@GetMapping("/tipo")
	public ResponseEntity<List<TipoComentario>> obtenerPersona(){
		List<TipoComentario> r = this.service.traerTipos();
		return new ResponseEntity<List<TipoComentario>>(r , HttpStatus.OK);
	}

}
