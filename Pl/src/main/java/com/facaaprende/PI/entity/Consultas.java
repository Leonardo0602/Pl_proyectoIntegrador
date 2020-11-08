package com.facaaprende.PI.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "consultas")
public class Consultas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "nucleo_id", foreignKey = @ForeignKey(name = "FK_nucleo"))
	@JsonIgnoreProperties(value = {"consulta", "hibernateLazyInitializer"}, allowGetters = true)
	private NucleoTematico nucleo_consulta;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public NucleoTematico getNucleo_consulta() {
		return nucleo_consulta;
	}
	
	public void setNucleo_consulta(NucleoTematico nucleo_consulta) {
		this.nucleo_consulta = nucleo_consulta;
	}
}
