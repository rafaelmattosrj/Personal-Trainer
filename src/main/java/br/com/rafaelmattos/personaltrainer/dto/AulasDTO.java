package br.com.rafaelmattos.personaltrainer.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.rafaelmattos.personaltrainer.domain.Aulas;

public class AulasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String uuid = UUID.randomUUID().toString();
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yy HH:mm")
	private Date agendado;

	@NotNull
	private String local;

	@NotNull
	private String tipoAula;

	public AulasDTO() {
	}

	public AulasDTO(Aulas obj) {
		id = obj.getId();
		uuid = obj.getUuid();
		agendado = obj.getAgendado();
		local = obj.getLocal();
		tipoAula = obj.getTipoAula();
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

}