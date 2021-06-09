package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Local
public interface ControladorEventoDALocal {
	DTOEvento crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento);
	DTOEvento modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
	boolean eliminarChatEvento(int idEvento, int idChat);
	List<DTOEvento> obtenerEventos(String idPersona, int offset, int size);
}
