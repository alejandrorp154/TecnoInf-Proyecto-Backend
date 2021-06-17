package com.javaee.pryectoBack.service;

import com.javaee.pryectoBack.data.ControladorUsuarioDA;
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
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ControladorUsuarioTest {

	/*
    @InjectMocks
    ControladorUsuarioDALocal controladorUsuarioDa = new ControladorUsuarioDA();

    @InjectMocks
    @PersistenceContext(unitName = "primary")
	private EntityManager manager;
    
    @Before
    public void cargarBase(){
    	DTOUsuario dtoUsuario = new DTOUsuario("1", "probanto1@gmail.com", "German", "Gutierrez", "elguti", "en su casa", "099111111", "Uruguay","imagen", "imagenPerfil", "jpg");
        controladorUsuarioDa.registrarUsuario(dtoUsuario);
        System.out.println("Termina carga");
    }
    */
    @InjectMocks
    private ControladorUsuarioLocal controladorUsuario = new ControladorUsuario();

     //@Mock
     //private ControladorUsuarioLocal controladorUsuarioMock = new ControladorUsuario();

    @Test
    public void sayHello(){
        String nome = "Dodi";
        Assert.assertEquals("Hello, Dodi", controladorUsuario.sayHello(nome));
        System.out.println(controladorUsuario.sayHello(nome));
    }
    
}
