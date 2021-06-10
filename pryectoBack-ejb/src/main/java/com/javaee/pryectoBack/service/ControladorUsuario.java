package com.javaee.pryectoBack.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorUsuarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;

@Stateless
@Remote(ControladorUsuarioRemote.class)
public class ControladorUsuario implements ControladorUsuarioRemote, ControladorUsuarioLocal {

	@EJB
	private ControladorUsuarioDALocal controladorDA;

	@Override
	public DTOUsuario editarPerfil(DTOUsuario dtoUsuario) {
		return controladorDA.editarPerfil(dtoUsuario);
	}

	@Override
	public boolean registrarUsuario(DTOUsuario dtoUsuario) {
		return controladorDA.registrarUsuario(dtoUsuario);
	}

	@Override
	public boolean subirFoto(String idPersona, DTOMultimedia dtoMultimedia) {
		// TODO Auto-generated method stub
		return false;
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
		return controladorDA.bloquearUsuario(idPersona);
	}

	@Override
	public boolean desbloquearUsuario(String idPersona) {
		// TODO Auto-generated method stub
		return false;
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
}
