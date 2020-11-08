package com.facaaprende.PI.entity;

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
@Table(name = "calificacion")
public class Calificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="profesor_id", nullable = false)
	@JsonIgnoreProperties(value = {"calificacion", "hibernateLazyInitializer"}, allowSetters = true)
	private Persona persona_profesor;
	
	
	@Column
	private Integer id_alumno;
	
	@Column
	private Integer cantidad;
	
	@Column
	private Boolean voto = false;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Persona getPersona_profesor() {
		return persona_profesor;
	}

	public void setPersona_profesor(Persona persona_profesor) {
		this.persona_profesor = persona_profesor;
	}

	public Integer getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Integer id_alumno) {
		this.id_alumno = id_alumno;
	}

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
