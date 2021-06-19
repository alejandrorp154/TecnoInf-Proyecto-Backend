package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;
import com.javaee.pryectoBack.service.ControladorConfigSistemaLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/configSistema")
@RequestScoped
@Api(value = "/configSistema", description = "ConfigSistema!")
public class ConfigSistemaRest {
	
	@EJB
	private ControladorConfigSistemaLocal controladorConfigSistema;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Modifica un las configuraciones para el usuario logueado", notes = "")
	public Response configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {

		Response.ResponseBuilder builder = null;
		try {
			DTOConfiguracion modified = controladorConfigSistema.configurarNotificaciones(dtoConfiguracion);
			if (modified.getIdConfiguracion() != 0) {
	            builder = Response.ok();
	            builder.entity(modified);
			}
			else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "Las configuraciones para el usuario con id = " + dtoConfiguracion.getIdPersona() + " no pudo modificarse");
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
	@ApiOperation(value = "Devuelve las configuraciones de un usuario", notes = "el que corresponda al id de persona")
	@Path("/{idPersona}")
	public Response get(@PathParam("idPersona") String idPersona) throws Exception
	{
		Response.ResponseBuilder builder = null;
		DTOConfiguracion configuracion = controladorConfigSistema.getByIdPersona(idPersona);
		if (configuracion.getIdConfiguracion() != 0)
		{
			builder = Response.ok();
			builder.entity(configuracion);
		}
		else
		{
          Map<String, String> responseObj = new HashMap<>();
          responseObj.put("error", "La configuracion para el usuario con id = " + idPersona + " no existe");
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
}
