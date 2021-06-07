package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.model.*;
import com.javaee.pryectoBack.util.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class ControladorUsuarioDA implements ControladorUsuarioDALocal, ControladorUsuarioDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	public ControladorUsuarioDA()
	{
	}

	@Override
	public boolean editarPerfil(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registrarUsuario(DTOUsuario dtoUsuario) {
		try{
			Usuario user = new Usuario(dtoUsuario);
			user.getConfiguracion().setUsuario(user);
			manager.merge(user);
			return true;
		}catch (Exception exception) {
			return false;
		}
	}

	@Override
	public boolean subirFoto(String idPersona, DTOMultimedia dtoMultimedia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarContacto(String idPersona, String idPersona2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bajaContacto(String idPersona, String idPersona2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarCuenta(String idPersona) {

		try	{
			Usuario user = manager.find(Usuario.class,idPersona);

			if (user != null) {

				//Medallas
				List<Medalla> medallas = user.getMedallas();
				for (Medalla med : medallas) {
					manager.remove(med);
				}

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
	public boolean modificarUsuarioAdmin(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bloquearUsuario(String idPersona) {
		boolean quedoBloqueado;
			Usuario user = manager.find(Usuario.class, idPersona);
			if(user != null) {
				boolean estaBloqueado = user.getEstaBloqueado();
				if (!estaBloqueado) {
					user.setEstaBloqueado(true);
				}
				quedoBloqueado = true;
				manager.persist(user);
			} else {
				quedoBloqueado = false;
			}
		return quedoBloqueado;
	}

	@Override
	public boolean desbloquearUsuario(String idPersona) {
		// TODO Auto-generated method stub
		return false;

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
}
