package com.javaee.pryectoBack.data;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioContacto;
import com.javaee.pryectoBack.datatypes.DTOAdministrador;
import com.javaee.pryectoBack.datatypes.DTOUsuarioInicioSesion;
import com.javaee.pryectoBack.datatypes.DTOUsuarioPerfil;

@Local
public interface ControladorUsuarioDALocal {
	DTOUsuario editarPerfil(DTOUsuarioPerfil dtoUsuario);
	DTOUsuario registrarUsuario(DTOUsuario dtoUsuario);
	boolean subirFoto(DTOMultimedia dtoMultimedia);
	boolean agregarContacto(String idPersona, String idPersona2);
	boolean bajaContacto(String idPersona, String idPersona2);
	boolean eliminarCuenta(String idPersona);
	boolean bajaUsuarioAdmin(String idPersona);
	DTOAdministrador modificarUsuarioAdmin(DTOAdministrador dtoAdministrador);
	boolean bloquearUsuario(String idPersona);
	boolean desbloquearUsuario(String idPersona);
	DTOUsuarioInicioSesion datosUsuarioInicioSesion(String idPersona);
	DTOUsuarioContacto respuestaContacto(DTOUsuarioContacto dtoUsuarioContacto);
	DTOAdministrador altaUsuarioAdmin(DTOAdministrador dtoAdministrador);
	DTOUsuarioPerfil getPerfil(String idPersona);
}