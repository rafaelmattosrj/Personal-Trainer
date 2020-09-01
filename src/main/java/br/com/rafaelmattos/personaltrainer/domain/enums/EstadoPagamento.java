package br.com.rafaelmattos.personaltrainer.domain.enums;

////Implementação do Enum
public enum EstadoPagamento {

PENDENTE(1, "PENDENTE"), 
PAGO(2, "PAGO");

private int cod;
private String descricao;

private EstadoPagamento(int cod, String descricao) {
	this.cod = cod;
	this.descricao = descricao;
}

public int getCod() {
	return cod;
}

public String getDescricao() {
	return descricao;
}

public static EstadoPagamento toEnum(Integer id) {
	if (id == null) {
		return null;
	}
	
	for (EstadoPagamento x : EstadoPagamento.values()) {
		if (id.equals(x.getCod())) {
			return x;
		}
	}
	
	throw new IllegalArgumentException("Id inválido " + id);
}
}

