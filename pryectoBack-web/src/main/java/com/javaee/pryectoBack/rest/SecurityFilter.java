package com.javaee.pryectoBack.rest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.util.Base64;

import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.service.ControladorLocal;

@Provider
public class SecurityFilter implements ContainerRequestFilter
{
	@EJB
	private ControladorLocal controladorLocal;
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	private static final String SWAGGER_API_DOCS = "api-docs";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SWAGGER_API_DOCS)) {
			return;
		}
		List<String> autHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if (autHeaders != null && autHeaders.size() > 0) {
			String authToken = autHeaders.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			byte[] decoded = Base64.decode(authToken);
			String decodedString = new String(decoded);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			String username = tokenizer.nextToken();
			String uid = tokenizer.nextToken();
			Usuario usuario;
			try {
				usuario = controladorLocal.getUsuarioByUid(uid);
				if (usuario.getUid() != null)
				{				
					if (uid.equals(usuario.getUid())) {
						return;
					}
				}
			} catch (Exception e) {
				Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("user can not access the resource").build();
				requestContext.abortWith(unauthorizedStatus);
			}
		}

		Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("user can not access the resource").build();
		requestContext.abortWith(unauthorizedStatus);
	}

}
