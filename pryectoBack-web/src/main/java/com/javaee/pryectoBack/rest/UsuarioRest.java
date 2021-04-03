package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.service.ControladorLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/usuario")
@RequestScoped
@Api(value = "/usuario", description = "Usuarios!")
public class UsuarioRest
{
	@EJB
	private ControladorLocal controladorLocal;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Devuelve una lista de objetos usuarios",
    notes = "el offset es la posicion donde empieza (0 por defecto) el size es el tamño de la lista, por ejemplo si la primera consulta es offset=0 y size=10 la segunda consulta va a ser offset=10 y size=10")
    @Path("/{offset}/{size}")
    public List<Usuario> getAll(@PathParam("offset") int offset, @PathParam("size") int size) 
	{
        return controladorLocal.getUsuarios(offset, size);
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Devuelve un usuario",
    notes = "el que corresponda al id de usuario")
    @Path("/{idUsuario}")
	public Response get(@PathParam("idUsuario") int idUsuario) throws Exception
	{
		Response.ResponseBuilder builder = null;
		Usuario usuario = controladorLocal.getUsuario(idUsuario);
		if (usuario.getIdUsuario() != 0)
		{
			builder = Response.ok();
			builder.entity(usuario);
		}
		else
		{
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "El usuario con id = " + idUsuario + " no existe");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Agrega un usuario al back",
    notes = "se le pasa el objeto usuario como lo devuelve el get obvio que metiendo los datos del nuevo")
	public Response add(Usuario usuario)
	{
		Response.ResponseBuilder builder = null;
		try {
        	controladorLocal.addUsuario(usuario);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}

}
