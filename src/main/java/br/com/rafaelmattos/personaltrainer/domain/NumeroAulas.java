package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NumeroAulas implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private NumeroAulasPK id = new NumeroAulasPK();

	private Double desconto;
	private Integer vezes;
	private Double valor;

	public NumeroAulas() {
	}

	public NumeroAulas(Agendamentos agendamentos, Aulas aulas, Double desconto, Integer vezes, Double valor) {

		id.setAgendamentos(agendamentos);
		id.setAulas(aulas);
		this.desconto = desconto;
		this.vezes = vezes;
		this.valor = valor;
	}

	public double getSubTotal() {
		return (valor - desconto) * vezes;
	}

	@JsonIgnore
	public Agendamentos getAgendamentos() {
		return id.getAgendamentos();
	}

	public void setAgendamentos(Agendamentos agendamentos) {
		id.setAgendamentos(agendamentos);
	}

	public Aulas getAulas() {
		return id.getAulas();
	}

	public void setAulas(Aulas aulas) {
		id.setAulas(aulas);
	}

	public NumeroAulasPK getId() {
		return id;
	}

	public void setId(NumeroAulasPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getVezes() {
		return vezes;
	}

	public void setQuantidade(Integer vezes) {
		this.vezes = vezes;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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
		NumeroAulas other = (NumeroAulas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getAulas().getTipoAula());
		builder.append(", Qte: ");
		builder.append(getVezes());
		builder.append(", Hora/aula: ");
		builder.append(nf.format(getValor()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
