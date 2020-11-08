package com.facaaprende.PI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facaaprende.PI.entity.Rol;

@Repository
public interface IRolRepo extends JpaRepository<Rol, Integer> {
	
	public List <Rol> findByVer(Boolean ver);
	
	
}
