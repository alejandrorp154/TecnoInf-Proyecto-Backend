package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOInteres;

@Local
public interface ControladorInteresLocal {
	DTOInteres alta(DTOInteres dtoInteres);
	boolean baja(int idInteres);
	DTOInteres modificar(DTOInteres dtoInteres);
	boolean suscribe(String idPersona, int idInteres);
	boolean desuscribe(String idPersona, int idInteres);
	DTOInteres getById(int idInteres);
	List<DTOInteres> getAll(int offset, int size);
}
