package com.javaee.pryectoBack.datatypes;

public class DTOEstadistica {

	// Estadistica de Cantidad de usuarios registrados en la plataforma
	private Long cantidadUsuariosRegistrados;
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

}
