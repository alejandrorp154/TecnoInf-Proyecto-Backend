package com.javaee.pryectoBack.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.javaee.pryectoBack.datatypes.DTOInteres;
import com.javaee.pryectoBack.service.ControladorInteresLocal;
import com.wordnik.swagger.annotations.Api;

@Path("/interes")
@RequestScoped
@Api(value = "/interes", description = "Intereses!")
public class InteresRest {

	@EJB
	private ControladorInteresLocal controladorInteres;

	public boolean alta(DTOInteres dtoInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean baja(DTOInteres dtoInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modificar(DTOInteres dtoInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean suscribe(String idPersona, int idInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean desuscribe(String idPersona, int idInteres) {
		// TODO Auto-generated method stub
		return false;
	}
}
