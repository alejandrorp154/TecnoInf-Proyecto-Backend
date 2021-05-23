package com.javaee.pryectoBack.data;

import javax.ejb.Remote;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;

@Remote
public interface ControladorConfigSistemaDARemote {
	boolean configurarNotificaciones(DTOConfiguracion dtoConfiguracion);
}
