package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;

@Remote
public interface ControladorVisualizacionDARemote {
	List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size);
	DTOPerfilUsuario visualizarPerfil(String idPersona);
	List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerUsuarios(int offset, int size);
	DTOUsuarioMedalla visualizarProgreso(String idPersona);
	List<DTOUsuario> obtenerCantidadUsuariosEnElSistema(int offset, int size);
	List<DTOUsuario> obtenerCantidadUsuariosSegunPais(String pais, int offset, int size);
	List<DTOUsuarioMedalla> obtenerCantidadUsuariosSegunMedallas(int idMedalla, int offset, int size);
	List<DTOUsuario> buscarAmigosSegunUbicacion(List<DTOUsuario> dtoUsuarios, String idPersona);
	List<DTOUsuario> buscarAmigosDeAmigos(String idPersona);
	List<DTOUsuario> buscarAmigosSegunIntereses(List<DTOUsuario> dtoUsuarios, String idPersona);
	List<DTOUsuario> obtenerSolicitudesPendientes(String idPersona, int offset, int size);
}
