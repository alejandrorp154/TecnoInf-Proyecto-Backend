package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOPublicacion;
import com.javaee.pryectoBack.datatypes.DTOReaccion;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.service.ControladorPublicacionComentarioLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/publicacionComentario")
@RequestScoped
@Api(value = "/publicacionComentario", description = "Publicaciones y comentarios!")
public class PublicacionComentarioRest {

	@EJB
	private ControladorPublicacionComentarioLocal controladorPublicacionComentario;
	
	public boolean altaComentario(DTOComentario dtoComentario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bajaComentario(int idComentario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modificarComentario(DTOComentario dtoComentario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean reaccionarComentario(int idComentario, DTOReaccion dtoReaccion) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<DTOPublicacion> obtenerPublicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modificarPublicacion(DTOPublicacion dtoPublicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean reaccionPublicacion(DTOReaccion dtoReaccion) {
		// TODO Auto-generated method stub
		return false;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Agrega una publicacion al back", notes = "se le pasa el data type publicacion como lo devuelve el get obvio que metiendo los datos del nuevo")
	public Response altaPublicacion(Publicacion publicacion) {
		Response.ResponseBuilder builder = null;
		try {
			DTOPublicacion dtoPublicacion = new DTOPublicacion(publicacion);
			DTOPublicacion newPublicacion = controladorPublicacionComentario.altaPublicacion(dtoPublicacion);
			Publicacion publi = new Publicacion(newPublicacion);
            builder = Response.ok();
            builder.entity(publi);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
}
