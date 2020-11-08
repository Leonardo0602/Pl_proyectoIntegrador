package com.facaaprende.PI.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "nucleo_tematico")
public class NucleoTematico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@JsonIgnore
	@JsonIgnoreProperties(value={"nucleo", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="nucleo")
	private List<Persona> persona;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="nucleo_archivo")
	private List<Archivo> archivo;
	
	@JsonIgnore
	@JsonIgnoreProperties(value={"nucleo_consulta", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(mappedBy="nucleo_consulta")
	private List<Consultas> consulta;
	
	@Column
	private String nucleo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Persona> getPersona() {
		return persona;
	}

	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}

	public List<Archivo> getArchivo() {
		return archivo;
	}

	public void setArchivo(List<Archivo> archivo) {
		this.archivo = archivo;
	}
 
	public String getNucleo() {
		return nucleo;
	}
	
	public void setNucleo(String nucleo) {
		this.nucleo = nucleo;
	}
	
	public List<Consultas> getConsulta() {
		return consulta;
	}
	
	public void setConsulta(List<Consultas> consulta) {
		this.consulta = consulta;
	}
	
}
