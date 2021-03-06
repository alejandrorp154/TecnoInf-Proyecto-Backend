package com.javaee.pryectoBack.model;

import com.javaee.pryectoBack.datatypes.DTOUsuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String celular;
	private String direccion;
	private String pais;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
	private Medalla medalla;

	@Column(name = "estaBloqueado", nullable = false, columnDefinition = "boolean default false")
	private boolean estaBloqueado;

	@ManyToMany(mappedBy = "usuarios")
	private List<Notificacion> notificaciones = new ArrayList<>();

	@OneToMany(mappedBy = "usuario")
	private List<Ubicacion> ubicaciones = new ArrayList<>();

	@OneToMany(mappedBy = "usuarioCreador")
	private List<Evento> creadorDeEventos = new ArrayList<>();
		
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
	private PerfilUsuario perfil;

	public Usuario() {
	}

	public Usuario (DTOUsuario dtoUsuario)
	{
		this.idPersona = dtoUsuario.getIdPersona();
		this.email = dtoUsuario.getEmail();
		this.nombre = dtoUsuario.getNombre();
		this.apellido = dtoUsuario.getApellido();
		this.nickname = dtoUsuario.getNickname();
		this.celular = dtoUsuario.getCelular();
		this.direccion = dtoUsuario.getDireccion();
		this.medalla = new Medalla(dtoUsuario.getMedalla());
		this.notificaciones = new ArrayList<>();
		this.ubicaciones = new ArrayList<>();
		this.creadorDeEventos = new ArrayList<>();
		this.estaBloqueado = false;
		this.pais = dtoUsuario.getPais();
	}

	public Usuario(String idPersona) {
		this.idPersona = idPersona;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Evento> getCreadorDeEventos() {
		return creadorDeEventos;
	}

	public void setCreadorDeEventos(List<Evento> creadorDeEventos) {
		this.creadorDeEventos = creadorDeEventos;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public Medalla getMedalla() {
		return medalla;
	}

	public void setMedalla(Medalla medalla) {
		this.medalla = medalla;
	}

	public boolean getEstaBloqueado() {
		return estaBloqueado;
	}

	public void setEstaBloqueado(boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
