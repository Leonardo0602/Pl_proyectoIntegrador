package com.facaaprende.PI.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facaaprende.PI.DTO.ConsultasIndiceDTO;
import com.facaaprende.PI.entity.Consultas;

public interface IConsultaRepo extends JpaRepository<Consultas, Integer>{

	@Query(value = "SELECT nt.nucleo as nucleo, COUNT(c.nucleo_id) AS total FROM public.consultas c JOIN public.nucleo_tematico nt ON nt.id = c.nucleo_id GROUP BY nt.nucleo ORDER BY total DESC LIMIT 5", nativeQuery = true)
	List<ConsultasIndiceDTO> obtenerIndices();
	
}
