package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.model.Evento;

@Singleton
public class ControladorEventoDA implements ControladorEventoDALocal, ControladorEventoDARemote {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Override
	public boolean crearEvento(DTOEvento dtoEvento) {
		try {
			Evento evento = new Evento(dtoEvento);
			manager.persist(evento);
			//Falta Agregar logica de puntos
			return true;
		} catch (Exception exception) {
			return false;
		}
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
