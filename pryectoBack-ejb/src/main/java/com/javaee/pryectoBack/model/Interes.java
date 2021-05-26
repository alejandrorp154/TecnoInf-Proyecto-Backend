package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.javaee.pryectoBack.datatypes.DTOInteres;

@Entity
public class Interes implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idInteres;
	
	private String interes;
	@ManyToMany
	private List<PerfilUsuario> perfiles = new ArrayList<>();
	
	public Interes() {
	}

	public Interes(DTOInteres dtoInteres) {
		this.idInteres = dtoInteres.getIdInteres();
		this.interes = dtoInteres.getIntres();
		this.perfiles = new ArrayList<PerfilUsuario>();
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

	public List<PerfilUsuario> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<PerfilUsuario> perfiles) {
		this.perfiles = perfiles;
	}
}
