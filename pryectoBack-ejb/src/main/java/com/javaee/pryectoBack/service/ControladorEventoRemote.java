package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTODetalleEvento;
import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOEventoUsuario;

@Remote
public interface ControladorEventoRemote {
	DTOEvento crearEvento(DTOEvento dtoEvento);
	boolean eliminarEvento(int idEvento, String idPersona);
	DTOEvento modificar(DTOEvento dtoEvento);
	boolean agregarUsuario(int idEvento, String idPersona, String idPersonaInvitador);
	boolean removerUsuario(int idEvento, String idPersona);
	boolean dejar(int idEvento, String idPersona);
	List<DTOEvento> obtenerEventos(String idPersona, int offset, int size);
	List<DTOEvento> obtenerInvitacionesPendientes(String idPersona, int offset, int size);
	boolean responderIvitacion(DTOEventoUsuario dtoEventoUsuario);
	DTODetalleEvento obtenerEventoById(int idEvento);
}
