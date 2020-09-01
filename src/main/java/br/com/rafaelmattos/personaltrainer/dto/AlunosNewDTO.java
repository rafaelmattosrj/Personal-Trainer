package br.com.rafaelmattos.personaltrainer.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.rafaelmattos.personaltrainer.services.validation.AlunoInsert;

	//Checklist:
	// Criar a anotação customizada
	// Criar o Valitator personalizado para esta anotação e para o nosso DTO
	// Programar o Validator, fazendo testes e inserindo as mensagens de erro
	// Anotar nosso DTO com a nova anotação criada
	@AlunoInsert
	public class AlunosNewDTO implements Serializable {	
		private static final long serialVersionUID = 1L;

		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
		private String nomeCompleto;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Email(message="Email inválido")
		private String email;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
		private String cpf;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String dataDeNascimento;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String telefone1;
		
		private String telefone2;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=6, max=12, message="O tamanho deve ser entre 6 e 12 caracteres")
		private String senha;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String logradouro;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String numero;
		
		private String complemento;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String bairro;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String cidade;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String estado;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String cep;	
		
		private Date agendado;
		
		private String local;

		public AlunosNewDTO() {
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

		public String getTelefone1() {
			return telefone1;
		}

		public void setTelefone1(String telefone1) {
			this.telefone1 = telefone1;
		}

		public String getTelefone2() {
			return telefone2;
		}

		public void setTelefone2(String telefone2) {
			this.telefone2 = telefone2;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
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

		public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
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
		
}
