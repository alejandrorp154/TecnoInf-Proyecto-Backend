package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.model.Usuario;

@Singleton
public class ControladorVisualizacionDA implements ControladorVisualizacionDALocal, ControladorVisualizacionDARemote {


	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public ControladorVisualizacionDA()
	{
	}
	
	@Override
	public List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOPerfilUsuario visualizarPerfil(String idPersona) {
		DTOPerfilUsuario dtoPerfil = new DTOPerfilUsuario();
		Persona usuario = manager.find(Usuario.class, idPersona);
		if (usuario != null) {
			PerfilUsuario perfil = manager.find(PerfilUsuario.class, idPersona);
			if (perfil != null)
			{
				dtoPerfil = new DTOPerfilUsuario(perfil);
			}
		}
		return dtoPerfil;
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
