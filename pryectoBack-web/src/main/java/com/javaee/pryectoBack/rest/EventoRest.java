package com.javaee.pryectoBack.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.service.ControladorEventoLocal;
import com.wordnik.swagger.annotations.Api;

@Path("/evento")
@RequestScoped
@Api(value = "/evento", description = "Eventos!")
public class EventoRest {

	@EJB
	private ControladorEventoLocal controladorEvento;

	public boolean crearEvento(DTOEvento dtoEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean eliminarEvento(int idEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modificar(DTOEvento dtoEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean agregarUsuario(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removerUsuario(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean dejar(int idEvento, String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

}
