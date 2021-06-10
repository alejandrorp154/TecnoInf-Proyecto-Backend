package com.javaee.pryectoBack.data;

import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.model.Medalla;
import com.javaee.pryectoBack.model.Notificacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Multimedia;
import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.Administrador;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.UsuarioContacto;
import com.javaee.pryectoBack.model.UsuarioContactoId;
import com.javaee.pryectoBack.model.estadosContactos;
import com.javaee.pryectoBack.util.PuntosUsuario;


@Singleton
public class ControladorUsuarioDA implements ControladorUsuarioDALocal, ControladorUsuarioDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	private PuntosUsuario puntoUsuario;
	
	public ControladorUsuarioDA()
	{
		puntoUsuario = new PuntosUsuario();
	}

	@Override
	public DTOUsuario editarPerfil(DTOUsuario dtoUsuario) {
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
		try{
			Usuario user = new Usuario(dtoUsuario);
			user.getMedalla().setUsuario(user);
			user.getConfiguracion().setUsuario(user);
			PerfilUsuario perfil = new PerfilUsuario(user, dtoUsuario);
			user.setPerfil(perfil);
			manager.merge(user);
			return dtoUsuario;
		}catch (Exception exception) {
			return null;
		}
	}

	@Override
	public boolean subirFoto(String idPersona, DTOMultimedia dtoMultimedia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarContacto(String idPersona, String idPersona2) {
		try{
			Persona user1 = manager.find(Usuario.class, idPersona);
			Persona user2 = manager.find(Usuario.class, idPersona2);
			if (user1 != null && user2 != null) {
				UsuarioContacto usuarioContacto = new UsuarioContacto();
				usuarioContacto.setIdPersona(idPersona);
				usuarioContacto.setContactoIdPersona(idPersona2);
				usuarioContacto.setFechaContactos(new Date());
				usuarioContacto.setEstadoContactos(estadosContactos.pendiente);
				manager.persist(usuarioContacto);
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

				//Medallas
				Medalla medalla = user.getMedalla();
				manager.remove(medalla);
				

				//Configuracion
				manager.remove(user.getConfiguracion());

				//Notificaciones
				List<Notificacion> notificaciones = user.getNotificaciones();
				for (Notificacion noti : notificaciones) {
					manager.remove(noti);
				}

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

						//Dessasigna a Usuarios que asistiran
						List<Usuario> usuariosQueAsistiran = event.getUsuarios();
						if (!usuariosQueAsistiran.isEmpty()) {
							for (Usuario usuarioAsisteEvento : usuariosQueAsistiran) {
								usuarioAsisteEvento.getEventos().remove(event);
							}
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

								manager.remove(pub);
							}
						}
						manager.remove(event);
					}
				}

				//Eventos a los que asistira el usuario
				List<Evento> eventos = user.getEventos();

				if (!eventos.isEmpty()){
					for (Evento event : eventos){
						event.getUsuarios().remove(user);
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

						MongoDBConnector mongoConnector = new MongoDBConnector();
						MongoCollection<Document> collection = mongoConnector.getCollection("ComentariosYReacciones");

						String idPublicacion = String.valueOf(publicacion.getIdPublicacion());
						collection.deleteOne(eq("idPublicacion", new ObjectId(idPublicacion)));

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

	@Override
	public boolean bajaUsuarioAdmin(String idPersona) {
		// TODO Auto-generated method stub
		return false;
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
				manager.persist(user);
				fueDesbloqueado = true;
			}
			return fueDesbloqueado;
		}catch (Exception exception){
			return false;
		}
	}

	@Override
	public DTOUsuarioInicioSesion datosUsuarioInicioSesion(String idPersona){

		Usuario user = manager.find(Usuario.class, idPersona);

		if (user != null){
			String imagen = user.getPerfil().getImagenPerfil();
			return new DTOUsuarioInicioSesion(user.getIdPersona(), user.getEmail(), user.getNombre(), user.getApellido(), user.getNickname(), imagen);
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
				usuarioContacto2.setContactoIdPersona(dtoUsuarioContacto.getContactoIdPersona());
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
}
