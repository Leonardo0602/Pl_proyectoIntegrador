package com.facaaprende.PI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.entity.Calificacion;

@Repository
public interface ICalificacionRepo extends JpaRepository<Calificacion, Integer>{

}
