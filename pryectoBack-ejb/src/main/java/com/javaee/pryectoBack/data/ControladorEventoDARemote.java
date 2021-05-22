package com.javaee.pryectoBack.data;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Remote
public interface ControladorEventoDARemote {
	boolean crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento);
	boolean modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
	boolean eliminarChatEvento(int idEvento, int idChat);
}
