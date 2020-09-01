package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.rafaelmattos.personaltrainer.domain.enums.EstadoPagamento;

@Entity
public class Pagamentos  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid = UUID.randomUUID().toString();
    
	@NotNull
	@JsonFormat(pattern = "dd/MM/yy")
	@Column(name = "data_de_vencimento")
    private Date dataVencimento;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yy HH:mm")
	@Column(name = "data_do_pagamento")
    private Date dataPagamento;

	@NotNull
	@Column(name = "tipo_do_pagamento")
    private String tipoPagamento;
	
	@NotNull
	@Column(name = "estado_do_pagamento")
    private Integer estadoPagamento;
		
	@OneToOne
	@JoinColumn(name = "agendamento_id")
	@MapsId
	private Agendamentos agendamentos;
	
	public Pagamentos() {
	}

	public Pagamentos(Integer id, String uuid, Date dataVencimento,
			Date dataPagamento, String tipoPagamento, EstadoPagamento estadoPagamento, Agendamentos agendamentos) {
		this.id = id;
		this.uuid = uuid;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.tipoPagamento = tipoPagamento;
		this.estadoPagamento = (estadoPagamento==null) ? null : estadoPagamento.getCod();
		this.agendamentos = agendamentos;
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
	
	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCod();
	}

	public Agendamentos getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(Agendamentos agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	
	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
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
		Pagamentos other = (Pagamentos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
