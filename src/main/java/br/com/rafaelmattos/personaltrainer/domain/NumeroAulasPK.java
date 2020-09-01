package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


//Atributos Embedded (NumeroAulasPK)
@Embeddable
public class NumeroAulasPK implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "agendamentos_id")
	private Agendamentos agendamentos;
	
	@ManyToOne
	@JoinColumn(name = "aulas_id")
	private Aulas aulas;
	
	public Agendamentos getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(Agendamentos agendamentos) {
		this.agendamentos = agendamentos;
	}
	public Aulas getAulas() {
		return aulas;
	}
	public void setAulas(Aulas aulas) {
		this.aulas = aulas;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agendamentos == null) ? 0 : agendamentos.hashCode());
		result = prime * result + ((aulas == null) ? 0 : aulas.hashCode());
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
		NumeroAulasPK other = (NumeroAulasPK) obj;
		if (agendamentos == null) {
			if (other.agendamentos != null)
				return false;
		} else if (!agendamentos.equals(other.agendamentos))
			return false;
		if (aulas == null) {
			if (other.aulas != null)
				return false;
		} else if (!aulas.equals(other.aulas))
			return false;
		return true;
	}

}
