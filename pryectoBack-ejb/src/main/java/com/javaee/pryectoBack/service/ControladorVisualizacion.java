package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorVisualizacionDALocal;
import com.javaee.pryectoBack.datatypes.DTOEstadistica;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;

@Stateless
@Remote(ControladorVisualizacionRemote.class)
public class ControladorVisualizacion implements ControladorVisualizacionLocal, ControladorVisualizacionRemote {
	
	@EJB
	private ControladorVisualizacionDALocal controladorVisualizacionDA;
	
	@Override
	public List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size) {
		return controladorVisualizacionDA.obtenerGaleriaFoto(idPersona, offset, size);
	}

	@Override
	public DTOPerfilUsuario visualizarPerfil(String idPersona) {
		return controladorVisualizacionDA.visualizarPerfil(idPersona);
	}

	@Override
	public List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size) {
		return controladorVisualizacionDA.obtenerSugerenciaAmigos(idPersona, offset, size);
	}

	@Override
	public List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size) {
		return controladorVisualizacionDA.obtenerAmigos(idPersona, offset, size);
	}

	@Override
	public List<DTOUsuario> obtenerUsuarios(int offset, int size) {
		return controladorVisualizacionDA.obtenerUsuarios(offset, size);
	}

	@Override
	public DTOUsuarioMedalla visualizarProgreso(String idPersona) {
		return controladorVisualizacionDA.visualizarProgreso(idPersona);
	}
	
	@Override
	public List<DTOEstadistica> seleccionarTipoEstadistica(String tipoEstadistica){
		return controladorVisualizacionDA.seleccionarTipoEstadistica(tipoEstadistica);
	}

	@Override
	public List<DTOUsuario> obtenerSolicitudesPendientes(String idPersona, int offset, int size) {
		return controladorVisualizacionDA.obtenerSolicitudesPendientes(idPersona, offset, size);
	}

}
