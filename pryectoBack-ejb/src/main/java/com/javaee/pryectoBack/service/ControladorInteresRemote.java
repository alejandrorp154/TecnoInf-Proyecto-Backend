package com.javaee.pryectoBack.service;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOInteres;

@Remote
public interface ControladorInteresRemote {
	boolean alta(DTOInteres dtoInteres);
	boolean baja(DTOInteres dtoInteres);
	boolean modificar(DTOInteres dtoInteres);
	boolean suscribe(String idPersona, int idInteres);
	boolean desuscribe(String idPersona, int idInteres);
}
