package com.facaaprende.PI.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "publicacion")
public class Publicacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column
	private LocalDateTime fecha;
	
	@Column 
	private String asunto;
	
	@Column
	private int reacciones;
	
	@ManyToOne
	@JoinColumn(name = "tipo_id", foreignKey = @ForeignKey(name = "FK_tipo_publicacion"))
	private TipoComentario tipo;
	
	@JsonIgnoreProperties(value={"publicacion", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name = "persona_id", foreignKey = @ForeignKey(name = "FK_persona_publicacion"))
	private Persona persona_publicacion;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public TipoComentario getTipo() {
		return tipo;
	}

	public void setTipo(TipoComentario tipo) {
		this.tipo = tipo;
	}

	public Persona getPersona_publicacion() {
		return persona_publicacion;
	}

	public void setPersona_publicacion(Persona persona_publicacion) {
		this.persona_publicacion = persona_publicacion;
	}

	public int getReacciones() {
		return reacciones;
	}
	
	public void setReacciones(int reacciones) {
		this.reacciones = reacciones;
	}

	
}
