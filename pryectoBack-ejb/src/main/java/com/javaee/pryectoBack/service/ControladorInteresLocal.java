package com.javaee.pryectoBack.service;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOInteres;

@Local
public interface ControladorInteresLocal {
	boolean alta(DTOInteres dtoInteres);
	boolean baja(DTOInteres dtoInteres);
	boolean modificar(DTOInteres dtoInteres);
	boolean suscribe(String idPersona, int idInteres);
	boolean desuscribe(String idPersona, int idInteres);
}
