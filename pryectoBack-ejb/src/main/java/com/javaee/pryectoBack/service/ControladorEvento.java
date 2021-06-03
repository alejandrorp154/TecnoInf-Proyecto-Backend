package com.javaee.pryectoBack.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorEventoDALocal;
import com.javaee.pryectoBack.datatypes.DTOEvento;

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
	public boolean modificar(DTOEvento dtoEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarUsuario(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removerUsuario(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dejar(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

}
