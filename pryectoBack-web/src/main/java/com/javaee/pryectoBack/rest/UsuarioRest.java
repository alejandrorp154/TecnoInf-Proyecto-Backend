package com.javaee.pryectoBack.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.service.ControladorUsuarioLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

@Path("/usuario")
@RequestScoped
@Api(value = "/usuario", description = "Usuarios!")
public class UsuarioRest
{
	@EJB
	private ControladorUsuarioLocal controladorUsuario;
	
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "Devuelve una lista de objetos usuarios",
//    notes = "el offset es la posicion donde empieza (0 por defecto) el size es el tamño de la lista, por ejemplo si la primera consulta es offset=0 y size=10 la segunda consulta va a ser offset=10 y size=10")
//    @Path("/{offset}/{size}")
//    public List<Usuario> getAll(@PathParam("offset") int offset, @PathParam("size") int size) 
//	{
//        return controladorLocal.getUsuarios(offset, size);
//    }
//	
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "Devuelve un usuario",
//    notes = "el que corresponda al id de usuario")
//    @Path("/{idUsuario}")
//	public Response get(@PathParam("idUsuario") String idUsuario) throws Exception
//	{
//		Response.ResponseBuilder builder = null;
//		Usuario usuario = controladorLocal.getUsuario(idUsuario);
//		if (((Persona)usuario).getIdPersona() != null)
//		{
//			builder = Response.ok();
//			builder.entity(usuario);
//		}
//		else
//		{
//            Map<String, String> responseObj = new HashMap<>();
//            responseObj.put("error", "El usuario con id = " + idUsuario + " no existe");
//            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//		}
//		return builder.build();
//	}
//	
//	@POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "Agrega un usuario al back",
//    notes = "se le pasa el objeto usuario como lo devuelve el get obvio que metiendo los datos del nuevo")
//	public Response add(Usuario usuario)
//	{
//		Response.ResponseBuilder builder = null;
//		try {
//        	controladorLocal.addUsuario(usuario);
//            builder = Response.ok();
//        } catch (Exception e) {
//            Map<String, String> responseObj = new HashMap<>();
//            responseObj.put("error", e.getMessage());
//            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//        }
//        return builder.build();
//	}

	public boolean editarPerfil(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idUsuario}")
	public Response inicioSesionDatos(@PathParam("idUsuario") String idPersona){

		Response.ResponseBuilder builder = null;

		try{
			DTOUsuarioInicioSesion dtUserInicioSesion = controladorUsuario.datosUsuarioInicioSesion(idPersona);

			if (dtUserInicioSesion != null) {
				builder = Response.ok();
			}
		}catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Registra un nuevo Usuario en la aplicación", notes = "")
	public Response registrarUsuario(DTOUsuario dtoUsuario) {

		Response.ResponseBuilder builder = null;

		try{
			boolean registrado = controladorLocal.registrarUsuario(dtoUsuario);
			if (registrado){
				builder = Response.ok();
			}

		}catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	public boolean subirFoto(String idPersona, DTOMultimedia dtoMultimedia) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean agregarContacto(String idPersona, String idPersona2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bajaContacto(String idPersona, String idPersona2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean eliminarCuenta(String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bajaUsuarioAdmin(String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modificarUsuarioAdmin(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bloquearUsuario(String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean desbloquearUsuario(String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}
}
