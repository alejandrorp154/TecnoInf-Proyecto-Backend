package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Remote
public interface ControladorEventoRemote {
	DTOEvento crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento);
	DTOEvento modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
	List<DTOEvento> obtenerEventos(String idPersona, int offset, int size);
}
