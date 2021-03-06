package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuracion implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private boolean isEmailNotification;

	private String idPersona;
	
	public Configuracion() {
		
	}

	public Configuracion(boolean isEmailNotification) {
		this.altaPublicacion = true;
		this.altaContacto = true;
		this.reaccionPublicacion = true;
		this.comentarPublicacion = true;
		this.altaEvento = true;
		this.invitacionUsuario = true;
		this.salirEvento = true;
		this.recuperarContrasenia = true;
		this.bloquearUsuario = true;
		this.desbloquearUsuario = true;
		this.chatUsuario = true;
		this.bajaEvento = true;
		this.modificacionEvento = true;
		this.isEmailNotification = isEmailNotification;
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

	public boolean isEmailNotification() {
		return isEmailNotification;
	}

	public void setEmailNotification(boolean isEmailNotification) {
		this.isEmailNotification = isEmailNotification;
	}
}
