package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Singleton
public class ControladorEventoDA implements ControladorEventoDALocal, ControladorEventoDARemote {

	@Override
	public boolean crearEvento(DTOEvento dtoEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarEvento(int idEvento) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean eliminarChatEvento(int idEvento, int idChat) {
		// TODO Auto-generated method stub
		return false;
	}

}
