package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorVisualizacionDALocal;
import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;

@Stateless
@Remote(ControladorVisualizacionRemote.class)
public class ControladorVisualizacion implements ControladorVisualizacionLocal, ControladorVisualizacionRemote {
	
	@EJB
	private ControladorVisualizacionDALocal controladorVisualizacionDA;
	@Override
	public List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOUsuario visualizarPerfil(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> obtenerUsuarios(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOUsuarioMedalla visualizarProgreso(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> obtenerCantidadUsuariosEnElSistema(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> obtenerCantidadUsuariosSegunPais(String pais, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuarioMedalla> obtenerCantidadUsuariosSegunMedallas(int idMedalla, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
