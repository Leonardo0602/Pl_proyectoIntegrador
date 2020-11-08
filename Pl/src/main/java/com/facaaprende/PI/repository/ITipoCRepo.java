package com.facaaprende.PI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.entity.TipoComentario;

@Repository
public interface ITipoCRepo extends JpaRepository<TipoComentario, Integer>{

}
