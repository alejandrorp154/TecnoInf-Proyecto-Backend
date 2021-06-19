package com.javaee.pryectoBack.service;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.sql.Date;

import com.javaee.pryectoBack.data.ControladorUsuarioDA;
import com.javaee.pryectoBack.data.ControladorUsuarioDALocal;
import com.javaee.pryectoBack.datatypes.DTOUsuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ControladorUsuarioTest {

	@Mock
	ControladorUsuarioDALocal ctrlUserD = new ControladorUsuarioDA();

    @InjectMocks
    private ControladorUsuarioLocal controladorUsuario = new ControladorUsuario();
    
    Date f = new Date(02/02/2020);
	DTOUsuario dtoUser = new DTOUsuario("10", "probanto1@gmail.com", "German", "Gutierrez", "elguti", "en su casa", "099111111", "Uruguay","imagen", "imagenPerfil", "jpg");
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

	
    @Test
    public void registrarUsuario(){
    	MockitoAnnotations.initMocks(this);
    	
    	Mockito.when(ctrlUserD.registrarUsuario(Mockito.any(DTOUsuario.class))).thenReturn(dtoUser);
    	DTOUsuario resdto = controladorUsuario.registrarUsuario(dtoUser);
    	assertEquals(dtoUser,resdto);
    	assertEquals(resdto.getApellido(),dtoUser.getApellido());
    	System.out.println("test: " + resdto.getApellido() + " y:  " + dtoUser.getApellido());
    }  
}