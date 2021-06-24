package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;

@Remote
public interface ControladorUbicacionRemote {
	DTOUbicacion alta(DTOUbicacion dtoUbicacion);
	List<DTOUbicacion> obtenerUbicaciones(String idPersona);
	boolean baja(int idUbicacion);
	boolean modificar(DTOUbicacion dtoUbicacion);
}
