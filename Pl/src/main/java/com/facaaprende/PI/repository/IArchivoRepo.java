package com.facaaprende.PI.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.entity.Archivo;

@Repository
public interface IArchivoRepo extends JpaRepository<Archivo, Integer>{
	public Archivo findByUrl(String url);
	
	@Query("SELECT a FROM Archivo a WHERE a.persona_archivo.correo = :ide")
	public Page<Archivo> archivosProfesor(@Param("ide")String ide, Pageable pageable);
	
	@Query("SELECT a FROM Archivo a WHERE a.nucleo_archivo.id = :ide")
	public Page<Archivo> archivosNucleo(@Param("ide")Integer ide, Pageable pageable);
	
}
