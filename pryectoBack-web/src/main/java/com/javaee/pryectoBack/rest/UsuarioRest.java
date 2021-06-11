package com.javaee.pryectoBack.rest;

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

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
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
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Modifica un perfil de usuario", notes = "correspondiente al usuario logueado se le pasa el DTOUsuario como sigue: {\r\n" + 
			"    \"idPersona\": \"15\",\r\n" + 
			"    \"nickname\" : \"probandoPut\",\r\n" + 
			"    \"nombre\" : \"Alvaro\",\r\n" + 
			"    \"apellido\" : \"Gutierrez\",\r\n" + 
			"    \"email\" : \"probando@test.com\",\r\n" + 
			"    \"celular\" : \"0999\",\r\n" + 
			"    \"direccion\" : \"En la FING\"\r\n" + 
			"} ")
	@Path("/editarPerfil")
	public Response editarPerfil(DTOUsuario dtoUsuario) {
		Response.ResponseBuilder builder = null;
		try {
			DTOUsuario modified = controladorUsuario.editarPerfil(dtoUsuario);
			if (!modified.getIdPersona().isEmpty()) {
	            builder = Response.ok();
	            builder.entity(modified);
			}
			else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "El interes con id = " + dtoUsuario.getIdPersona() + " no pudo modificarse");
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idUsuario}")
	public Response inicioSesionDatos(@PathParam("idUsuario") String idPersona){

		Response.ResponseBuilder builder = null;

		try{
			DTOUsuarioInicioSesion dtUserInicioSesion = controladorUsuario.datosUsuarioInicioSesion(idPersona);

			if (dtUserInicioSesion != null) {
				builder = Response.ok();
				builder.entity(dtUserInicioSesion);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "El usuario con idUsuario = " + idPersona + " no existe");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
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
			DTOUsuario registrado = controladorUsuario.registrarUsuario(dtoUsuario);
			if (registrado != null){
				builder = Response.ok();
				builder.entity(registrado);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo slaio mal registrando al usuario con id = " + dtoUsuario.getIdPersona() + " intente nuevamente luego de ver la documentacion");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Agregar un nuevo contacto a la lista de contactos con estado pendiente", notes = "")
	@Path("/agregarContacto/{idPersona}/{idPersona2}")
	public Response agregarContacto(@PathParam("idPersona") String idPersona, @PathParam("idPersona2") String idPersona2) {
		Response.ResponseBuilder builder = null;
		try {
			boolean agregado = controladorUsuario.agregarContacto(idPersona, idPersona2);
			if (agregado) {
				builder = Response.ok();
				builder.entity(agregado);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo salio mal al tratr de agregar como contacto al usuario con id = " + idPersona2 + " verficar que el usuario logueado tiene id = " + idPersona);
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
	@ApiOperation(value = "Respuesta a una solicitud anterior que se encuentra como pendiente", notes = "Se le pasa el objeto DTOUsuarioContacto como sigue:  {" + 
			"			+ \"    \"idPersona\": \"1\", //el idPersona es el id del usuario que solicito la amistad" + 
			"			+ \"    \"contactoIdPersona\": \"2\", " + 
			"			+ \"    \"estadoContactos\": \"aceptado o cancelada o borrado o pendiente\" " +
			"			+ \"}")
	@Path("/respuestaContacto")
	public Response respuestaContacto(DTOUsuarioContacto dtoUsuarioContacto) {
		Response.ResponseBuilder builder = null;
		try {
			DTOUsuarioContacto agregado = controladorUsuario.respuestaContacto(dtoUsuarioContacto);
			if (agregado.getIdPersona() != null && agregado.getContactoIdPersona() != null) {
				builder = Response.ok();
				builder.entity(agregado);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "El usuario con id igual a " + dtoUsuarioContacto.getIdPersona() + " no le envio una solicitud previa de amistad al usuario con id igual a " + dtoUsuarioContacto.getContactoIdPersona());
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}

		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Remueve el un contacto de la lista de contactos segun idPersona y contactoIdPersona", notes = "el que corresponda a los ids")
	@Path("/bajaContacto/{idPersona}/{idPersona2}")
	public Response bajaContacto(@PathParam("idPersona") String idPersona, @PathParam("idPersona2") String idPersona2) {
		Response.ResponseBuilder builder = null;
		try {
			boolean baja = controladorUsuario.bajaContacto(idPersona, idPersona2);
			if (baja) {
				builder = Response.ok();
				builder.entity(baja);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo salio mal dando de baja a los contactos con ids: " + idPersona + ", " + idPersona2);
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}

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
	@ApiOperation( value = "Se elimina al Usuario", notes = "")
	@Path("/{idPersona}")
	public Response eliminarCuenta(@PathParam("idPersona") String idPersona) {

		Response.ResponseBuilder builder = null;

		try{
			boolean seElimino = controladorUsuario.eliminarCuenta(idPersona);
			if (seElimino){
				builder = Response.ok();
				builder.entity(seElimino);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo salio mal al eliminar el usuario con id = " + idPersona);
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch (Exception e){
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	public boolean bajaUsuarioAdmin(String idPersona) {
		// TODO Auto-generated method stub
		return false;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modificarAdministrador")
	public Response modificarUsuarioAdmin(DTOAdministrador dtoAdministrador) {
		Response.ResponseBuilder builder = null;
		try {
			DTOAdministrador dtoAdmin = controladorUsuario.modificarUsuarioAdmin(dtoAdministrador);
			if (dtoAdmin != null){
				builder = Response.ok();
				builder.entity(dtoAdmin);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo salio mal al tratr de modificar el usuario administrador con id = " + dtoAdministrador.getIdPersona());
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( value = "Se bloquea al Usuario", notes = "")
	@Path("/{idPersona}")
	public Response bloquearUsuario(@PathParam("idPersona") String idPersona) {
		Response.ResponseBuilder builder = null;
		try{
			boolean fueBloqueado = controladorUsuario.bloquearUsuario(idPersona);
			if (fueBloqueado){
				builder = Response.ok();
				builder.entity(fueBloqueado);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo salio mal al tratr de bloquear al usuario con idPersona = " + idPersona);
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( value = "Se desbloquea al Usuario", notes = "")
	@Path("/desbloquearUsuario/{idPersona}")
	public Response desbloquearUsuario(@PathParam("idPersona") String idPersona) {
		Response.ResponseBuilder builder = null;
		try{
			boolean fueDesbloqueado = controladorUsuario.desbloquearUsuario(idPersona);
			if (fueDesbloqueado){
				builder = Response.ok();
				builder.entity(fueDesbloqueado);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", " algo salio mal al tratar de desbloquar al usuario con id = " + idPersona);
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
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
	@ApiOperation(value = "Registra un nuevo Administrador en la aplicación", notes = "")
	@Path("/altaAdministrador")
	public Response altaUsuarioAdmin(DTOAdministrador dtoAdministrador){
		Response.ResponseBuilder builder = null;
		try {
			DTOAdministrador dtoadmin = controladorUsuario.altaUsuarioAdmin(dtoAdministrador);
			if (dtoadmin != null){
				builder = Response.ok();
				builder.entity(dtoadmin);
			} else {
				Map<String, String> responseObj = new HashMap<>();
				responseObj.put("error", "algo salio mal al tratar de dar de alta al administrador con id = " + dtoAdministrador.getIdPersona());
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
}
