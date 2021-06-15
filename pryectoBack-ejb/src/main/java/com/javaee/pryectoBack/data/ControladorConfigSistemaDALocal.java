package com.javaee.pryectoBack.data;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Local
public interface ControladorConfigSistemaDALocal {
	DTOConfiguracion configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
	DTOConfiguracion getByIdPersona(String idPersona);
}
