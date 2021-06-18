package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorEventoDALocal;
import com.javaee.pryectoBack.datatypes.DTODetalleEvento;
import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOEventoUsuario;

@Stateless
@Remote(ControladorEventoRemote.class)
public class ControladorEvento implements ControladorEventoLocal, ControladorEventoRemote {

	@EJB
	private ControladorEventoDALocal controladorEventoDA;
	
	@Override
	public DTOEvento crearEvento(DTOEvento dtoEvento) {
		return controladorEventoDA.crearEvento(dtoEvento);
	}

	@Override
	public boolean eliminarEvento(int idEvento, String idPersona) {
		return controladorEventoDA.eliminarEvento(idEvento, idPersona);
	}

	@Override
	public DTOEvento modificar(DTOEvento dtoEvento) {
		return controladorEventoDA.modificar(dtoEvento);
	}

	@Override
	public boolean agregarUsuario(int idEvento, String idPersona, String idPersonaInvitador) {
		return controladorEventoDA.agregarUsuario(idEvento, idPersona, idPersonaInvitador);
	}

	@Override
	public boolean removerUsuario(int idEvento, String idPersona) {
		return controladorEventoDA.removerUsuario(idEvento, idPersona);
	}

	@Override
	public boolean dejar(int idEvento, String idPersona) {
		return controladorEventoDA.dejar(idEvento, idPersona);
	}

	@Override
	public List<DTOEvento> obtenerEventos(String idPersona, int offset, int size) {
		return controladorEventoDA.obtenerEventos(idPersona, offset, size);
	}

	@Override
	public List<DTOEvento> obtenerInvitacionesPendientes(String idPersona, int offset, int size) {
		return controladorEventoDA.obtenerInvitacionesPendientes(idPersona, offset, size);
	}

	@Override
	public boolean responderIvitacion(DTOEventoUsuario dtoEventoUsuario) {
		return controladorEventoDA.responderIvitacion(dtoEventoUsuario);
	}

	@Override
	public DTODetalleEvento obtenerEventoById(int idEvento) {
		return controladorEventoDA.obtenerEventoById(idEvento);
	}

}
