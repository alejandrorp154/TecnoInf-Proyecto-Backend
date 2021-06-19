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
	public DTOUbicacion alta(DTOUbicacion dtoUbicacion) {
		try {
			Usuario owner = manager.find(Usuario.class, dtoUbicacion.getIdPersona());
			if (owner != null) {
				Ubicacion ubicacion = new Ubicacion(dtoUbicacion);
				Usuario user = manager.find(Usuario.class, dtoUbicacion.getIdPersona());
				ubicacion.setUsuario(user);
				manager.persist(ubicacion);
				owner.getUbicaciones().add(ubicacion);
				manager.merge(owner);
				DTOUbicacion ubicacionCreada = dtoUbicacion;
				ubicacionCreada.setIdUbicacion(ubicacion.getIdUbicacion());
				return ubicacionCreada;
			}			
			return null;
		} catch (Exception exception) {
			return null;
		}
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
		boolean res = false;
		try {
			Ubicacion ubicacion = manager.find(Ubicacion.class, idUbicacion);
			if (ubicacion != null) {
				manager.remove(ubicacion);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}

	@Override
	public boolean modificar(DTOUbicacion dtoUbicacion) {
		boolean res = false;
		try {
			Ubicacion ubicacion = manager.find(Ubicacion.class, dtoUbicacion.getIdUbicacion());
			if (ubicacion != null) {
				ubicacion.setDescripcion(dtoUbicacion.getDescripcion());
				ubicacion.setFecha(dtoUbicacion.getFecha());
				ubicacion.setLatitud(dtoUbicacion.getLatitud());
				ubicacion.setLongitud(dtoUbicacion.getLongitud());
				ubicacion.setPais(dtoUbicacion.getPais());
				manager.merge(ubicacion);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
}
