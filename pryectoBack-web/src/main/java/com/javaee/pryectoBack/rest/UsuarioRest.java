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
    notes = "Probando")
    public List<Usuario> getAll() 
	{
        return controladorLocal.getUsuarios();
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Devuelve una usuario",
    notes = "el que corresponda al id de usuario")
    @Path("/{idUsuario}")
	public Usuario get(@PathParam("idUsuario") int idUsuario) throws Exception
	{
		return controladorLocal.getUsuario(idUsuario);
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
