package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;

@Singleton
public class ControladorVisualizacionDA implements ControladorVisualizacionDALocal, ControladorVisualizacionDARemote {

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

	@Override
	public List<DTOUsuario> buscarAmigosSegunUbicacion(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> buscarAmigosContactosCelular(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> buscarAmigosDeAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTOUsuario> buscarAmigosSegunIntereses(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
