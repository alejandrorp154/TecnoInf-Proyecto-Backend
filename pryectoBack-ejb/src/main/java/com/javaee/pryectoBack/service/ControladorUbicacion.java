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
	public DTOUbicacion alta(DTOUbicacion dtoUbicacion) {
		return controladorUbicacionDA.alta(dtoUbicacion);
	}

	@Override
	public List<DTOUbicacion> obtenerUbicaciones(String idPersona) {
		return controladorUbicacionDA.obtenerUbicaciones(idPersona);
	}

	@Override
	public boolean baja(int idUbicacion) {
		return controladorUbicacionDA.baja(idUbicacion);
	}

	@Override
	public boolean modificar(DTOUbicacion dtoUbicacion) {
		return controladorUbicacionDA.modificar(dtoUbicacion);
	}
}
