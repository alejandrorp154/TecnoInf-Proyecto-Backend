package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorUbicacionDALocal;
import com.javaee.pryectoBack.datatypes.DTOUbicacion;

@Stateless
@Remote(ControladorUbicacionRemote.class)
public class ControladorUbicacion implements ControladorUbicacionLocal, ControladorUbicacionRemote {

	@EJB
	private ControladorUbicacionDALocal controladorUbicacionDA;
	
	@Override
	public boolean alta(DTOUbicacion dtoUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DTOUbicacion> obtenerUbicaciones(String idPersona) {
		return controladorUbicacionDA.obtenerUbicaciones(idPersona);
	}

	@Override
	public boolean baja(int idUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(DTOUbicacion dtoUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DTOUbicacion seleccionarUbicacion(int idUbicacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
