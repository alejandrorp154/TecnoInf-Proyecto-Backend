package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.service.ControladorEventoLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/evento")
@RequestScoped
@Api(value = "/evento", description = "Eventos!")
public class EventoRest {

	@EJB
	private ControladorEventoLocal controladorEvento;

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Agrega una evento al back", notes = "Se le pasa el objeto DTOEvento como sigue: {"
			+ "    \"ubicacion\": \"Ubicacion de prueba\","
			+ "    \"descripcion\": \"Descripcion de prueba\","
			+ "    \"fechaInicio\": \"2021-02-20\","
			+ "    \"fechaFin\": \"2021-02-30\","
			+ "    \"estado\": \"enCurso\","
			+ "    \"idPersona\": 1,"
			+ "    \"idChat\": \"asdasd\""
			+ "}")
	public Response crearEvento(DTOEvento dtoEvento) {
		Response.ResponseBuilder builder = null;
		try {
			boolean error = controladorEvento.crearEvento(dtoEvento);
			if (!error) {
				Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("error", "Hubo un error en el sistema.");
	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			} else {
				builder = Response.ok();
			}            
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
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
