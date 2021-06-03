package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
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
			DTOEvento dtoEventoAdded = controladorEvento.crearEvento(dtoEvento);
	        builder.entity(dtoEventoAdded);           
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idEvento}")
	@ApiOperation(value = "Eliminar evento", notes = "Se le pasa el id del evento y el Id del Usuario logueado")
	public Response eliminarEvento(@PathParam("idEvento") int idEvento, String idPersona) {

		Response.ResponseBuilder builder = null;

		try{
			boolean eventoEliminado = controladorEvento.eliminarEvento(idEvento, idPersona);
			if (eventoEliminado){
				builder = Response.ok();
			}
		}catch (Exception e){
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return  builder.build();
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
