package com.javaee.pryectoBack.data;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Local
public interface ControladorConfigSistemaDALocal {
	boolean configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
}
