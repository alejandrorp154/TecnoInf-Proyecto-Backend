package com.javaee.pryectoBack.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;
import com.javaee.pryectoBack.service.ControladorUbicacionLocal;
import com.wordnik.swagger.annotations.Api;

@Path("/ubicacion")
@RequestScoped
@Api(value = "/ubicacion", description = "Ubicaciones!")
public class UbicacionRest {

	@EJB
	private ControladorUbicacionLocal controladorUbicacionLocal;
	
	public boolean alta(DTOUbicacion dtoUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<DTOUbicacion> obtenerUbicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean baja(int idUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean modificar(DTOUbicacion dtoUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public DTOUbicacion seleccionarUbicacion(int idUbicacion) {
		// TODO Auto-generated method stub
		return null;
	}
}
