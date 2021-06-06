package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.model.*;

import java.util.List;

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

				//Evento
					//Creados por el usuario
				List<Evento> eventosCreadosPorUsuario = user.getCreadorDeEventos();
				for (Evento event : eventosCreadosPorUsuario){

					List<Usuario> usuariosQueAsistiran = event.getUsuarios();

					for (Usuario usuarioAsisteEvento: usuariosQueAsistiran){
						usuarioAsisteEvento.getEventos().remove(event);
					}

				}

					//Creados a los que asistira el usuario

				//Perfil
					//Publicaciones
					//ComentariosYReacciones
					//Intereses
					//Galeria






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
