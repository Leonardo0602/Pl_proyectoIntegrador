package com.facaaprende.PI.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "archivo")
public class Archivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="persona_id", nullable = false)
	@JsonIgnoreProperties(value = {"archivo", "hibernateLazyInitializer"}, allowSetters = true)
	private Persona persona_archivo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="nucleo_id", nullable = false)
	@JsonIgnoreProperties(value = {"archivo", "hibernateLazyInitializer"}, allowSetters = true)
	private NucleoTematico nucleo_archivo;
	
	@Column
	private LocalDateTime fecha;
	
	@Column
	private String url;
	
	@Column
	private String titulo;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Persona getPersona_archivo() {
		return persona_archivo;
	}

	public void setPersona_archivo(Persona persona_archivo) {
		this.persona_archivo = persona_archivo;
	}

	public NucleoTematico getNucleo_archivo() {
		return nucleo_archivo;
	}

	public void setNucleo_archivo(NucleoTematico nucleo_archivo) {
		this.nucleo_archivo = nucleo_archivo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
		
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
