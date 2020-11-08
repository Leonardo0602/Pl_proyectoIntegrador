package com.facaaprende.PI.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column 
	private int documento;
	
	@Column
	private String correo;
	
	@Column
	private String clave;
	
	@Column
	private String url_foto;
	
	@Column Double total;
	
	@ManyToOne
	@JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "FK_rol"))
    private Rol rol;
	
	
	@JsonIgnoreProperties(value={"vista", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name="persona_nucleo", joinColumns={@JoinColumn(name="persona_id",referencedColumnName = "id",
            nullable = false, updatable = false)}, inverseJoinColumns={@JoinColumn(name="nucleo_id",referencedColumnName = "id",
                    nullable = false, updatable = false)})
	private List<NucleoTematico> nucleo;
	
	
	
	@JsonIgnoreProperties(value={"persona_archivo", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(mappedBy="persona_archivo", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Archivo> archivo;
	
	@JsonIgnoreProperties(value={"persona_publicacion", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(mappedBy = "persona_publicacion", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Publicacion> publicacion;
	
	@JsonIgnoreProperties(value={"persona_profesor", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(mappedBy="persona_profesor", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Calificacion> calificacion;
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDocumento() {
		return documento;
	}


	public void setDocumento(int documento) {
		this.documento = documento;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	public List<NucleoTematico> getNucleo() {
		return nucleo;
	}


	public void setNucleo(List<NucleoTematico> nucleo) {
		this.nucleo = nucleo;
	}


	public List<Archivo> getArchivo() {
		return archivo;
	}


	public void setArchivo(List<Archivo> archivo) {
		this.archivo = archivo;
	}


	public List<Publicacion> getPublicacion() {
		return publicacion;
	}


	public void setPublicacion(List<Publicacion> publicacion) {
		this.publicacion = publicacion;
	}
	
	
	 public String getUrl_foto() {
		return url_foto;
	}
	 
	 
	 public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	 
	public List<Calificacion> getCalificacion() {
		return calificacion;
	}
	 
	 public void setCalificacion(List<Calificacion> calificacion) {
		this.calificacion = calificacion;
	}
	 
	 public Double getTotal() {
		return total;
	}
	 
	 public void setTotal(Double total) {
		this.total = total;
	}

}
