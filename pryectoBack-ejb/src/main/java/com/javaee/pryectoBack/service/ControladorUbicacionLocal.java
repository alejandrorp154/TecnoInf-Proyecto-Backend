package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;

@Local
public interface ControladorUbicacionLocal {
	DTOUbicacion alta(DTOUbicacion dtoUbicacion);
	List<DTOUbicacion> obtenerUbicaciones(String idPersona);
	boolean baja(int idUbicacion);
	boolean modificar(DTOUbicacion dtoUbicacion);
}
