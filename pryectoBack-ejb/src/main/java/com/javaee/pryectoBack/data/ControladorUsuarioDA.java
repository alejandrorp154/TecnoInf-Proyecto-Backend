package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
import com.javaee.pryectoBack.datatypes.DTOComentario;
import com.javaee.pryectoBack.datatypes.DTOConfiguracion;
import com.javaee.pryectoBack.datatypes.DTOInteresUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.datatypes.DTOUsuarioPerfil;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.EventoUsuario;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Multimedia;
import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.Administrador;
import com.javaee.pryectoBack.model.Configuracion;
import com.javaee.pryectoBack.util.EnviarNotificacion;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.UsuarioContacto;
import com.javaee.pryectoBack.model.UsuarioContactoId;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.model.reacciones;
import com.javaee.pryectoBack.util.PuntosUsuario;


@Stateless
public class ControladorUsuarioDA implements ControladorUsuarioDALocal, ControladorUsuarioDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	private PuntosUsuario puntoUsuario;
	private EnviarNotificacion enviarNotificacion;
	private MongoDBConnector mongoConnector;
	
	public ControladorUsuarioDA()
	{
		puntoUsuario = new PuntosUsuario();
		enviarNotificacion = new EnviarNotificacion();
	}

	@Override
	public boolean sonAmigos(String idPersona, String idContacto)
	{
		boolean res = false;
		try {
			UsuarioContacto usuarioContacto = manager.find(UsuarioContacto.class, new UsuarioContactoId(idPersona, idContacto));
			if (usuarioContacto != null) {
				res = usuarioContacto.getEstadoContactos().equals(estadosContactos.pendiente) ? true : false;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
	
	@Override
	public DTOUsuario editarPerfil(DTOUsuarioPerfil dtoUsuario) {
		DTOUsuario dtoUsuarioRes = new DTOUsuario();
		try{
			Usuario usuario = manager.find(Usuario.class, dtoUsuario.getIdPersona());
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
				manager.merge(usuario);
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
			Usuario usuario = manager.find(Usuario.class, dtoUsuario.getIdPersona());
			if (usuario == null) {
				Usuario user = new Usuario(dtoUsuario);
				user.getMedalla().setUsuario(user);
				PerfilUsuario perfil = new PerfilUsuario(user, dtoUsuario);
				user.setPerfil(perfil);
				manager.merge(user);
				Configuracion configuracionEmail = new Configuracion(true);
				configuracionEmail.setIdPersona(user.getIdPersona());
				manager.persist(configuracionEmail);
				Configuracion configuracionPush = new Configuracion(false);
				configuracionPush.setIdPersona(user.getIdPersona());
				manager.persist(configuracionPush);
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
			PerfilUsuario perfil = manager.find(PerfilUsuario.class, dtoMultimedia.getIdPersona());
			if (perfil != null) {
				Multimedia multimedia = new Multimedia(dtoMultimedia, perfil);
				manager.merge(multimedia);
				Usuario usuario1 = manager.find(Usuario.class, perfil.getIdPersona());
				DTOUsuario dtoUsuario1 = new DTOUsuario(usuario1);
				puntoUsuario.getPuntosUsuario("SubirFotoGaleria", dtoUsuario1, manager);
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
			Persona user1 = manager.find(Usuario.class, idPersona);
			Persona user2 = manager.find(Usuario.class, idPersona2);
			if (user1 != null && user2 != null) {
				UsuarioContacto usuarioContacto1 = manager.find(UsuarioContacto.class, new UsuarioContactoId(idPersona, idPersona2));
				if (usuarioContacto1 == null) {
					UsuarioContacto usuarioContacto = new UsuarioContacto();
					usuarioContacto.setIdPersona(idPersona);
					usuarioContacto.setContactoIdPersona(idPersona2);
					usuarioContacto.setFechaContactos(new Date());
					usuarioContacto.setEstadoContactos(estadosContactos.pendiente);
					manager.persist(usuarioContacto);
					String nickname = getNicknameById(idPersona);
					String mensaje = "Ha recibido una nueva solicitud del usuario " + nickname + ".";
					String titulo = "Nueva solicitud";
					enviarNotificacion(idPersona2, mensaje, titulo, "agregarContacto");
					return true;
				}
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
			UsuarioContacto usuarioContacto1 = manager.find(UsuarioContacto.class, new UsuarioContactoId(idPersona, idPersona2));
			UsuarioContacto usuarioContacto2 = manager.find(UsuarioContacto.class, new UsuarioContactoId(idPersona2, idPersona));
			if (usuarioContacto1 != null && usuarioContacto2 != null) {
				manager.remove(usuarioContacto1);
				manager.remove(usuarioContacto2);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public boolean eliminarCuenta(String idPersona) {

		try	{
			Usuario user = manager.find(Usuario.class,idPersona);

			if (user != null) {

//				//Medallas
//				Medalla medalla = user.getMedalla();
//				manager.remove(medalla);
//				
				// Remover configuraciones
				TypedQuery<Configuracion> queryConfiguraciones = manager.createQuery("SELECT configuracion FROM Configuracion configuracion where configuracion.idPersona = '" + idPersona + "'", Configuracion.class);
				List<Configuracion> configuraciones =  queryConfiguraciones.getResultList();
				if (configuraciones.size() > 0) {
					for (Configuracion configuracion : configuraciones) {
						manager.remove(configuracion);
					}	
				}

//				//Notificaciones
//				List<Notificacion> notificaciones = user.getNotificaciones();
//				for (Notificacion noti : notificaciones) {
//					manager.remove(noti);
//				}

				//Ubicaciones
				List<Ubicacion> ubicaciones = user.getUbicaciones();
				for (Ubicacion ubic : ubicaciones) {
					manager.remove(ubic);
				}

				//Eventos
				//Creados por el usuario
				List<Evento> eventosCreadosPorUsuario = user.getCreadorDeEventos();
				if (!eventosCreadosPorUsuario.isEmpty()){
					for (Evento event : eventosCreadosPorUsuario) {

						TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idEvento = '" + event.getIdEvento() + "'", EventoUsuario.class);
						List<EventoUsuario> eventosUsuarios = query.getResultList();
						//Dessasigna a Usuarios que asistiran
						for (EventoUsuario eventoUsuario : eventosUsuarios) {
							manager.remove(eventoUsuario);
						}

						//Desasigno las publicaciones del evento borrando sus comentarios
						List<Publicacion> publicaciones = event.getPublicaciones();
						if (!publicaciones.isEmpty()) {
							for (Publicacion pub : publicaciones) {
								mongoConnector = new MongoDBConnector();
//								MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");
								// Obtengo lista de comentarios
								List<DTOComentario> comentarios = getComentarios(pub.getIdPublicacion());
								for (DTOComentario dtoComentario : comentarios) {
									bajaComentario(dtoComentario.getIdComentario());
								}

								pub.getPerfil().getPublicaciones().remove(pub);

								manager.remove(pub);
							}
						}
						manager.remove(event);
					}
				}				
				
				//Remove Multimedia
				PerfilUsuario userProfile = user.getPerfil();
				List<Multimedia> galerias = userProfile.getGalerias();
				if (!galerias.isEmpty()){
					for (Multimedia multi : galerias){
						manager.remove(multi);
					}
				}
				//Publicaciones del Usuario
				List<Publicacion> pubs = userProfile.getPublicaciones();
				if (!pubs.isEmpty()){
					for (Publicacion publicacion : pubs){

						mongoConnector = new MongoDBConnector();
//						MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");
						// Obtengo lista de comentarios
						List<DTOComentario> comentarios = getComentarios(publicacion.getIdPublicacion());
						for (DTOComentario dtoComentario : comentarios) {
							bajaComentario(dtoComentario.getIdComentario());
						}

						manager.remove(publicacion);
					}
				}

				//Intereses del usuario
				List<Interes> intereses = userProfile.getIntereses();
				if (!intereses.isEmpty()){
					for (Interes inter : intereses){
						inter.getPerfiles().remove(userProfile);
					}
				}

				TypedQuery<EventoUsuario> query = manager.createQuery("SELECT eventoUsuario FROM EventoUsuario eventoUsuario where eventoUsuario.idPersona = '" + user.getIdPersona() + "'", EventoUsuario.class);
				List<EventoUsuario> eventosUsuarios = query.getResultList();
				//Eventos a los que asistira el usuario
				for (EventoUsuario eventoUsuario : eventosUsuarios) {
					manager.remove(eventoUsuario);
				}
				
				TypedQuery<UsuarioContacto> queryUsuarioContacto = manager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.idPersona = :idPersona ", UsuarioContacto.class);
				List<UsuarioContacto> usuariosContactos = queryUsuarioContacto.setParameter("idPersona", idPersona).getResultList();
				for (UsuarioContacto usuarioContacto : usuariosContactos) {
					manager.remove(usuarioContacto);
				}
				
				TypedQuery<UsuarioContacto> queryUsuarioContacto2 = manager.createQuery("SELECT usuariocontacto FROM UsuarioContacto usuariocontacto where usuariocontacto.contactoIdPersona = :contactoIdPersona ", UsuarioContacto.class);
				List<UsuarioContacto> usuariosContactos2 = queryUsuarioContacto2.setParameter("contactoIdPersona", idPersona).getResultList();
				for (UsuarioContacto usuarioContacto2 : usuariosContactos2) {
					manager.remove(usuarioContacto2);
				}

				//Perfil
				manager.remove(userProfile);

				//Remueve Usuario
				manager.remove(user);

				return true;
			}
		} catch (Exception exception){
			return false;
		}
		return false;
	}
	
	private boolean bajaComentario(String idComentario) {
		try {
			if (mongoConnector == null || mongoConnector.getClient() == null || mongoConnector.getDataBase() == null) {
				mongoConnector = new MongoDBConnector();
			}
			MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosPublicacion");
			collection.deleteMany(eq("_id", new ObjectId(idComentario)));
			collection = mongoConnector.getCollection("ReaccionesComentario");
			collection.deleteMany(eq("idComentario", new ObjectId(idComentario)));
			return true;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return false;
		}
	}
	
	private List<DTOComentario> getComentarios(int idPublicacion) {
		try {
			List<DTOComentario> result = new ArrayList<DTOComentario>();
			if (mongoConnector == null || mongoConnector.getClient() == null || mongoConnector.getDataBase() == null) {
				mongoConnector = new MongoDBConnector();
			}
			MongoCollection<Document> collectionComentariosPublicacion = mongoConnector
					.getCollection("ComentariosPublicacion");
			MongoCollection<Document> reaccionesComentarios = mongoConnector.getCollection("ReaccionesComentario");
			FindIterable<Document> comentariosPadre = collectionComentariosPublicacion
					.find(and(eq("idPublicacion", idPublicacion), eq("idComentarioPadre", null)));
			for (Document comentario : comentariosPadre) {
				result.add(getArbolComentario(comentario, collectionComentariosPublicacion, reaccionesComentarios));
			}
			return result;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return null;

		}
	}

	private DTOComentario getArbolComentario(Document comentarioPadre,
			MongoCollection<Document> collectionComentariosPublicacion,
			MongoCollection<Document> reaccionesComentarios) {
		DTOComentario comentarioPadreDTO = new DTOComentario(comentarioPadre);
		FindIterable<Document> comentariosHijo = collectionComentariosPublicacion
				.find(eq("idComentarioPadre", comentarioPadreDTO.getIdComentario()));
		comentarioPadreDTO
				.setCantidadLikes(getCantidadReaccion(reacciones.MeGusta, comentarioPadre, reaccionesComentarios));
		comentarioPadreDTO
				.setCantidadDislikes(getCantidadReaccion(reacciones.NoMeGusta, comentarioPadre, reaccionesComentarios));
		if (comentariosHijo != null) {
			for (Document comentario : comentariosHijo) {
				DTOComentario hijo = new DTOComentario(comentario);
				hijo.setCantidadLikes(getCantidadReaccion(reacciones.MeGusta, comentario, reaccionesComentarios));
				hijo.setCantidadDislikes(getCantidadReaccion(reacciones.NoMeGusta, comentario, reaccionesComentarios));
				comentarioPadreDTO.getComentariosHijos().add(hijo);
			}
		}
		return comentarioPadreDTO;
	}	
	
	@SuppressWarnings("unused")
	private Integer getCantidadReaccion(reacciones reaccion, Document comentario,
			MongoCollection<Document> collection) {
		Integer cantidad = 0;
		FindIterable<Document> reacciones = collection.find(and(
				eq("idComentario", String.valueOf(comentario.get("_id"))), eq("reaccion", String.valueOf(reaccion))));
		for (Document document : reacciones) {
			cantidad++;
		}
		return cantidad;
	}


	@Override
	public boolean bajaUsuarioAdmin(String idPersona) {
		boolean res = false;
		try {
			Administrador admin = manager.find(Administrador.class, idPersona);
			if (admin != null) {
				TypedQuery<Administrador> query = manager.createQuery("SELECT administrador FROM Administrador administrador ", Administrador.class);
				List<Administrador> administradores =  query.getResultList();
				if (administradores.size() > 1) {
					manager.remove(admin);
					res = true;
					String mensaje = "Su usario administrador ha sido removido del sistema.";
					String titulo = "Usuario removido";
					enviarNotificacionBajaUsuarioAdmin(admin.getEmail(), mensaje, titulo);
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
			Administrador admin = manager.find(Administrador.class,dtoAdministrador.getIdPersona());
			admin.setIdPersona(dtoAdministrador.getIdPersona());
			admin.setEmail(dtoAdministrador.getEmail());
			admin.setNombre(dtoAdministrador.getNombre());
			admin.setApellido(dtoAdministrador.getApellido());
			manager.merge(admin);
			return new DTOAdministrador(admin.getIdPersona(),admin.getEmail(), admin.getNombre(),admin.getApellido());
		}catch (Exception exception){
			return null;
		}
	}

	@Override
	public boolean bloquearUsuario(String idPersona) {
		boolean quedoBloqueado = false;
		try {
			Usuario user = manager.find(Usuario.class, idPersona);
			if (user != null) {
				boolean estaBloqueado = user.getEstaBloqueado();
				if (!estaBloqueado) {
					user.setEstaBloqueado(true);
					manager.persist(user);
					String mensaje = "Su usario ha sido bloqueado en el sistema por un administrador.";
					String titulo = "Usuario bloqueado";
					enviarNotificacion(idPersona, mensaje, titulo, "bloquearUsuario");
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
			Usuario user = manager.find(Usuario.class, idPersona);
			if (user != null){
				user.setEstaBloqueado(false);
				manager.merge(user);
				fueDesbloqueado = true;
				String mensaje = "Su usario ha sido desbloqueado en el sistema por un administrador.";
				String titulo = "Usuario desbloqueado";
				enviarNotificacion(idPersona, mensaje, titulo, "desbloquearUsuario");
			}
			return fueDesbloqueado;
		}catch (Exception exception){
			return false;
		}
	}

	@Override
	public DTOUsuarioInicioSesion datosUsuarioInicioSesion(String idPersona) {

		Persona persona = manager.find(Persona.class, idPersona);

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
			UsuarioContacto usuarioContacto1 = manager.find(UsuarioContacto.class, new UsuarioContactoId(dtoUsuarioContacto.getIdPersona(), dtoUsuarioContacto.getContactoIdPersona()));
			if (usuarioContacto1 != null) {
				Date dateUpdated = new Date();
				usuarioContacto1.setEstadoContactos(dtoUsuarioContacto.getEstadoContactos());
				UsuarioContacto usuarioContacto2 = new UsuarioContacto();
				usuarioContacto2.setIdPersona(dtoUsuarioContacto.getContactoIdPersona());
				usuarioContacto2.setContactoIdPersona(dtoUsuarioContacto.getIdPersona());
				usuarioContacto2.setFechaContactos(dateUpdated);
				usuarioContacto2.setEstadoContactos(dtoUsuarioContacto.getEstadoContactos());
				usuarioContacto1.setFechaContactos(dateUpdated);
				manager.merge(usuarioContacto1);
				manager.persist(usuarioContacto2);
				dtoUsuarioContactoRes = new DTOUsuarioContacto(usuarioContacto1);
				Usuario usuario1 = manager.find(Usuario.class, usuarioContacto1.getIdPersona());
				DTOUsuario dtoUsuario1 = new DTOUsuario(usuario1);
				puntoUsuario.getPuntosUsuario("AltaContacto", dtoUsuario1, manager);
			}
		} catch (Exception exception) {
			return dtoUsuarioContactoRes;
		}
		return dtoUsuarioContactoRes;
	}

	@Override
	public DTOAdministrador altaUsuarioAdmin(DTOAdministrador dtoAdministrador){
		try{
			Administrador admin = new Administrador(dtoAdministrador);
			manager.persist(admin);
			return new DTOAdministrador(admin.getIdPersona(),admin.getEmail(), admin.getNombre(), admin.getApellido());

		}catch ( Exception exception){
			return null;
		}
	}

	@Override
	public DTOUsuarioPerfil getPerfil(String idPersona) {
		DTOUsuarioPerfil dtoUsuarioPerfil = new DTOUsuarioPerfil();
		try {
			Usuario usuario = manager.find(Usuario.class, idPersona);
			if (usuario != null) {
				dtoUsuarioPerfil = new DTOUsuarioPerfil(usuario);
			}
		} catch (Exception exception) {
			return dtoUsuarioPerfil;
		}
		return dtoUsuarioPerfil;
	}

	@Override
	public List<DTOInteresUsuario> getInteresesUsuario(String idPersona) {
		List<DTOInteresUsuario> res = new ArrayList<DTOInteresUsuario>();
		try {
			PerfilUsuario perfil = manager.find(PerfilUsuario.class, idPersona);
			if (perfil != null) {
				List<Interes> interesesUsuario = perfil.getIntereses();
				TypedQuery<Interes> query = manager.createQuery("SELECT interes FROM Interes interes order by interes.idInteres", Interes.class);
				List<Interes> intereses = query.getResultList();
				for (Interes interes : intereses) {
					DTOInteresUsuario dtoInteresUsuario = new DTOInteresUsuario(interes);
					for (Interes interesUsuario : interesesUsuario) {
						if (interesUsuario.getIdInteres() == interes.getIdInteres()) {
							dtoInteresUsuario.setEstaSuscripto(true);
							break;
						}
					}
					res.add(dtoInteresUsuario);
				}
			}
		} catch(Exception exception) {
			return res;
		}
		return res;
	}

	private void enviarNotificacion(String idPersona, String mensaje, String titulo, String tipoNotificacion) {
		List<DTOConfiguracion> dtoConfiguraciones = getByIdPersona(idPersona);
		String email = getEmailById(idPersona);
		for (DTOConfiguracion dtoConfig : dtoConfiguraciones) {
			switch (tipoNotificacion) {
				case "desbloquearUsuario":
					if (dtoConfig.isDesbloquearUsuario()) {
						enviar(dtoConfig, mensaje, titulo, email, idPersona);
					}
				case "bloquearUsuario":
					if (dtoConfig.isBloquearUsuario()) {
						enviar(dtoConfig, mensaje, titulo, email, idPersona);
					}
				case "agregarContacto":
					if (dtoConfig.isAltaContacto()) {
						enviar(dtoConfig, mensaje, titulo, email, idPersona);
					}
				default:
					continue;
			}
		}
	}
	
	private void enviarNotificacionBajaUsuarioAdmin(String email, String mensaje, String titulo) {
		enviarNotificacion.enviarEmailNotificacion(mensaje, email, titulo);
	}
	
	private void enviar(DTOConfiguracion dtoConfig, String mensaje, String titulo, String email, String idPersona) {
		if (dtoConfig.isEmailNotification()) {
			enviarNotificacion.enviarEmailNotificacion(mensaje, email, titulo);
		} else {
			enviarNotificacion.enviarPushNotificacion(mensaje, idPersona, titulo);
		}
	}
	
	private String getEmailById(String idPersona) {
		String email = null;
		try {
			Usuario usuario = manager.find(Usuario.class, idPersona);
			if (usuario != null) {
				email = usuario.getEmail();
			}
		} catch (Exception exception) {
			return email;
		}
		return email;
	}

	public List<DTOConfiguracion> getByIdPersona(String idPersona) {
		List<DTOConfiguracion> res = new ArrayList<DTOConfiguracion>();
		try {
			TypedQuery<Configuracion> query = manager.createQuery("SELECT configuracion FROM Configuracion configuracion where configuracion.idPersona = '" + idPersona + "'", Configuracion.class);
			List<Configuracion> configuraciones =  query.getResultList();
			if (configuraciones.size() > 0) {
				for (Configuracion configuracion : configuraciones) {
					DTOConfiguracion dtoConfiguracion = new DTOConfiguracion(configuracion);
					res.add(dtoConfiguracion);
				}	
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	private String getNicknameById(String idPersona) {
		String nickname = null;
		try {
			Usuario usuario = manager.find(Usuario.class, idPersona);
			if (usuario != null) {
				nickname = usuario.getNickname();
			}
		} catch (Exception exception) {
			return nickname;
		}
		return nickname;
	}
}
