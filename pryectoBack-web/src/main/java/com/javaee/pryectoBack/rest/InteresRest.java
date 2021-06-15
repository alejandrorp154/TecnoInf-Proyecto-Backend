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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOInteres;
import com.javaee.pryectoBack.service.ControladorInteresLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/interes")
@RequestScoped
@Api(value = "/interes", description = "Intereses!")
public class InteresRest {

	@EJB
	private ControladorInteresLocal controladorInteres;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Agrega un interes al back", notes = "se le pasa el data type interes como lo devuelve el get obvio que metiendo los datos del nuevo")
	public Response alta(DTOInteres interes) {
		Response.ResponseBuilder builder = null;
		try {
			DTOInteres newInter = controladorInteres.alta(interes);
            builder = Response.ok();
            builder.entity(newInter);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Remueve el interes segun id", notes = "el que corresponda al id de interes")
	@Path("/{idInteres}")
	public Response baja(@PathParam("idInteres") int idInteres) {
		Response.ResponseBuilder builder = null;
		try {
			boolean res = controladorInteres.baja(idInteres);
			if (res) {
	            builder = Response.ok();
	            builder.entity(res);
			}
			else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "El interes con id = " + idInteres + " no se pudo eliminar. O bien no existe o hubo un error.");
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
	@ApiOperation(value = "Modifica un interes", notes = "si no existe crea el interes")
	public Response modificar(DTOInteres interes) {
		Response.ResponseBuilder builder = null;
		try {
			DTOInteres modified = controladorInteres.modificar(interes);
			if (modified.getIdInteres() != 0) {
	            builder = Response.ok();
	            builder.entity(modified);
			}
			else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "El interes con id = " + interes.getIdInteres() + " no pudo modificarse");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve una lista de data types intereses", notes = "el offset es la posicion donde empieza (0 por defecto) el size es el tamño de la lista, por ejemplo si la primera consulta es offset=0 y size=10 la segunda consulta va a ser offset=10 y size=10")
	@Path("/{offset}/{size}")
	public Response getAll(@PathParam("offset") int offset, @PathParam("size") int size)
	{
		Response.ResponseBuilder builder = null;
		try {
			List<DTOInteres> intereses = controladorInteres.getAll(offset, size);
			builder = Response.ok();
			builder.entity(intereses);
		} catch (Exception exception) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Devuelve un interes", notes = "el que corresponda al id de interes")
	@Path("/{idInteres}")
	public Response get(@PathParam("idInteres") int idInteres) throws Exception
	{
		Response.ResponseBuilder builder = null;
		DTOInteres interes = controladorInteres.getById(idInteres);
		if (interes.getIdInteres() != 0)
		{
			builder = Response.ok();
			builder.entity(interes);
		}
		else
		{
          Map<String, String> responseObj = new HashMap<>();
          responseObj.put("error", "El interes con id = " + idInteres + " no existe");
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "El usuario logueado se suscribe a un interes", notes = "")
	@Path("suscribe/{idPersona}/{idInteres}")
	public Response suscribe(@PathParam("idPersona") String idPersona, @PathParam("idInteres") int idInteres) {
		Response.ResponseBuilder builder = null;
		boolean result = controladorInteres.suscribe(idPersona, idInteres);
		if (result)
		{
			builder = Response.ok();
			builder.entity(result);
		}
		else
		{
          Map<String, String> responseObj = new HashMap<>();
          responseObj.put("error", "Algo salio mal cuando el usuario con id = " + idPersona + " intento suscribirse al interes con id = " + idInteres);
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "El usuario logueado se desuscribe de un interes", notes = "")
	@Path("desuscribe/{idPersona}/{idInteres}")
	public Response desuscribe(@PathParam("idPersona") String idPersona, @PathParam("idInteres") int idInteres) {
		Response.ResponseBuilder builder = null;
		boolean result = controladorInteres.desuscribe(idPersona, idInteres);
		if (result)
		{
			builder = Response.ok();
			builder.entity(result);
		}
		else
		{
          Map<String, String> responseObj = new HashMap<>();
          responseObj.put("error", "Algo salio mal cuando el usuario con id = " + idPersona + " intento desuscribirse al interes con id = " + idInteres);
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
}
