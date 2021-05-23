package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;

@Singleton
public class ControladorUbicacionDA implements ControladorUbicacionDALocal, ControladorUbicacionDARemote {

	@Override
	public boolean alta(DTOUbicacion dtoUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DTOUbicacion> obtenerUbicaciones(String idPersona) {
		// TODO Auto-generated method stub
		return null;
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
