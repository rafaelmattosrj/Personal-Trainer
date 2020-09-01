package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enderecos implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid = UUID.randomUUID().toString();
    
	@NotNull
    @Column(name = "logradouro", nullable = false, length = 100)
	private String logradouro;
	
	@NotNull
    @Column(name = "numero", nullable = false, length = 20)
	private String numero;
	
    @Column(name = "complemento", nullable = false, length = 20)
	private String complemento;
	
	@NotNull
    @Column(name = "bairro", nullable = false, length = 25)
	private String bairro;
	
	@NotNull
    @Column(name = "cep", nullable = false, length = 8)
	private String cep;	
	
	@NotNull
    @Column(name = "cidade", nullable = false, length = 45)
	private String cidade;	
	
	@NotNull
    @Column(name = "estado", nullable = false, length = 2)
	private String estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="alunos")
	private Alunos alunos;
	
	public Enderecos() {
	}

	public Enderecos(Integer id, String uuid, String logradouro, String numero, String complemento, String bairro, String cep,
			String cidade, String estado, Alunos alunos) {
		this.id = id;
		this.uuid = uuid;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.alunos = alunos;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Alunos getAluno() {
		return alunos;
	}

	public void setAluno(Alunos alunos) {
		this.alunos = alunos;
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
		Enderecos other = (Enderecos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
