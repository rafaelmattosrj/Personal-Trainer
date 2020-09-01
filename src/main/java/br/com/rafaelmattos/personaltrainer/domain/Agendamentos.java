package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Agendamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid = UUID.randomUUID().toString();
    
	@JsonFormat(pattern = "dd/MM/yy HH:mm")
	private Date dataRegistro;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "agendamentos")
	private Pagamentos pagamentos;
		
	@ManyToOne
	@JoinColumn(name = "alunos_id")
	private Alunos alunos;
		
	@OneToMany(mappedBy = "id.agendamentos")
	private Set<NumeroAulas> numeroAulas = new HashSet<>();

	public Agendamentos() {
	}

	public Agendamentos(Integer id, String uuid, Date dataRegistro, Alunos alunos) {
		this.id = id;
		this.uuid = uuid;
		this.dataRegistro = dataRegistro;
		this.alunos = alunos;
	}

	public double getValorTotal() {
		double soma = 0.0;
		for(NumeroAulas na : numeroAulas) {
			soma = soma + na.getSubTotal();
		}
			return soma;
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

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Alunos getAlunos() {
		return alunos;
	}

	public void setAlunos(Alunos alunos) {
		this.alunos = alunos;
	}

	public Set<NumeroAulas> getNumeroAulas() {
		return numeroAulas;
	}

	public void setNumeroAulas(Set<NumeroAulas> numeroAulas) {
		this.numeroAulas = numeroAulas;
	}
	
	public Pagamentos getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Pagamentos pagamentos) {
		this.pagamentos = pagamentos;
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
		Agendamentos other = (Agendamentos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}