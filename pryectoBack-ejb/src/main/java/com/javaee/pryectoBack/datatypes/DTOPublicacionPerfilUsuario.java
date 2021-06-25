package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Usuario;

public class DTOPublicacionPerfilUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idPublicacion;
	private String contenido;
	private Date fecha;
	private DTOTipo tipo;
	private String extension;
	private String nombre;
	private List<DTOComentario> comentarioReacciones = new ArrayList<>();
	private DTOEvento evento;
	private DTOPerfilUsuario perfil;
	private Integer cantidadLikes;
	private Integer cantidadDislikes;
	private Integer cantidadComentarios;
    private String idPersona;
    private String email;
    private String nickname;
    private String imagenPerfil;
    private String extensionImagenPerfil;
    private String nombreImagenPerfil;
	
    public DTOPublicacionPerfilUsuario() {
	}

	public DTOPublicacionPerfilUsuario(Publicacion publicacion, Usuario usuario) {
		this.idPublicacion = publicacion.getIdPublicacion();
		this.contenido = publicacion.getContenido();
		this.fecha = publicacion.getFecha();
		this.tipo = new DTOTipo(publicacion.getTipo());
		this.idPersona = usuario.getIdPersona();
		this.extension = publicacion.getExtension();
		this.nombre = publicacion.getNombre();
		this.email = usuario.getEmail();
		this.nickname = usuario.getNickname();
		this.imagenPerfil = usuario.getPerfil().getImagenPerfil();
		this.extensionImagenPerfil = usuario.getPerfil().getExtension();
		this.nombreImagenPerfil = usuario.getPerfil().getNombreImagen();
		this.cantidadComentarios = 0;
		this.cantidadDislikes = 0;
		this.cantidadLikes = 0;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public DTOTipo getTipo() {
		return tipo;
	}

	public void setTipo(DTOTipo tipo) {
		this.tipo = tipo;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTOComentario> getComentarioReacciones() {
		return comentarioReacciones;
	}

	public void setComentarioReacciones(List<DTOComentario> comentarioReacciones) {
		this.comentarioReacciones = comentarioReacciones;
	}

	public DTOEvento getEvento() {
		return evento;
	}

	public void setEvento(DTOEvento evento) {
		this.evento = evento;
	}

	public DTOPerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(DTOPerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public Integer getCantidadLikes() {
		return cantidadLikes;
	}

	public void setCantidadLikes(Integer cantidadLikes) {
		this.cantidadLikes = cantidadLikes;
	}

	public Integer getCantidadDislikes() {
		return cantidadDislikes;
	}

	public void setCantidadDislikes(Integer cantidadDislikes) {
		this.cantidadDislikes = cantidadDislikes;
	}

	public Integer getCantidadComentarios() {
		return cantidadComentarios;
	}

	public void setCantidadComentarios(Integer cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getExtensionImagenPerfil() {
		return extensionImagenPerfil;
	}

	public void setExtensionImagenPerfil(String extensionImagenPerfil) {
		this.extensionImagenPerfil = extensionImagenPerfil;
	}

	public String getNombreImagenPerfil() {
		return nombreImagenPerfil;
	}

	public void setNombreImagenPerfil(String nombreImagenPerfil) {
		this.nombreImagenPerfil = nombreImagenPerfil;
	}
}
