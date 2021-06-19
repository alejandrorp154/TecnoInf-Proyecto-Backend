package com.javaee.pryectoBack.data;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;

@Remote
public interface ControladorUbicacionDARemote {
	DTOUbicacion alta(DTOUbicacion dtoUbicacion);
	List<DTOUbicacion> obtenerUbicaciones(String idPersona);
	boolean baja(int idUbicacion);
	boolean modificar(DTOUbicacion dtoUbicacion);
}
