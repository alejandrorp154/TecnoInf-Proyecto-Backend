package com.javaee.pryectoBack.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javaee.pryectoBack.service.ControladorLocal;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/notification")
@RequestScoped
@Api(value = "/notification", description = "notification push!")
public class NotificationRest
{
	@EJB
	private ControladorLocal controladorLocal;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Envia una notificacion push a un topico",
    notes = "el topico debe ser creado previamente")
	public Response add(String message)
	{
		Response.ResponseBuilder builder = null;
		try {
        	controladorLocal.sendNotification(message);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
	}
}
