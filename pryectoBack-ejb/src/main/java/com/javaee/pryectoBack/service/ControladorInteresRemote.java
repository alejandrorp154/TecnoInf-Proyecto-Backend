package com.javaee.pryectoBack.service;

import java.util.List;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOInteres;

@Remote
public interface ControladorInteresRemote {
	DTOInteres alta(DTOInteres dtoInteres);
	boolean baja(int idInteres);
	DTOInteres modificar(DTOInteres dtoInteres);
	boolean suscribe(String idPersona, int idInteres);
	boolean desuscribe(String idPersona, int idInteres);
	DTOInteres getById(int idInteres);
	List<DTOInteres> getAll(int offset, int size);
}
