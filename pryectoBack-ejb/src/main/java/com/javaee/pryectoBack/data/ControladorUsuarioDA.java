package com.javaee.pryectoBack.data;

import java.util.Date;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.datatypes.DTOUsuarioPerfil;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.model.Medalla;
import com.javaee.pryectoBack.model.Notificacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.EventoUsuario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Multimedia;
import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.Administrador;
import com.javaee.pryectoBack.model.Configuracion;
import com.javaee.pryectoBack.util.DbManager;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.UsuarioContacto;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.util.PuntosUsuario;


@Singleton
public class ControladorUsuarioDA implements ControladorUsuarioDALocal, ControladorUsuarioDARemote {

	private PuntosUsuario puntoUsuario;
	
	public ControladorUsuarioDA()
	{
		puntoUsuario = new PuntosUsuario();
	}

	@Override
	public DTOUsuario editarPerfil(DTOUsuarioPerfil dtoUsuario) {
		DTOUsuario dtoUsuarioRes = new DTOUsuario();
		try{
			DbManager.open();
			Usuario usuario = DbManager.find(Usuario.class, dtoUsuario.getIdPersona());
			if (usuario != null) {
				usuario.setNickname(dtoUsuario.getNickname());
				usuario.setDireccion(dtoUsuario.getDireccion());
				usuario.setCelular(dtoUsuario.getCelular());
				usuario.setNombre(dtoUsuario.getNombre());
				usuario.setApellido(dtoUsuario.getApellido());
				usuario.setEmail(dtoUsuario.getEmail());
				usuario.setPais(dtoUsuario.getPais());
				usuario.getPerfil().setImagenPerfil(dtoUsuario.getImagenPerfil());
				usuario.getPerfil().setExtension(dtoUsuario.getExtensionImagen());
				usuario.getPerfil().setNombreImagen(dtoUsuario.getNombreImagen());
				DbManager.merge(usuario);
				dtoUsuarioRes = new DTOUsuario(usuario);
			}
		}catch (Exception exception) {
			return dtoUsuarioRes;
		}
		return dtoUsuarioRes;
	}

	@Override
	public DTOUsuario registrarUsuario(DTOUsuario dtoUsuario) {
		DTOUsuario res = new DTOUsuario();
		try{
			DbManager.open();
			Usuario usuario = DbManager.find(Usuario.class, dtoUsuario.getIdPersona());
			if (usuario == null) {
				Usuario user = new Usuario(dtoUsuario);
				user.getMedalla().setUsuario(user);
				PerfilUsuario perfil = new PerfilUsuario(user, dtoUsuario);
				user.setPerfil(perfil);
				DbManager.merge(user);
				Configuracion configuracionEmail = new Configuracion(true);
				configuracionEmail.setIdPersona(user.getIdPersona());
				DbManager.persist(configuracionEmail);
				Configuracion configuracionPush = new Configuracion(false);
				configuracionPush.setIdPersona(user.getIdPersona());
				DbManager.persist(configuracionPush);
				res = new DTOUsuario(user);
			} else {
				res = dtoUsuario;
			}
		}catch (Exception exception) {
			return null;
		}
		return res;
	}

	@Override
	public boolean subirFoto(DTOMultimedia dtoMultimedia) {
		boolean res = false;
		try {
			DbManager.open();
			PerfilUsuario perfil = DbManager.find(PerfilUsuario.class, dtoMultimedia.getIdPersona());
			if (perfil != null) {
				Multimedia multimedia = new Multimedia(dtoMultimedia, perfil);
				DbManager.merge(multimedia);
				Usuario usuario1 = DbManager.find(Usuario.class, perfil.getIdPersona());
				DTOUsuario dtoUsuario1 = new DTOUsuario(usuario1);
				puntoUsuario.getPuntosUsuario("SubirFotoGaleria", dtoUsuario1);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public boolean agregarContacto(String idPersona, String idPersona2) {
		try{
			DbManager.open();
			Persona user1 = DbManager.find(Usuario.class, idPersona);
			Persona user2 = DbManager.find(Usuario.class, idPersona2);
			if (user1 != null && user2 != null) {
				UsuarioContacto usuarioContacto = new UsuarioContacto();
				usuarioContacto.setIdPersona(idPersona);
				usuarioContacto.setContactoIdPersona(idPersona2);
				usuarioContacto.setFechaContactos(new Date());
				usuarioContacto.setEstadoContactos(estadosContactos.pendiente);
				DbManager.persist(usuarioContacto);
				return true;
			}
			return false;
		}catch (Exception exception) {
			return false;
		}
	}

	@Override
	public boolean bajaContacto(String idPersona, String idPersona2) {
		boolean res = false;
		try {
			DbManager.open();
			UsuarioContacto usuarioContacto1 = DbManager.find(UsuarioContacto.class, idPersona, idPersona2);
			UsuarioContacto usuarioContacto2 = DbManager.find(UsuarioContacto.class, idPersona2, idPersona);
			if (usuarioContacto1 != null && usuarioContacto2 != null) {
				DbManager.remove(usuarioContacto1);
				DbManager.remove(usuarioContacto2);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarCuenta(String idPersona) {

		try	{
			DbManager.open();
			Usuario user = DbManager.find(Usuario.class,idPersona);

			if (user != null) {

				//Medallas
				Medalla medalla = user.getMedalla();
				DbManager.remove(medalla);
				
				// Remover configuraciones
				List<Configuracion> configuraciones = DbManager.createQuery("SELECT configuracion FROM Configuracion configuracion where configuracion.idPersona = '" + idPersona + "'");
				if (configuraciones.size() > 0) {
					for (Configuracion configuracion : configuraciones) {
						DbManager.remove(configuracion);
					}	
				}

				//Notificaciones
				List<Notificacion> notificaciones = user.getNotificaciones();
				for (Notificacion noti : notificaciones) {
					DbManager.remove(noti);
				}

				//Ubicaciones
				List<Ubicacion> ubicaciones = user.getUbicaciones();
				for (Ubicacion ubic : ubicaciones) {
					DbManager.remove(ubic);
				}

				//Eventos
				//Creados por el usuario
				List<Evento> eventosCreadosPorUsuario = user.getCreadorDeEventos();
				if (!eventosCreadosPorUsuario.isEmpty()){
					for (Evento event : eventosCreadosPorUsuario) {

						List<EventoUsuario> eventosUsuarios = DbManager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + event.getIdEvento() + "'");
						//Dessasigna a Usuarios que asistiran
						for (EventoUsuario eventoUsuario : eventosUsuarios) {
							DbManager.remove(eventoUsuario);
						}

						//Desasigno las publicaciones del evento borrando sus comentarios
						List<Publicacion> publicaciones = event.getPublicaciones();
						if (!publicaciones.isEmpty()) {
							for (Publicacion pub : publicaciones) {
								MongoDBConnector mongoConnector = new MongoDBConnector();
								MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");

								String idPublicacion = String.valueOf(pub.getIdPublicacion());
								collection.deleteOne(eq("idPublicacion", new ObjectId(idPublicacion)));

								pub.getPerfil().getPublicaciones().remove(pub);

								DbManager.remove(pub);
							}
						}
						DbManager.remove(event);
					}
				}

				List<EventoUsuario> eventosUsuarios = DbManager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idPersona = '" + user.getIdPersona() + "'");
				//Eventos a los que asistira el usuario
				for (EventoUsuario eventoUsuario : eventosUsuarios) {
					DbManager.remove(eventoUsuario);
				}

				//Remove Multimedia
				PerfilUsuario userProfile = user.getPerfil();
				List<Multimedia> galerias = userProfile.getGalerias();
				if (!galerias.isEmpty()){
					for (Multimedia multi : galerias){
						DbManager.remove(multi);
					}
				}

				//Publicaciones del Usuario
				List<Publicacion> pubs = userProfile.getPublicaciones();
				if (!pubs.isEmpty()){
					for (Publicacion publicacion : pubs){

						MongoDBConnector mongoConnector = new MongoDBConnector();
						MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");

						String idPublicacion = String.valueOf(publicacion.getIdPublicacion());
						collection.deleteOne(eq("idPublicacion", new ObjectId(idPublicacion)));

						DbManager.remove(publicacion);
					}
				}

				//Intereses del usuario
				List<Interes> intereses = userProfile.getIntereses();
				if (!intereses.isEmpty()){
					for (Interes inter : intereses){
						inter.getPerfiles().remove(userProfile);
					}
				}

				//Perfil
				DbManager.remove(userProfile);

				//Remueve Usuario
				DbManager.remove(user);

				return true;
			}
		} catch (Exception exception){
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean bajaUsuarioAdmin(String idPersona) {
		boolean res = false;
		try {
			DbManager.open();
			Administrador admin = DbManager.find(Administrador.class, idPersona);
			if (admin != null) {
				List<Administrador> administradores = DbManager.createQuery("SELECT administrador FROM Administrador administrador ");
				if (administradores.size() > 1) {
					DbManager.remove(admin);
					res = true;
				}
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public DTOAdministrador modificarUsuarioAdmin(DTOAdministrador dtoAdministrador) {
		try{
			DbManager.open();
			Administrador admin = DbManager.find(Administrador.class,dtoAdministrador.getIdPersona());
			admin.setIdPersona(dtoAdministrador.getIdPersona());
			admin.setEmail(dtoAdministrador.getEmail());
			admin.setNombre(dtoAdministrador.getNombre());
			admin.setApellido(dtoAdministrador.getApellido());
			DbManager.merge(admin);
			return new DTOAdministrador(admin.getIdPersona(),admin.getEmail(), admin.getNombre(),admin.getApellido());
		}catch (Exception exception){
			return null;
		}
	}

	@Override
	public boolean bloquearUsuario(String idPersona) {
		boolean quedoBloqueado = false;
		try {
			DbManager.open();
			Usuario user = DbManager.find(Usuario.class, idPersona);
			if (user != null) {
				boolean estaBloqueado = user.getEstaBloqueado();
				if (!estaBloqueado) {
					user.setEstaBloqueado(true);
					DbManager.persist(user);
				}
				quedoBloqueado = true;
			}
			return quedoBloqueado;
		}catch (Exception exception) {
			return false;
		}
	}

	@Override
	public boolean desbloquearUsuario(String idPersona) {
		boolean fueDesbloqueado = false;
		try{
			DbManager.open();
			Usuario user = DbManager.find(Usuario.class, idPersona);
			if (user != null){
				user.setEstaBloqueado(false);
				DbManager.merge(user);
				fueDesbloqueado = true;
			}
			return fueDesbloqueado;
		}catch (Exception exception){
			return false;
		}
	}

	@Override
	public DTOUsuarioInicioSesion datosUsuarioInicioSesion(String idPersona) {

		DbManager.open();
		Persona persona = DbManager.find(Persona.class, idPersona);

		if (persona != null){
			if (persona instanceof Usuario) {
				Usuario user = (Usuario)persona;
				String imagen = user.getPerfil().getImagenPerfil();
				String extension = user.getPerfil().getExtension();
				String nombreImagen = user.getPerfil().getNombreImagen();
				return new DTOUsuarioInicioSesion(user.getIdPersona(), user.getEmail(), user.getNombre(), user.getApellido(), user.getNickname(), imagen, extension, nombreImagen, false, user.getEstaBloqueado());
			} else {
				Administrador admin = (Administrador)persona;
				return new DTOUsuarioInicioSesion(admin.getIdPersona(), admin.getEmail(), admin.getNombre(), admin.getApellido(), null, null, null, null, true, false);
			}
		}
		return null;
	}

	@Override
	public DTOUsuarioContacto respuestaContacto(DTOUsuarioContacto dtoUsuarioContacto) {
		DTOUsuarioContacto dtoUsuarioContactoRes = new DTOUsuarioContacto();
		try {
			DbManager.open();
			UsuarioContacto usuarioContacto1 = DbManager.find(UsuarioContacto.class, dtoUsuarioContacto.getIdPersona(), dtoUsuarioContacto.getContactoIdPersona());
			if (usuarioContacto1 != null) {
				Date dateUpdated = new Date();
				usuarioContacto1.setEstadoContactos(dtoUsuarioContacto.getEstadoContactos());
				UsuarioContacto usuarioContacto2 = new UsuarioContacto();
				usuarioContacto2.setIdPersona(dtoUsuarioContacto.getContactoIdPersona());
				usuarioContacto2.setContactoIdPersona(dtoUsuarioContacto.getIdPersona());
				usuarioContacto2.setFechaContactos(dateUpdated);
				usuarioContacto2.setEstadoContactos(dtoUsuarioContacto.getEstadoContactos());
				usuarioContacto1.setFechaContactos(dateUpdated);
				DbManager.merge(usuarioContacto1);
				DbManager.persist(usuarioContacto2);
				dtoUsuarioContactoRes = new DTOUsuarioContacto(usuarioContacto1);
				Usuario usuario1 = DbManager.find(Usuario.class, usuarioContacto1.getIdPersona());
				DTOUsuario dtoUsuario1 = new DTOUsuario(usuario1);
				puntoUsuario.getPuntosUsuario("AltaContacto", dtoUsuario1);
			}
		} catch (Exception exception) {
			return dtoUsuarioContactoRes;
		}
		return dtoUsuarioContactoRes;
	}

	@Override
	public DTOAdministrador altaUsuarioAdmin(DTOAdministrador dtoAdministrador){
		try{
			DbManager.open();
			Administrador admin = new Administrador(dtoAdministrador);
			DbManager.persist(admin);
			return new DTOAdministrador(admin.getIdPersona(),admin.getEmail(), admin.getNombre(), admin.getApellido());

		}catch ( Exception exception){
			return null;
		}
	}

	@Override
	public DTOUsuarioPerfil getPerfil(String idPersona) {
		DTOUsuarioPerfil dtoUsuarioPerfil = new DTOUsuarioPerfil();
		try {
			DbManager.open();
			Usuario usuario = DbManager.find(Usuario.class, idPersona);
			if (usuario != null) {
				dtoUsuarioPerfil = new DTOUsuarioPerfil(usuario);
			}
		} catch (Exception exception) {
			return dtoUsuarioPerfil;
		}
		return dtoUsuarioPerfil;
	}
}
