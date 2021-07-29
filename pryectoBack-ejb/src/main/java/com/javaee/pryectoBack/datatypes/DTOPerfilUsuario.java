package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.javaee.pryectoBack.model.Interes;
import com.javaee.pryectoBack.model.Multimedia;
import com.javaee.pryectoBack.model.PerfilUsuario;
import com.javaee.pryectoBack.model.Publicacion;

public class DTOPerfilUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private DTOUsuario usuario;
	private List<DTOInteres> intereses = new ArrayList<>();
	private List<DTOMultimedia> galerias = new ArrayList<>();
	private List<DTOPublicacion> publicaciones = new ArrayList<>();
	
	public DTOPerfilUsuario() {
	}

	public DTOPerfilUsuario(DTOUsuario usuario, List<DTOInteres> intereses, List<DTOMultimedia> galerias,
			List<DTOPublicacion> publicaciones) {
		super();
		this.usuario = usuario;
		this.intereses = intereses;
		this.galerias = galerias;
		this.publicaciones = publicaciones;
	}

	public DTOPerfilUsuario(PerfilUsuario perfil) {
		this.usuario = new DTOUsuario(perfil.getUsuario());
		this.intereses = this.getIntereses(perfil.getIntereses());
		this.galerias = this.getGalerias(perfil.getGalerias());
		this.publicaciones = this.getPublicaciones(perfil.getPublicaciones());
	}

	private List<DTOPublicacion> getPublicaciones(List<Publicacion> publicaciones) {
		List<DTOPublicacion> res = new ArrayList<>();
		for(Publicacion publicacion : publicaciones) {
			DTOPublicacion dtoPublicacion = new DTOPublicacion(publicacion);
			res.add(dtoPublicacion);
		}
		Collections.sort(res, Comparator.comparingLong(DTOPublicacion::getIdPublicacion).reversed());
		return res;
	}

	private List<DTOMultimedia> getGalerias(List<Multimedia> galerias) {
		List<DTOMultimedia> res = new ArrayList<>();
		for(Multimedia galeria : galerias) {
			DTOMultimedia dtoGaleria = new DTOMultimedia(galeria);
			res.add(dtoGaleria);
		}
		return res;
	}

	private List<DTOInteres> getIntereses(List<Interes> intereses) {
		List<DTOInteres> res = new ArrayList<>();
		for(Interes interes : intereses) {
			DTOInteres dtoInteres = new DTOInteres(interes);
			res.add(dtoInteres);
		}
		return res;
	}

	public DTOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(DTOUsuario usuario) {
		this.usuario = usuario;
	}

	public List<DTOInteres> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<DTOInteres> intereses) {
		this.intereses = intereses;
	}

	public List<DTOMultimedia> getGalerias() {
		return galerias;
	}

	public void setGalerias(List<DTOMultimedia> galerias) {
		this.galerias = galerias;
	}

	public List<DTOPublicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<DTOPublicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
}
