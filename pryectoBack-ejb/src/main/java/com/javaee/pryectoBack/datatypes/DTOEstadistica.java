package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOEstadistica implements Serializable {
	private static final long serialVersionUID = 1L;

	// Estadistica de Cantidad de usuarios registrados en la plataforma
	private Long cantidadUsuariosRegistrados;
	// Estadistica de Cantidad de publicaciones en la plataforma
	private Long cantidadPublicaciones;
	// Estadistica de Cantidad de ubicaciones en la plataforma
	private Long cantidadUbicaciones;
	// Estadistica de Cantidad de eventos en la plataforma
	private Long cantidadEventos;
	// Estadistica de Usuarios por Pais
	private String nombrePais;
	// Estadistica de Usuarios por Medalla con puntos
	private String nombreUsuario;
	private String nombreMedalla;
	private Integer cantidadPuntos;
	// Estadistica de Ranking visitas por usuario
	private Integer cantidadVisitas;
	
	public DTOEstadistica() {
		super();
	}

	public DTOEstadistica(String nombreUsuario, Integer cantidadVisitas) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.cantidadVisitas = cantidadVisitas;
	}

	public DTOEstadistica(Long cantidadUsuariosRegistrados, String nombrePais) {
		super();
		this.cantidadUsuariosRegistrados = cantidadUsuariosRegistrados;
		this.nombrePais = nombrePais;
	}

	public Long getCantidadUsuariosRegistrados() {
		return cantidadUsuariosRegistrados;
	}

	public void setCantidadUsuariosRegistrados(Long cantidadUsuariosRegistrados) {
		this.cantidadUsuariosRegistrados = cantidadUsuariosRegistrados;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreMedalla() {
		return nombreMedalla;
	}

	public void setNombreMedalla(String nombreMedalla) {
		this.nombreMedalla = nombreMedalla;
	}

	public Integer getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void setCantidadPuntos(Integer cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}

	public Integer getCantidadVisitas() {
		return cantidadVisitas;
	}

	public void setCantidadVisitas(Integer cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}

	public Long getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}

	public void setCantidadPublicaciones(Long cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}

	public Long getCantidadUbicaciones() {
		return cantidadUbicaciones;
	}

	public void setCantidadUbicaciones(Long cantidadUbicaciones) {
		this.cantidadUbicaciones = cantidadUbicaciones;
	}

	public Long getCantidadEventos() {
		return cantidadEventos;
	}

	public void setCantidadEventos(Long cantidadEventos) {
		this.cantidadEventos = cantidadEventos;
	}

}
