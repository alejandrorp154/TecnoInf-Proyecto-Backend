package com.javaee.pryectoBack.service;

import com.javaee.pryectoBack.data.ControladorPublicacionComentarioDA;
import com.javaee.pryectoBack.data.ControladorPublicacionComentarioDALocal;
import com.javaee.pryectoBack.datatypes.*;
import com.javaee.pryectoBack.model.estados;
import com.javaee.pryectoBack.model.tipos;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ControladorPublicacionComentarioTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private ControladorPublicacionComentarioDALocal controladorPublicacionComentarioDA = new ControladorPublicacionComentarioDA();

    @InjectMocks
    private ControladorPublicacionComentarioLocal controladorPublicacionComentario = new ControladorPublicacionComentario();

    @Test
    public void altaComentario() {
        Date date = new Date(2021, Calendar.JUNE,21);
        DTOComentario dtoComentario = new DTOComentario("1","Este es mi primer comentario",date,15,"1",null);

        Mockito.when(controladorPublicacionComentarioDA.altaComentario(Mockito.any(DTOComentario.class))).thenReturn(true);
        boolean res = controladorPublicacionComentario.altaComentario(dtoComentario);
        Assert.assertTrue(res);
    }
    @Test
    public void bajaComentario() {
    }

    @Test
    public void modificarComentario() {
    }

    @Test
    public void reaccionarComentario() {
    }

    @Test
    public void obtenerPublicaciones() {
    }

    @Test
    public void modificarPublicacion() {
        Date date = new Date(2021, Calendar.JUNE,21);

        DTOTipo dtoTipo = new DTOTipo(1, tipos.texto);

        List<DTOComentarioReaccion> comentariosReaccion = new ArrayList<>();
        Date date2 = new Date(2021,Calendar.JUNE,23);
        Date date3 = new Date(2021,Calendar.JUNE,21);
        DTOUbicacion dtoUb = new DTOUbicacion(22,5433354,534235,date3,"Detalles de la ubicación del evento","Uruguay");
        DTOEvento dtoEvento = new DTOEvento(1,"una buena fiesta",date2,date3, estados.enCurso,1,"1","Javier",dtoUb,"none","none","none");

        DTOPerfilUsuario dtoPerfilUsuario = new DTOPerfilUsuario();
        DTOUsuario usuario = new DTOUsuario("1","user1@gmail.com","Gabriel", "Mankers","gabiM","estadio centenario 1233","099111222","Uruguay","imagen823e09e2enLaCancha","image","jpg");
        dtoPerfilUsuario.setUsuario(usuario);

        DTOPublicacion dtopublic = new DTOPublicacion(1,"Primera publicación",date,dtoTipo,"1","text","Primera publicación",comentariosReaccion,dtoEvento,dtoPerfilUsuario);

        Mockito.when(controladorPublicacionComentarioDA.modificarPublicacion(Mockito.any(DTOPublicacion.class))).thenReturn(true);
        boolean res = controladorPublicacionComentario.modificarPublicacion(dtopublic);
        Assert.assertTrue(res);
    }

    @Test
    public void bajaPublicacion() {
        Mockito.when(controladorPublicacionComentarioDA.bajaPublicacion(Mockito.anyInt())).thenReturn(true);
        boolean res = controladorPublicacionComentario.bajaPublicacion(1);
        Assert.assertTrue(res);
    }

    @Test
    public void reaccionPublicacion() {
    }

    @Test
    public void altaPublicacion() {
        Date date = new Date(2021, Calendar.JUNE,21);

        DTOTipo dtoTipo = new DTOTipo(1, tipos.texto);

        List<DTOComentarioReaccion> comentariosReaccion = new ArrayList<>();
        Date date2 = new Date(2021,Calendar.JUNE,23);
        Date date3 = new Date(2021,Calendar.JUNE,21);
        DTOUbicacion dtoUb = new DTOUbicacion(22,5433354,534235,date3,"Detalles de la ubicación del evento","Uruguay");
        DTOEvento dtoEvento = new DTOEvento(1,"una buena fiesta",date2,date3, estados.enCurso,1,"1","Javier",dtoUb,"none","none","none");

        DTOPerfilUsuario dtoPerfilUsuario = new DTOPerfilUsuario();
        DTOUsuario usuario = new DTOUsuario("1","user1@gmail.com","Gabriel", "Mankers","gabiM","estadio centenario 1233","099111222","Uruguay","imagen823e09e2enLaCancha","image","jpg");
        dtoPerfilUsuario.setUsuario(usuario);

        DTOPublicacion dtopublic = new DTOPublicacion(1,"Primera publicación",date,dtoTipo,"1","text","Primera publicación",comentariosReaccion,dtoEvento,dtoPerfilUsuario);

        Mockito.when(controladorPublicacionComentarioDA.altaPublicacion(Mockito.any(DTOPublicacion.class))).thenReturn(dtopublic);
        DTOPublicacion res = controladorPublicacionComentario.altaPublicacion(dtopublic);
        Assert.assertNotNull(res);
    }
}
