package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Interes;

public class DTOInteresUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idInteres;
	private String interes;
	private boolean estaSuscripto;
	
	public DTOInteresUsuario() {
	}

	public DTOInteresUsuario(Interes interes2) {
		this.idInteres = interes2.getIdInteres();
		this.interes = interes2.getInteres();
		this.estaSuscripto = false;
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public String getInteres() {
		return interes;
	}

	public void setInteres(String interes) {
		this.interes = interes;
	}

	public boolean isEstaSuscripto() {
		return estaSuscripto;
	}

	public void setEstaSuscripto(boolean estaSuscripto) {
		this.estaSuscripto = estaSuscripto;
	}
}
