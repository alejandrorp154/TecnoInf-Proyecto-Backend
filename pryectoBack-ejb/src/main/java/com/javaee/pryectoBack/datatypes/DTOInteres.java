package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOInteres implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idInteres;
	private String idPersona;
	private String intres;
	
	public DTOInteres(int idInteres, String idPersona, String intres) {
		super();
		this.idInteres = idInteres;
		this.idPersona = idPersona;
		this.intres = intres;
	}

	public DTOInteres() {
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getIntres() {
		return intres;
	}

	public void setIntres(String intres) {
		this.intres = intres;
	}
}
