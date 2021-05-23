package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Reaccion extends ComentarioReaccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private reacciones reaccion;
	
	public Reaccion() {
	}
	
	public reacciones getReaccion() {
		return reaccion;
	}
	
	public void setReaccion(reacciones reaccion) {
		this.reaccion = reaccion;
	}

}
