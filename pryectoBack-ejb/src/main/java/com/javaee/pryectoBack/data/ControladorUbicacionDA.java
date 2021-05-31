package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;

@Singleton
public class ControladorUbicacionDA implements ControladorUbicacionDALocal, ControladorUbicacionDARemote {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Override
	public boolean alta(DTOUbicacion dtoUbicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DTOUbicacion> obtenerUbicaciones(String idPersona) {
		Usuario user = manager.find(Usuario.class, idPersona);
		List<Ubicacion> ubicaciones = user.getUbicaciones();
		List<DTOUbicacion> result = new ArrayList<DTOUbicacion>();
		for (Ubicacion ubicacion : ubicaciones) {
			DTOUbicacion dto = ubicacion.getDTO();
			dto.setIdPersona(idPersona);
			result.add(dto);			
		}
		return result;		
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
