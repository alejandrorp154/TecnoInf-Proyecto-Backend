package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOConfiguracion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idConfiguracion;
	private boolean altaPublicacion;
	private boolean altaContacto;
	private boolean reaccionPublicacion;
	private boolean comentarPublicacion;
	private boolean altaEvento;
	private boolean invitacionUsuario;
	private boolean salirEvento;
	private boolean recuperarContrasenia;
	private boolean bloquearUsuario;
	private boolean desbloquearUsuario;
	private boolean chatUsuario;
	private boolean bajaEvento;
	private boolean modificacionEvento;
	private String idPersona;
	
	public DTOConfiguracion() {
	}

	public DTOConfiguracion(int idConfiguracion, boolean altaPublicacion, boolean altaContacto,
			boolean reaccionPublicacion, boolean comentarPublicacion, boolean altaEvento, boolean invitacionUsuario,
			boolean salirEvento, boolean recuperarContrasenia, boolean bloquearUsuario, boolean desbloquearUsuario,
			boolean chatUsuario, boolean bajaEvento, boolean modificacionEvento, String idPersona) {
		super();
		this.idConfiguracion = idConfiguracion;
		this.altaPublicacion = altaPublicacion;
		this.altaContacto = altaContacto;
		this.reaccionPublicacion = reaccionPublicacion;
		this.comentarPublicacion = comentarPublicacion;
		this.altaEvento = altaEvento;
		this.invitacionUsuario = invitacionUsuario;
		this.salirEvento = salirEvento;
		this.recuperarContrasenia = recuperarContrasenia;
		this.bloquearUsuario = bloquearUsuario;
		this.desbloquearUsuario = desbloquearUsuario;
		this.chatUsuario = chatUsuario;
		this.bajaEvento = bajaEvento;
		this.modificacionEvento = modificacionEvento;
		this.idPersona = idPersona;
	}

	public int getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(int idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

	public boolean isAltaPublicacion() {
		return altaPublicacion;
	}

	public void setAltaPublicacion(boolean altaPublicacion) {
		this.altaPublicacion = altaPublicacion;
	}

	public boolean isAltaContacto() {
		return altaContacto;
	}

	public void setAltaContacto(boolean altaContacto) {
		this.altaContacto = altaContacto;
	}

	public boolean isReaccionPublicacion() {
		return reaccionPublicacion;
	}

	public void setReaccionPublicacion(boolean reaccionPublicacion) {
		this.reaccionPublicacion = reaccionPublicacion;
	}

	public boolean isComentarPublicacion() {
		return comentarPublicacion;
	}

	public void setComentarPublicacion(boolean comentarPublicacion) {
		this.comentarPublicacion = comentarPublicacion;
	}

	public boolean isAltaEvento() {
		return altaEvento;
	}

	public void setAltaEvento(boolean altaEvento) {
		this.altaEvento = altaEvento;
	}

	public boolean isInvitacionUsuario() {
		return invitacionUsuario;
	}

	public void setInvitacionUsuario(boolean invitacionUsuario) {
		this.invitacionUsuario = invitacionUsuario;
	}

	public boolean isSalirEvento() {
		return salirEvento;
	}

	public void setSalirEvento(boolean salirEvento) {
		this.salirEvento = salirEvento;
	}

	public boolean isRecuperarContrasenia() {
		return recuperarContrasenia;
	}

	public void setRecuperarContrasenia(boolean recuperarContrasenia) {
		this.recuperarContrasenia = recuperarContrasenia;
	}

	public boolean isBloquearUsuario() {
		return bloquearUsuario;
	}

	public void setBloquearUsuario(boolean bloquearUsuario) {
		this.bloquearUsuario = bloquearUsuario;
	}

	public boolean isDesbloquearUsuario() {
		return desbloquearUsuario;
	}

	public void setDesbloquearUsuario(boolean desbloquearUsuario) {
		this.desbloquearUsuario = desbloquearUsuario;
	}

	public boolean isChatUsuario() {
		return chatUsuario;
	}

	public void setChatUsuario(boolean chatUsuario) {
		this.chatUsuario = chatUsuario;
	}

	public boolean isBajaEvento() {
		return bajaEvento;
	}

	public void setBajaEvento(boolean bajaEvento) {
		this.bajaEvento = bajaEvento;
	}

	public boolean isModificacionEvento() {
		return modificacionEvento;
	}

	public void setModificacionEvento(boolean modificacionEvento) {
		this.modificacionEvento = modificacionEvento;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
}
