package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Aulas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "uuid", nullable = false, length = 36, unique = true)
	private String uuid = UUID.randomUUID().toString();
	
	@NotNull
	private String tipoAula;
	
	@NotNull
	private String local;
	
	@NotNull
	private Double valor;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yy HH:mm")
	private Date agendado;

	@JsonIgnore
	@OneToMany(mappedBy = "id.aulas")
	private Set<NumeroAulas> numeroAulas = new HashSet<>();
	
	public Aulas() {
	}
	
	public Aulas(Integer id, String uuid, Date agendado, String local, String tipoAula, Double valor) {
		this.id = id;
		this.uuid = uuid;
		this.agendado = agendado;
		this.local = local;
		this.tipoAula = tipoAula;
		this.valor = valor;
	}
	
	@JsonIgnore
	public List<Agendamentos> getAgendamentos() {
		List<Agendamentos> lista = new ArrayList<>();
		for (NumeroAulas x : numeroAulas) {
			lista.add(x.getAgendamentos());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getAgendado() {
		return agendado;
	}

	public void setAgendado(Date agendado) {
		this.agendado = agendado;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTipoAula() {
		return tipoAula;
	}

	public void setTipoAula(String tipoAula) {
		this.tipoAula = tipoAula;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Set<NumeroAulas> getNumeroAulas() {
		return numeroAulas;
	}

	public void setNumeroAulas(Set<NumeroAulas> numeroAulas) {
		this.numeroAulas = numeroAulas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Aulas other = (Aulas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
