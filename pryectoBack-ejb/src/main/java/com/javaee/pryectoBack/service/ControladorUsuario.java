package com.javaee.pryectoBack.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorUsuarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;

@Stateless
@Remote(ControladorUsuarioRemote.class)
public class ControladorUsuario implements ControladorUsuarioRemote, ControladorUsuarioLocal {

	@EJB
	private ControladorUsuarioDALocal controladorDA;

	@Override
	public boolean editarPerfil(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
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
}
