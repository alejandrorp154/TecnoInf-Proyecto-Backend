package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Administrador() {
	}

}
