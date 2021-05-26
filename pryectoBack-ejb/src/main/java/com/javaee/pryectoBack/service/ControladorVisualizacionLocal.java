package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;

@Local
public interface ControladorVisualizacionLocal {
	List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size);
	DTOUsuario visualizarPerfil(String idPersona);
	List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size);
	List<DTOUsuario> obtenerUsuarios(int offset, int size);
	DTOUsuarioMedalla visualizarProgreso(String idPersona);
	List<DTOUsuario> obtenerCantidadUsuariosEnElSistema(int offset, int size);
	List<DTOUsuario> obtenerCantidadUsuariosSegunPais(String pais, int offset, int size);
	List<DTOUsuarioMedalla> obtenerCantidadUsuariosSegunMedallas(int idMedalla, int offset, int size);
}