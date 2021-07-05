package com.javaee.pryectoBack.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.javaee.pryectoBack.datatypes.DTOUsuario;
import com.javaee.pryectoBack.model.Medalla;
import com.javaee.pryectoBack.model.Persona;
import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.rangos;

public class PuntosUsuario
{
	private Map<String, Integer> map = null;
	
	public PuntosUsuario()
	{
		this.initMap();
	}

	public int getPuntosUsuario(String accion, DTOUsuario dtoUsuario, EntityManager manager)
	{
		if (map == null)
		{
			this.initMap();
		}
		int puntos = map.get(accion) != null ? map.get(accion) : 0;
		this.updatePuntosUsuario(dtoUsuario, puntos, manager);
		return puntos;
	}
	
	private void updatePuntosUsuario(DTOUsuario dtoUsuario, int puntos, EntityManager manager) {
		try {
			String idPersona = dtoUsuario.getIdPersona();
			Persona persona = manager.find(Usuario.class, idPersona);
			if (persona != null) {
				Usuario usuario = (Usuario)persona;
				if (usuario.getMedalla() == null) {
					Medalla medalla = new Medalla();
					medalla.setUsuario(usuario);
					usuario.setMedalla(medalla);
				}
				int cantidadPuntos = usuario.getMedalla().getCantidadPuntos() + puntos;
				rangos rango = isInRange(0, 75, cantidadPuntos) ? rangos.ironWolf :  
					isInRange(75, 150, cantidadPuntos) ? rangos.bronzeWolf : 
						isInRange(150, 300, cantidadPuntos) ? rangos.silverWolf : 
							isInRange(300, 600, cantidadPuntos) ? rangos.goldWolf : 
								isInRange(600, 1200, cantidadPuntos) ? rangos.platinumWolf : 
									isInRange(1200, 2400, cantidadPuntos) ? rangos.diamondWolf : 
										isInRange(2400, 4800, cantidadPuntos) ? rangos.masterWolf :
											rangos.alfaWolf;
				usuario.getMedalla().setCantidadPuntos(cantidadPuntos);
				usuario.getMedalla().setRango(rango);
				manager.merge(usuario);
			}
		} catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
		
	}

	private boolean isInRange(int min, int max, int cantidadPuntos)
	{
		return min < cantidadPuntos && cantidadPuntos <= max;
	}
	
	private void initMap()
    {
		map = new HashMap<String, Integer>();
		map.put("AltaPublicacion", 10);
		map.put("CompartirPublicacion", 15);
		map.put("AltaContacto", 15);
		map.put("AltaUbicacion", 50);
		map.put("ReaccionarPublicacion", 1);
		map.put("ReaccionarComentario", 1);
		map.put("ComentarPublicacion", 1);
		map.put("AltaEvento", 40);
		map.put("InvitarUsuarioEvento", 15);
		map.put("SubirFotoGaleria", 1);
		map.put("SubscribeInteres", 5);
		map.put("DesuscribirInteres", -5);
    }
}
