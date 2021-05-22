package com.javaee.pryectoBack.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.javaee.pryectoBack.datatypes.DTOConfiguracion;
import com.javaee.pryectoBack.service.ControladorConfigSistemaLocal;
import com.wordnik.swagger.annotations.Api;

@Path("/configSistema")
@RequestScoped
@Api(value = "/configSistema", description = "ConfigSistema!")
public class ConfigSistemaRest {
	
	@EJB
	private ControladorConfigSistemaLocal controladorConfigSistema;
	
	public boolean configurarNotificaciones(DTOConfiguracion dtoConfiguracion) {
		// TODO Auto-generated method stub
		return false;
	}
}
