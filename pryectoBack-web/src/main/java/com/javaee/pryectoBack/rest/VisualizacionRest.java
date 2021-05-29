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
		if (perfil != null && perfil.getUsuario() != null && perfil.getUsuario().getIdPersona() != null)
		{
			builder = Response.ok();
			builder.entity(perfil);
		}
		else
		{
          Map<String, String> responseObj = new HashMap<>();
          responseObj.put("error", "El perfil con id = " + idPersona + " no existe");
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	public List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerUsuarios(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public DTOUsuarioMedalla visualizarProgreso(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerCantidadUsuariosEnElSistema(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerCantidadUsuariosSegunPais(String pais, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuarioMedalla> obtenerCantidadUsuariosSegunMedallas(int idMedalla, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
