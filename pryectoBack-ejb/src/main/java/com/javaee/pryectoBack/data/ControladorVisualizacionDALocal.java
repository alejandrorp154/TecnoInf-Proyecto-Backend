package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;
import com.javaee.pryectoBack.datatypes.DTOEstadistica;

@Local
public interface ControladorVisualizacionDALocal {
	List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size);
	DTOPerfilUsuario visualizarPerfil(String idPersona);
	List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerUsuarios(int offset, int size);
	DTOUsuarioMedalla visualizarProgreso(String idPersona);
	List<DTOUsuario> buscarAmigosSegunUbicacion(List<DTOUsuario> dtoUsuarios, String idPersona);
	List<DTOUsuario> buscarAmigosDeAmigos(String idPersona);
	List<DTOUsuario> buscarAmigosSegunIntereses(List<DTOUsuario> dtoUsuarios, String idPersona);
	List<DTOUsuario> obtenerSolicitudesPendientes(String idPersona, int offset, int size);
	List<DTOEstadistica> seleccionarTipoEstadistica(String tipoEstadistica);
}
