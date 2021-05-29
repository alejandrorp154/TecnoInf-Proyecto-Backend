package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Tipo;
import com.javaee.pryectoBack.model.tipos;

public class DTOTipo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idPublicacion;
	private tipos tipo;
	
	public DTOTipo() {
	}

	public DTOTipo(int idPublicacion, tipos tipo) {
		super();
		this.idPublicacion = idPublicacion;
		this.tipo = tipo;
	}

	public DTOTipo(Tipo tipo) {
		this.idPublicacion = tipo.getIdPublicacion();
		this.tipo = tipo.getTipo();
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public tipos getTipo() {
		return tipo;
	}

	public void setTipo(tipos tipo) {
		this.tipo = tipo;
	}
}
