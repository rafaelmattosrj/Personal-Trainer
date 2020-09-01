package br.com.rafaelmattos.personaltrainer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.rafaelmattos.personaltrainer.domain.enums.Perfil;

@Entity
public class Alunos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "uuid", nullable = false, length = 36, unique = true)
	private String uuid = UUID.randomUUID().toString();

	@NotNull
	@Column(name = "nome_completo", nullable = false, length = 100)
	private String nomeCompleto;

	@NotNull
	@Column(name = "email", nullable = false, length = 100)
	@javax.validation.constraints.Email
	private String email;

	@NotNull
	@Max(11)
	@Min(11)
	@Column(name = "cpf", nullable = false, length = 12)
	private String cpf;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yy")
	@Column(name = "data_de_nascimento")
	private String dataDeNascimento;

	@NotNull
	@JsonIgnore
	private String senha;

	@OneToOne(mappedBy = "alunos", cascade = CascadeType.ALL)
	private Enderecos enderecos;

	//Mapeamento dos telefones (ElementCollection)
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private List<String> telefones = new ArrayList<>(); 
		
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "alunos")
	private List<Agendamentos> agendamentos = new ArrayList<>(); 
	
	public Alunos() {
		addPerfil(Perfil.ALUNO);
	}

	public Alunos(Integer id, String uuid, String nomeCompleto, String email, String cpf, String dataDeNascimento,
			String senha) {
		this.id = id;
		this.uuid = uuid;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.senha = senha;

		addPerfil(Perfil.ALUNO);
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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public Enderecos getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}
		
	public List<Agendamentos> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamentos> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
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
		Alunos other = (Alunos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
