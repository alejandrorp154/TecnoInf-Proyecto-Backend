package com.javaee.pryectoBack.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.model.Ubicacion;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.util.DbManager;
import com.javaee.pryectoBack.util.PuntosUsuario;

@Singleton
public class ControladorUbicacionDA implements ControladorUbicacionDALocal, ControladorUbicacionDARemote {

	private PuntosUsuario puntoUsuario;
	
	public ControladorUbicacionDA()
	{
		puntoUsuario = new PuntosUsuario();
	}
	
	@Override
	public DTOUbicacion alta(DTOUbicacion dtoUbicacion) {
		try {
			DbManager.open();
			Usuario owner = DbManager.find(Usuario.class, dtoUbicacion.getIdPersona());
			if (owner != null) {
				Ubicacion ubicacion = new Ubicacion(dtoUbicacion);
				Usuario user = DbManager.find(Usuario.class, dtoUbicacion.getIdPersona());
				ubicacion.setUsuario(user);
				DbManager.persist(ubicacion);
				owner.getUbicaciones().add(ubicacion);
				DbManager.merge(owner);
				DTOUbicacion ubicacionCreada = dtoUbicacion;
				ubicacionCreada.setIdUbicacion(ubicacion.getIdUbicacion());
				Usuario usuario1 = DbManager.find(Usuario.class, owner.getIdPersona());
				DTOUsuario dtoUsuario1 = new DTOUsuario(usuario1);
				puntoUsuario.getPuntosUsuario("AltaUbicacion", dtoUsuario1);
				return ubicacionCreada;
			}			
			return null;
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	public List<DTOUbicacion> obtenerUbicaciones(String idPersona) {
		DbManager.open();
		Usuario user = DbManager.find(Usuario.class, idPersona);
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
			DbManager.open();
			Ubicacion ubicacion = DbManager.find(Ubicacion.class, idUbicacion);
			if (ubicacion != null) {
				DbManager.remove(ubicacion);
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
			DbManager.open();
			Ubicacion ubicacion = DbManager.find(Ubicacion.class, dtoUbicacion.getIdUbicacion());
			if (ubicacion != null) {
				ubicacion.setDescripcion(dtoUbicacion.getDescripcion());
				ubicacion.setFecha(dtoUbicacion.getFecha());
				ubicacion.setLatitud(dtoUbicacion.getLatitud());
				ubicacion.setLongitud(dtoUbicacion.getLongitud());
				ubicacion.setPais(dtoUbicacion.getPais());
				DbManager.merge(ubicacion);
				res = true;
			}
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
}
