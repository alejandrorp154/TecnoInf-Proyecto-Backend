package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Entity
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEvento;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private estados estado;
	private String idChat;
	@OneToMany(mappedBy = "evento")
	private List<Publicacion> publicaciones = new ArrayList<>();

	@ManyToOne
	private Usuario usuarioCreador;

	private String nombre;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUbicacion")
	private Ubicacion ubicacion;
	private String nombreImagen;
	@Column(columnDefinition="text", length=10485760)
	private String imagen;
	private String extension;

	public Evento() {
	}

	public Evento(DTOEvento dtoEvento) {
		this.descripcion = dtoEvento.getDescripcion();
		this.fechaInicio = dtoEvento.getFechaInicio();
		this.fechaFin = dtoEvento.getFechaFin();
		this.estado = dtoEvento.getEstado();
		this.nombre = dtoEvento.getNombre();
		this.idChat = dtoEvento.getIdChat();
		this.usuarioCreador = new Usuario(dtoEvento.getIdPersona());
		this.ubicacion = new Ubicacion(dtoEvento.getUbicacion());
		this.nombreImagen = dtoEvento.getNombreImagen();
		this.extension = dtoEvento.getExtension();
		this.imagen = dtoEvento.getImagen();
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public estados getEstado() {
		return estado;
	}

	public void setEstado(estados estado) {
		this.estado = estado;
	}

	public String getIdChat() {
		return idChat;
	}

	public void setIdChat(String chat) {
		this.idChat = chat;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
