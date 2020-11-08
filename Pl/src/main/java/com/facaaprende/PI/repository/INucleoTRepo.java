package com.facaaprende.PI.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.entity.NucleoTematico;

@Repository
public interface INucleoTRepo extends JpaRepository<NucleoTematico, Integer>{
	
	@Query(value="SELECT nu FROM public.nucleo_tematico nu\r\n"
			+ "EXCEPT \r\n"
			+ "SELECT nt FROM public.persona_nucleo pn JOIN public.nucleo_tematico nt on nt.id = pn.nucleo_id where pn.persona_id = :id", nativeQuery = true)
	public List<NucleoTematico> traerNucleosProf(@Param("id") Integer id);
	
	
	@Query("SELECT n FROM NucleoTematico n WHERE n.nucleo LIKE %:nucleo%")
	List<NucleoTematico> findXTituloN(@Param("nucleo")String nucleo);
	
}
