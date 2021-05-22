package com.javaee.pryectoBack.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.javaee.pryectoBack.data.ControladorInteresDALocal;
import com.javaee.pryectoBack.datatypes.DTOInteres;

@Stateless
@Remote(ControladorInteresRemote.class)
public class ControladorInteres implements ControladorInteresLocal, ControladorInteresRemote {

	@EJB
	private ControladorInteresDALocal controladorInteresDA;

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
