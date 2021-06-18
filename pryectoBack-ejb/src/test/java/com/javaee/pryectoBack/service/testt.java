package com.javaee.pryectoBack.service;

import com.javaee.pryectoBack.data.ControladorUsuarioDA;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;

import java.sql.Date;

import com.javaee.pryectoBack.data.ControladorUsuarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOUsuario;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ejb.AfterBegin;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class testt {

	//@Mock
	//ControladorUsuarioDALocal ctrlUserD;

    @InjectMocks
    private ControladorUsuarioDALocal controladorUsuario = new ControladorUsuarioDA();

    @PersistenceContext(unitName = "primary")
	private EntityManager manager;
 
    EntityManagerFactory emf  = mock(EntityManagerFactory.class);;
	EntityManager em=emf.createEntityManager();	
    
    Date f = new Date(02/02/2020);
    DTOUsuario dtoUser = new DTOUsuario("10", "probanto1@gmail.com", "German", "Gutierrez", "elguti", "en su casa", "099111111", "Uruguay","imagen", "imagenPerfil", "jpg");
	
  
    
    @Test
    public void registrarUsuario(){
    	DTOUsuario dtoUser = new DTOUsuario("10", "probanto1@gmail.com", "German", "Gutierrez", "elguti", "en su casa", "099111111", "Uruguay","imagen", "imagenPerfil", "jpg");
    	MockitoAnnotations.initMocks(this);
    	System.out.println("test: " + dtoUser.getApellido() );
    	//Mockito.when(controladorUsuario.registrarUsuario(Mockito.any(DTOUsuario.class))).thenReturn(dtoUser);
    	DTOUsuario resdto = controladorUsuario.registrarUsuario(dtoUser);
    	//assertEquals(dtoUser,resdto);
    	//assertEquals(resdto.getApellido(),dtoUser.getApellido());
    	
    }  
}