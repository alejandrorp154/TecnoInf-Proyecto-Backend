package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOEstadistica;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;
import com.javaee.pryectoBack.service.ControladorVisualizacionLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/visualizacion")
@RequestScoped
@Api(value = "/visualizacion", description = "Visualizaciones!")
public class VisualizacionRest {

	@EJB
	private ControladorVisualizacionLocal controladorVisualizacionLocal;

	public List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve el perfil de un usuario", notes = "el que corresponda al id de persona")
	@Path("/perfil/{idPersona}")
	public Response visualizarPerfil(@PathParam("idPersona") String idPersona) {
		Response.ResponseBuilder builder = null;
		DTOPerfilUsuario perfil = controladorVisualizacionLocal.visualizarPerfil(idPersona);
		if (perfil != null && perfil.getUsuario() != null && perfil.getUsuario().getIdPersona() != null) {
			builder = Response.ok();
			builder.entity(perfil);
		} else {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", "El perfil con id = " + idPersona + " no existe");
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve una lista de amigos sugeridos", notes = "para el usuario que corresponda el idPersona")
	@Path("/sugerirAmigo/{idPersona}/{offset}/{size}")
	public Response obtenerSugerenciaAmigos(@PathParam("idPersona") String idPersona, @PathParam("offset") int offset,
			@PathParam("size") int size) {
		Response.ResponseBuilder builder = null;
		try {
			List<DTOUsuario> dtoUsuarios = controladorVisualizacionLocal.obtenerSugerenciaAmigos(idPersona, offset,
					size);
			builder = Response.ok();
			builder.entity(dtoUsuarios);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	public List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve los usuarios registrados en el sistema", notes = "")
	@Path("/obtenerUsuarios/{offset}/{size}")
	public Response obtenerUsuarios(@PathParam("offset") int offset, @PathParam("size") int size) {
		Response.ResponseBuilder builder = null;
		try {
			List<DTOUsuario> dtoUsuarios = controladorVisualizacionLocal.obtenerUsuarios(offset, size);
			builder = Response.ok();
			builder.entity(dtoUsuarios);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve una lista de solicitudes pendientes para el usuario logueado", notes = "el que corresponda el idPersona")
	@Path("/solicitudPendiente/{idPersona}/{offset}/{size}")
	public Response obtenerSolicitudesPendientes(@PathParam("idPersona") String idPersona, @PathParam("offset") int offset, @PathParam("size") int size) {
		Response.ResponseBuilder builder = null;
		try {
			List<DTOUsuario> dtoUsuarios = controladorVisualizacionLocal.obtenerSolicitudesPendientes(idPersona, offset, size);
			builder = Response.ok();
			builder.entity(dtoUsuarios);
		} catch(Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	public DTOUsuarioMedalla visualizarProgreso(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve un Listado DTOEstadistica", notes = "Posibles valores del tipo estadistica: CantidadUsuariosTotal, UsuariosPorMedalla, CantidadVisitasPorUsuario, CantidadUsuariosPorPais")
	@Path("/estadistica/{tipoEstadistica}")
	public Response seleccionarTipoEstadistica(@PathParam("tipoEstadistica") String tipoEstadistica) {
		Response.ResponseBuilder builder = null;
		try {
			List<DTOEstadistica> dtoEstadisticas = controladorVisualizacionLocal.seleccionarTipoEstadistica(tipoEstadistica);
			builder = Response.ok();
			builder.entity(dtoEstadisticas);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

}
