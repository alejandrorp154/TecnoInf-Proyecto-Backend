package com.javaee.pryectoBack.service;

import javax.ejb.Local;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Local
public interface ControladorConfigSistemaLocal {
	boolean configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
}
