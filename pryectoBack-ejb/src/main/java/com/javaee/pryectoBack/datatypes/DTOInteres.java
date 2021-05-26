package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.javaee.pryectoBack.model.Interes;

public class DTOInteres implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idInteres;
	private List<String> idPersonas;
	private String intres;
	
	public DTOInteres(int idInteres, List<String> idPersonas, String intres) {
		super();
		this.idInteres = idInteres;
		this.idPersonas = idPersonas;
		this.intres = intres;
	}

	public DTOInteres() {
	}

	public DTOInteres(Interes interes) {
		this.idInteres = interes.getIdInteres();
		this.intres = interes.getInteres();
		this.idPersonas = new ArrayList<String>();
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public List<String> getIdPersonas() {
		return idPersonas;
	}

	public void setIdPersonas(List<String> idPersonas) {
		this.idPersonas = idPersonas;
	}

	public String getIntres() {
		return intres;
	}

	public void setIntres(String intres) {
		this.intres = intres;
	}
}
