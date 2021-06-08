package com.javaee.pryectoBack.data;

import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
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
	public boolean registrarUsuario(DTOUsuario dtoUsuario) {
		try{
			Usuario user = new Usuario(dtoUsuario);
			user.getMedalla().setUsuario(user);
			user.getConfiguracion().setUsuario(user);
			PerfilUsuario perfil = new PerfilUsuario(user);
			user.setPerfil(perfil);
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
		try{
			Persona user1 = manager.find(Usuario.class, idPersona);
			Persona user2 = manager.find(Usuario.class, idPersona2);
			if (user1 != null && user2 != null) {
				UsuarioContacto usuarioContacto = new UsuarioContacto();
				usuarioContacto.setIdPersona(idPersona);
				usuarioContacto.setContactoIdPersona(idPersona2);
				usuarioContacto.setFechaContactos(new Date());
				usuarioContacto.setEstadoContactos(estadosContactos.pendiente);
				UsuarioContacto usuarioContacto2 = new UsuarioContacto();
				usuarioContacto2.setIdPersona(idPersona2);
				usuarioContacto2.setContactoIdPersona(idPersona);
				usuarioContacto2.setFechaContactos(new Date());
				usuarioContacto2.setEstadoContactos(estadosContactos.pendiente);
				manager.persist(usuarioContacto);
				manager.persist(usuarioContacto2);
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
		// TODO Auto-generated method stub
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

	@Override
	public DTOUsuarioContacto respuestaContacto(DTOUsuarioContacto dtoUsuarioContacto) {
		DTOUsuarioContacto dtoUsuarioContactoRes = new DTOUsuarioContacto();
		try {
			UsuarioContacto usuarioContacto1 = manager.find(UsuarioContacto.class, new UsuarioContactoId(dtoUsuarioContacto.getIdPersona(), dtoUsuarioContacto.getContactoIdPersona()));
			UsuarioContacto usuarioContacto2 = manager.find(UsuarioContacto.class, new UsuarioContactoId(dtoUsuarioContacto.getContactoIdPersona(), dtoUsuarioContacto.getIdPersona()));
			if (usuarioContacto1 != null && usuarioContacto2 != null) {
				usuarioContacto1.setEstadoContactos(dtoUsuarioContacto.getEstadoContactos());
				usuarioContacto2.setEstadoContactos(dtoUsuarioContacto.getEstadoContactos());
				manager.merge(usuarioContacto1);
				manager.merge(usuarioContacto2);
				dtoUsuarioContactoRes = new DTOUsuarioContacto(usuarioContacto1);
				Usuario usuario1 = manager.find(Usuario.class, usuarioContacto1.getIdPersona());
				DTOUsuario dtoUsuario1 = new DTOUsuario(usuario1);
				Usuario usuario2 = manager.find(Usuario.class, usuarioContacto1.getContactoIdPersona());
				DTOUsuario dtoUsuario2 = new DTOUsuario(usuario2);
				puntoUsuario.getPuntosUsuario("AltaContacto", dtoUsuario1, manager);
				puntoUsuario.getPuntosUsuario("AltaContacto", dtoUsuario2, manager);
			}
		} catch (Exception exception) {
			return dtoUsuarioContactoRes;
		}
		return dtoUsuarioContactoRes;
	}
}
