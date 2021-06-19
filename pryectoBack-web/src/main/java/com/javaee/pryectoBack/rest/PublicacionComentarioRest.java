package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;
import com.javaee.pryectoBack.service.ControladorPublicacionComentarioLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/publicacionComentario")
@RequestScoped
@Api(value = "/publicacionComentario", description = "Publicaciones y comentarios!")
public class PublicacionComentarioRest {

	@EJB
	private ControladorPublicacionComentarioLocal controladorPublicacionComentario;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comentario")
	@ApiOperation(value = "Agrega un comentario", notes = "Se le pasa el objeto comentario como sigue: {\r\n"
			+ "    \"idComentarioReaccion\": \"Test\","
			+ "    \"contenido\": \"Contenido de prueba 2\","
			+ "    \"fecha\": \"2020-03-10\","
			+ "    \"idPublicacion\": 1,"
			+ "    \"idPersona\": 1,"
			+ "    \"idComentarioPadre\": \"60b2a6ca83ea7a7211e52a01\""
			+ " Si se quiere crear un comentario padre, enviamos idComentarioPadre con null, sino, se lo agregamos para crear un hijo"
			+ "}")
	public Response altaComentario(DTOComentario dtoComentario) {
		Response.ResponseBuilder builder = null;
		try {			
			DTOComentario comentario = controladorPublicacionComentario.altaComentario(dtoComentario);			
			builder = Response.ok();	
			builder.entity(comentario);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		return builder.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comentario/{idComentario}")
	@ApiOperation(value = "Borra un comentario del back", notes = "Se le pasa el id de un comentario")
	public Response bajaComentario(@PathParam("idComentario") String idComentario) {
		Response.ResponseBuilder builder = null;
		try {
			controladorPublicacionComentario.bajaComentario(idComentario);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/publicacion/{idPublicacion}")
	@ApiOperation(value = "Borra una publicacion del back", notes = "Se le pasa el id de una publicacion")
	public Response bajaPublicacion(@PathParam("idPublicacion") int idPublicacion) {
		Response.ResponseBuilder builder = null;
		try {
			controladorPublicacionComentario.bajaPublicacion(idPublicacion);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comentario/modificar")
	@ApiOperation(value = "Modifica un Comentario del back", notes = "Se le pasa {\"contenido\":\"test 3\", \"idComentarioReaccion\": \"id\" }")
	public Response modificarComentario(DTOComentario dtoComentario) {
		Response.ResponseBuilder builder = null;
		try {
			controladorPublicacionComentario.modificarComentario(dtoComentario);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comentario/reaccionar")
	@ApiOperation(value = "Reacciona a una Comentario", notes = "Se le pasa {\"idPersona\":\"test 3\", \"idComentario\": \"adsfasdf\", \"reaccion\" : \"MeGusta\" }")
	public Response reaccionarComentario(DTOReaccion dtoReaccion) {
		Response.ResponseBuilder builder = null;
		try {
			controladorPublicacionComentario.reaccionarComentario(dtoReaccion);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

	public List<DTOPublicacion> obtenerPublicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/publicacion/reaccionar")
	@ApiOperation(value = "Reacciona a una Publicacion", notes = "Se le pasa {\"idPersona\":\"test 3\", \"idPublicacion\": \"1\", \"reaccion\" : \"MeGusta\" }")
	public Response reaccionPublicacion(DTOReaccion dtoReaccion) {
		Response.ResponseBuilder builder = null;
		try {
			controladorPublicacionComentario.reaccionarComentario(dtoReaccion);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Modifica una publicacion del back", notes = "se le pasa el objeto publicacion como sigue: {\"contenido\":\"test 3\",\"tipo\":{\"tipo\":\"mapa\"},\"fecha\":\"\",\"extension\":\"\",\"nombre\":\"\"} . Tambien quedamos que si el tipo es mapa se le pasa las coordenadas en contenido. Si el tipo es foto, se le pasa el nombre del archivo y la extension.")
	public Response modificarPublicacion(DTOPublicacion dtoPublicacion) {
		Response.ResponseBuilder builder = null;
		try {
			controladorPublicacionComentario.modificarPublicacion(dtoPublicacion);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Agrega una publicacion al back", notes = "se le pasa el objeto publicacion como sigue: {\"contenido\":\"test 3\",\"tipo\":{\"tipo\":\"mapa\"},\"extension\":\"\",\"nombre\":\"\",\"perfil\":{\"usuario\":{\"idPersona\":\"idPersona\"}}} no es necesario que se le pasen todos los atributos del objeto. Tambien quedamos que si el tipo es mapa se le pasa las coordenadas en contenido. Si el tipo es foto, se le pasa el nombre del archivo y la extension.")
	public Response altaPublicacion(DTOPublicacion dtoPublicacion) {
		Response.ResponseBuilder builder = null;
		try {
			DTOPublicacion newPublicacion = controladorPublicacionComentario.altaPublicacion(dtoPublicacion);
            builder = Response.ok();
            builder.entity(newPublicacion);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
}
