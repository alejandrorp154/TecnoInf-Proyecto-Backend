package com.javaee.pryectoBack.data;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Local
public interface ControladorEventoDALocal {
	DTOEvento crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento);
	boolean modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
	boolean eliminarChatEvento(int idEvento, int idChat);
}
