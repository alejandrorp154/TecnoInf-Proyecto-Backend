package com.javaee.pryectoBack.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuarioMedalla;
import com.javaee.pryectoBack.service.ControladorVisualizacionLocal;
import com.wordnik.swagger.annotations.Api;

@Path("/visualizacion")
@RequestScoped
@Api(value = "/visualizacion", description = "Visualizaciones!")
public class VisualizacionRest {

	@EJB
	private ControladorVisualizacionLocal controladorVisualizacionLocal;
	
	public List<DTOMultimedia> obtenerGaleriaFoto(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public DTOUsuario visualizarPerfil(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerSugerenciaAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerAmigos(String idPersona, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerUsuarios(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public DTOUsuarioMedalla visualizarProgreso(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerCantidadUsuariosEnElSistema(int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuario> obtenerCantidadUsuariosSegunPais(String pais, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DTOUsuarioMedalla> obtenerCantidadUsuariosSegunMedallas(int idMedalla, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
