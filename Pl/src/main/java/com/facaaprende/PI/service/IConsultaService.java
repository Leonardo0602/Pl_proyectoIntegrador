package com.facaaprende.PI.service;

import java.util.List;

import com.facaaprende.PI.DTO.ConsultasIndiceDTO;
import com.facaaprende.PI.entity.Consultas;

public interface IConsultaService {
	public Consultas insertarConsulta(Consultas c);
	public List<ConsultasIndiceDTO> listarConsultas();
}
