package com.javaee.pryectoBack.data;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOInteres;

@Singleton
public class ControladorInteresDA implements ControladorInteresDALocal, ControladorInteresDARemote {

	@Override
	public boolean alta(DTOInteres dtoInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean baja(DTOInteres dtoInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(DTOInteres dtoInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suscribe(String idPersona, int idInteres) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean desuscribe(String idPersona, int idInteres) {
		// TODO Auto-generated method stub
		return false;
	}

}
