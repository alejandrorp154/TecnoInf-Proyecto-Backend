package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.javaee.pryectoBack.model.Interes;

public class DTOInteres implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idInteres;
	private String interes;
	private List<DTOPerfilUsuario> perfiles = new ArrayList<>();

	public DTOInteres() {
	}

	public DTOInteres(Interes interes) {
		this.idInteres = interes.getIdInteres();
		this.interes = interes.getInteres();
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public List<DTOPerfilUsuario> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<DTOPerfilUsuario> perfiles) {
		this.perfiles = perfiles;
	}

	public String getInteres() {
		return interes;
	}

	public void setInteres(String intres) {
		this.interes = intres;
	}
}
