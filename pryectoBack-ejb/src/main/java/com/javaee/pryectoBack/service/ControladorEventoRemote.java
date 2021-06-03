package com.javaee.pryectoBack.service;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Remote
public interface ControladorEventoRemote {
	DTOEvento crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento, String idPersona);
	boolean modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
}
