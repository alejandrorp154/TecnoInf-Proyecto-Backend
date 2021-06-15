package com.javaee.pryectoBack.model;

import java.io.Serializable;

public class EventoUsuarioId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private int idEvento;

	public EventoUsuarioId(String idPersona, int idEvento) {
		this.idPersona = idPersona;
		this.idEvento = idEvento;
	}

    public EventoUsuarioId() {
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
        result = prime * result + ((idEvento == 0) ? 0 : idEvento);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventoUsuarioId other = (EventoUsuarioId) obj;
        if (idPersona == null) {
            if (other.idPersona != null)
                return false;
        } else if (!idPersona.equals(other.idPersona))
            return false;
        if (idEvento == 0) {
            if (other.idEvento != 0)
                return false;
        } else if (idEvento != other.idEvento)
            return false;
        return true;
    }
}
