package com.javaee.pryectoBack.rest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

@WebServlet(name = "SwaggerJaxrsConfig", loadOnStartup = 1)
public class SwaggerJaxrsConfig extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void init(ServletConfig servletConfig)
	{
		try {
	        super.init(servletConfig);
	        SwaggerConfig swaggerConfig = new SwaggerConfig();
	        ConfigFactory.setConfig(swaggerConfig);
	        swaggerConfig.setBasePath("https://travelpack.azurewebsites.net/pryectoBack-web/rest/");
	        swaggerConfig.setApiVersion("1.0.0");
	        ScannerFactory.setScanner(new DefaultJaxrsScanner());
	        ClassReaders.setReader(new DefaultJaxrsApiReader());
	    } catch (ServletException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
