package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;
import com.javaee.pryectoBack.service.ControladorUbicacionLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/ubicacion")
@RequestScoped
@Api(value = "/ubicacion", description = "Ubicaciones!")
public class UbicacionRest {

	@EJB
	private ControladorUbicacionLocal controladorUbicacionLocal;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Obtiene las ubicaciones del usuario", notes = "Se le pasa el id de la persona")
	@Path("/{idPersona}")
	public Response obtenerUbicaciones(@PathParam("idPersona") String idPersona) {
		Response.ResponseBuilder builder = null;
		List<DTOUbicacion> ubicaciones = controladorUbicacionLocal.obtenerUbicaciones(idPersona);
		if (ubicaciones != null) {
			builder = Response.ok();
			builder.entity(ubicaciones);
		} else {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", "No hay ubicaciones para la persona o la persona no existe.");
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Agrega una ubicacion para el usuario al back", notes = "Se le pasa el datatype de DTOUbicacion de la siguiente forma: "
			+ "{" + "    \"descripcion\": \"Descripcion de prueba\"," + "    \"longitud\": 60," + "    \"latitud\": 60,"
			+ "    \"fecha\": \"2021-04-20\"," + "    \"idPersona\": 1" + "}")
	public Response alta(DTOUbicacion dtoUbicacion) {
		Response.ResponseBuilder builder = null;
		try {
			DTOUbicacion newUbicacion = controladorUbicacionLocal.alta(dtoUbicacion);		
			builder = Response.ok();
			builder.entity(newUbicacion);		        
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
	@ApiOperation( value = "Se elimina una ubicacion", notes = "")
	@Path("/{idUbicacion}")
	public Response baja(@PathParam("idUbicacion") int idUbicacion) {
		Response.ResponseBuilder builder = null;
		try {
			boolean baja = controladorUbicacionLocal.baja(idUbicacion);		
			builder = Response.ok();
			builder.entity(baja);		        
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
	@ApiOperation(value = "Modifica una ubicacion para el usuario al back", notes = "Se le pasa el datatype de DTOUbicacion de la siguiente forma: "
			+ "{" + "    \"descripcion\": \"Descripcion de prueba\"," + "    \"longitud\": 60," + "    \"latitud\": 60,"
			+ "    \"fecha\": \"2021-04-20\"," + "    \"idPersona\": 1" + "}")
	public Response modificar(DTOUbicacion dtoUbicacion) {
		Response.ResponseBuilder builder = null;
		try {
			boolean modificar = controladorUbicacionLocal.modificar(dtoUbicacion);		
			builder = Response.ok();
			builder.entity(modificar);		        
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
}
