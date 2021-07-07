package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorUsuarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
import com.javaee.pryectoBack.datatypes.DTOInteresUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.datatypes.DTOUsuarioPerfil;

@Stateless
@Remote(ControladorUsuarioRemote.class)
public class ControladorUsuario implements ControladorUsuarioRemote, ControladorUsuarioLocal {

	@EJB
	private ControladorUsuarioDALocal controladorDA;

	@Override
	public DTOUsuario editarPerfil(DTOUsuarioPerfil dtoUsuario) {
		return controladorDA.editarPerfil(dtoUsuario);
	}

	@Override
	public DTOUsuario registrarUsuario(DTOUsuario dtoUsuario) {
		return controladorDA.registrarUsuario(dtoUsuario);
	}

	@Override
	public boolean subirFoto(DTOMultimedia dtoMultimedia) {
		return controladorDA.subirFoto(dtoMultimedia);
	}

	@Override
	public boolean agregarContacto(String idPersona, String idPersona2) {
		return controladorDA.agregarContacto(idPersona, idPersona2);
	}

	@Override
	public boolean bajaContacto(String idPersona, String idPersona2) {
		return controladorDA.bajaContacto(idPersona, idPersona2);
	}

	@Override
	public boolean eliminarCuenta(String idPersona) {
		return controladorDA.eliminarCuenta(idPersona);
	}

	@Override
	public boolean bajaUsuarioAdmin(String idPersona) {
		return controladorDA.bajaUsuarioAdmin(idPersona);
	}

	@Override
	public DTOAdministrador modificarUsuarioAdmin(DTOAdministrador dtoAdministrador) {
		return controladorDA.modificarUsuarioAdmin(dtoAdministrador);
	}

	@Override
	public boolean bloquearUsuario(String idPersona) {
		return controladorDA.bloquearUsuario(idPersona);
	}

	@Override
	public boolean desbloquearUsuario(String idPersona) {
		return controladorDA.desbloquearUsuario(idPersona);
	}

	@Override
	public DTOUsuarioInicioSesion datosUsuarioInicioSesion(String idPersona){
		return controladorDA.datosUsuarioInicioSesion(idPersona);
	}

	@Override
	public DTOUsuarioContacto respuestaContacto(DTOUsuarioContacto dtoUsuarioContacto) {
		return controladorDA.respuestaContacto(dtoUsuarioContacto);
	}

	@Override
	public DTOAdministrador altaUsuarioAdmin(DTOAdministrador dtoAdministrador){
		return controladorDA.altaUsuarioAdmin(dtoAdministrador);
	}

	@Override
	public DTOUsuarioPerfil getPerfil(String idPersona) {
		return controladorDA.getPerfil(idPersona);
	}

	@Override
	public boolean sonAmigos(String idPersona, String idContacto) {
		return controladorDA.sonAmigos(idPersona, idContacto);
	}

	@Override
	public List<DTOInteresUsuario> getInteresesUsuario(String idPersona) {
		return controladorDA.getInteresesUsuario(idPersona);
	}
}
