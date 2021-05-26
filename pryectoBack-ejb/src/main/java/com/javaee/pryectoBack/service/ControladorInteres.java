package com.javaee.pryectoBack.service;

import java.util.List;

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
	public DTOInteres alta(DTOInteres dtoInteres) {
		return this.controladorInteresDA.alta(dtoInteres);
	}

	@Override
	public boolean baja(int idInteres) {
		return this.controladorInteresDA.baja(idInteres);
	}

	@Override
	public DTOInteres modificar(DTOInteres dtoInteres) {
		return this.controladorInteresDA.modificar(dtoInteres);
	}

	@Override
	public DTOInteres getById(int idInteres) {
		return this.controladorInteresDA.getById(idInteres);
	}

	@Override
	public List<DTOInteres> getAll(int offset, int size) {
		return this.controladorInteresDA.getAll(offset, size);
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
