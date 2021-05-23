package com.javaee.pryectoBack.service;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Local
public interface ControladorEventoLocal {
	boolean crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento);
	boolean modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
}
