package com.facaaprende.PI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.DTO.IPersonaBestDTO;
import com.facaaprende.PI.entity.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Integer>{
	Persona findOneByCorreo(String correo);
	
	
	@Query("SELECT p FROM Persona p WHERE p.rol.id = :id")
	Page<Persona> personasXRol(@Param("id")Integer id, Pageable pageable);

	
	@Query(value = "SELECT p.nombre, p.apellido, p.correo, p.total, p.url_foto as foto FROM public.persona p WHERE p.rol_id = 2 ORDER BY p.total DESC LIMIT 3", nativeQuery = true)
	List<IPersonaBestDTO> listarMejores();
}
