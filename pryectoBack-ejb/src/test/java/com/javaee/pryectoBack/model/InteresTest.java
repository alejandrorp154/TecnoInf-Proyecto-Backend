package com.javaee.pryectoBack.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class InteresTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        interes.setIdInteres(1);
        interes.setInteres("Asado");
        List<PerfilUsuario> perfilUsuarios = new ArrayList<>();
        interes.setPerfiles(perfilUsuarios);

    }

    private Interes interes = new Interes();

    @Test
    public void interesDefault(){

    }

    @Test
    public void getIdInteres() {
        int idInteres = interes.getIdInteres();
        Assert.assertEquals(idInteres,1);
    }

    @Test
    public void setIdInteres() {
        interes.setIdInteres(2);
        Assert.assertEquals(interes.getIdInteres(),2);
    }

    @Test
    public void getInteres() {
        String inter = interes.getInteres();
        Assert.assertEquals(inter,"Asado");
    }

    @Test
    public void setInteres() {
        interes.setInteres("Pizza");
        Assert.assertEquals(interes.getInteres(),"Pizza");
    }

    @Test
    public void getPerfiles() {
        List<PerfilUsuario> res = interes.getPerfiles();
        List<PerfilUsuario> perfilUsuario = interes.getPerfiles();
        Assert.assertEquals(perfilUsuario,res);
    }

    @Test
    public void setPerfiles() {
        List<PerfilUsuario> perfilUsuarios = new ArrayList<>();
        Assert.assertEquals(interes.getPerfiles(),perfilUsuarios);
    }
}
