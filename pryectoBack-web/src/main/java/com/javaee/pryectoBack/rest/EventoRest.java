package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOEvento;
import com.javaee.pryectoBack.datatypes.DTOEventoUsuario;
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
			+ "    \"ubicacion\": {" + "	   \"descripcion\": \"deporte\" , " + "	   \"longitud\" : 60 , "
			+ "    \"latitud\" : 60 , " + "    \"fecha\": \"2021-04-20\", " + "    \"idPersona\": \"1\"   " + " }\" , "
			+ "    \"descripcion\": \"Descripcion de prueba\"," + "    \"fechaInicio\": \"2021-02-20\","
			+ "    \"fechaFin\": \"2021-02-30\"," + "    \"estado\": \"enCurso\"," + "    \"idPersona\": 1,"
			+ "    \"idChat\": \"asdasd\", " + "    \"nombre\": \"nombreEvento\", "
			+ "    \"nombreImagen\": \"nombreImagen\"," + "    \"imagen\": \"asdasd\", "
			+ "    \"extension\": \"extensionImagen\"" + "}")
	public Response crearEvento(DTOEvento dtoEvento) {
		Response.ResponseBuilder builder = null;
		try {
			DTOEvento dtoEventoAdded = controladorEvento.crearEvento(dtoEvento);
			builder = Response.ok();
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
	@Path("/{idEvento}/{idPersona}")
	@ApiOperation(value = "Eliminar evento", notes = "Se le pasa el id del evento y el Id del Usuario logueado")
	public Response eliminarEvento(@PathParam("idEvento") int idEvento, @PathParam("idPersona") String idPersona) {

		Response.ResponseBuilder builder = null;

		try {
			boolean eventoEliminado = controladorEvento.eliminarEvento(idEvento, idPersona);
			if (eventoEliminado) {
				builder = Response.ok();
				builder.entity(eventoEliminado);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error",
						"El evento con idEvento " + idEvento + " no se pudo eliminar o no existe mas en el servidor.");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Modifica un evento", notes = "Se le pasa el objeto DTOEvento como sigue: {"
			+ "    \"idEvento\": \"idEvento\"," + "    \"ubicacion\": {" + "	   \"descripcion\": \"deporte\" , "
			+ "	   \"longitud\" : 60 , " + "    \"latitud\" : 60 , " + "    \"fecha\": \"2021-04-20\", "
			+ "    \"idPersona\": \"1\"   " + " }\" , " + "    \"descripcion\": \"Descripcion de prueba\","
			+ "    \"fechaInicio\": \"2021-02-20\"," + "    \"fechaFin\": \"2021-02-30\","
			+ "    \"estado\": \"enCurso\"," + "    \"idPersona\": 1," + "    \"idChat\": \"asdasd\", "
			+ "    \"nombre\": \"nombreEvento\" ," + "    \"nombreImagen\": \"nombreImagen\","
			+ "    \"imagen\": \"asdasd\", " + "    \"extension\": \"extensionImagen\"" + "}")
	public Response modificar(DTOEvento dtoEvento) {
		Response.ResponseBuilder builder = null;
		try {
			DTOEvento dtoEventoModified = controladorEvento.modificar(dtoEvento);
			builder = Response.ok();
			builder.entity(dtoEventoModified);
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "El usuario logueado con id = idPersonaInvitador invita a otro usuario a un evento", notes = "el idPersona corresponde al usuario que es invitado")
	@Path("/invitar/{idEvento}/{idPersona}/{idPersonaInvitador}")
	public Response agregarUsuario(@PathParam("idEvento") int idEvento, @PathParam("idPersona") String idPersona, @PathParam("idPersonaInvitador") String idPersonaInvitador) {
		Response.ResponseBuilder builder = null;
		try {
			boolean res = controladorEvento.agregarUsuario(idEvento, idPersona, idPersonaInvitador);
			builder = Response.ok();
			builder.entity(res);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "El usuario que creo el evento remueve a un usuario del mismo", notes = "el idPersona corresponde al usuario que va a ser removido")
	@Path("/removerUsuario/{idEvento}/{idPersona}")
	public Response removerUsuario(@PathParam("idEvento") int idEvento, @PathParam("idPersona") String idPersona) {
		Response.ResponseBuilder builder = null;
		try {
			boolean res = controladorEvento.removerUsuario(idEvento, idPersona);
			builder = Response.ok();
			builder.entity(res);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "El usuario logueado deja un evento", notes = "")
	@Path("/dejar/{idEvento}/{idPersona}")
	public Response dejar(@PathParam("idEvento") int idEvento, @PathParam("idPersona") String idPersona) {
		Response.ResponseBuilder builder = null;
		try {
			boolean res = controladorEvento.dejar(idEvento, idPersona);
			builder = Response.ok();
			builder.entity(res);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve la lista de eventos de un usuario", notes = "el que corresponda al id de persona")
	@Path("/obtenerEventos/{idPersona}/{offset}/{size}")
	public Response obtenerEventos(@PathParam("idPersona") String idPersona, @PathParam("offset") int offset,
			@PathParam("size") int size) {
		Response.ResponseBuilder builder = null;
		try {
			List<DTOEvento> dtoEventos = controladorEvento.obtenerEventos(idPersona, offset, size);
			builder = Response.ok();
			builder.entity(dtoEventos);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve la lista de invitaciones pendientes a eventos de un usuario", notes = "el que corresponda al id de persona")
	@Path("/obtenerInvitacionesPendientes/{idPersona}/{offset}/{size}")
	public Response obtenerInvitacionesPendientes(@PathParam("idPersona") String idPersona,
			@PathParam("offset") int offset, @PathParam("size") int size) {
		Response.ResponseBuilder builder = null;
		try {
			List<DTOEvento> dtoEventos = controladorEvento.obtenerInvitacionesPendientes(idPersona, offset, size);
			builder = Response.ok();
			builder.entity(dtoEventos);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Actualiza la invitacion a un evento para el usuario logueado", notes = "el que corresponda al id de persona, estado es el mismo enum que para solicitud de amistad")
	@Path("/responderIvitacion")
	public Response responderIvitacion(DTOEventoUsuario dtoEventoUsuario) {
		Response.ResponseBuilder builder = null;
		try {
			boolean res = controladorEvento.responderIvitacion(dtoEventoUsuario);
			builder = Response.ok();
			builder.entity(res);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
}
