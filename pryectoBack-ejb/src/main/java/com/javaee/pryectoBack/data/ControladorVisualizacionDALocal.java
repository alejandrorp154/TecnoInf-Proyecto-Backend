package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;

@Local
public interface ControladorVisualizacionDALocal {
	List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size);
	DTOUsuario visualizarPerfil(String idPersona);
	List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerUsuarios(int offset, int size);
	DTOUsuarioMedalla visualizarProgreso(String idPersona);
	List<DTOUsuario> obtenerCantidadUsuariosEnElSistema(int offset, int size);
	List<DTOUsuario> obtenerCantidadUsuariosSegunPais(String pais, int offset, int size);
	List<DTOUsuarioMedalla> obtenerCantidadUsuariosSegunMedallas(int idMedalla, int offset, int size);
	List<DTOUsuario> buscarAmigosSegunUbicacion(String idPersona, int offset, int size);
	List<DTOUsuario> buscarAmigosContactosCelular(String idPersona, int offset, int size);
	List<DTOUsuario> buscarAmigosDeAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> buscarAmigosSegunIntereses(String idPersona, int offset, int size);
}
