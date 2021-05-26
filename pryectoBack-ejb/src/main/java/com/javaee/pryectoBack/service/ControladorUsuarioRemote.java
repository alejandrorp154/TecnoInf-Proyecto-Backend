package com.javaee.pryectoBack.service;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;

@Remote
public interface ControladorUsuarioRemote {
	boolean editarPerfil(DTOUsuario dtoUsuario);
	boolean registrarUsuario(DTOUsuario dtoUsuario);
	boolean subirFoto(String idPersona, DTOMultimedia dtoMultimedia);
	boolean agregarContacto(String idPersona, String idPersona2);
	boolean bajaContacto(String idPersona, String idPersona2);
	boolean eliminarCuenta(String idPersona);
	boolean bajaUsuarioAdmin(String idPersona);
	boolean modificarUsuarioAdmin(DTOUsuario dtoUsuario);
	boolean bloquearUsuario(String idPersona);
	boolean desbloquearUsuario(String idPersona);
}