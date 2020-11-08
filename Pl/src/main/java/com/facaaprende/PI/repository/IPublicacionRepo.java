package com.facaaprende.PI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.entity.Publicacion;

@Repository
public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer>{
	List<Publicacion> findByOrderByFechaDesc();
}
