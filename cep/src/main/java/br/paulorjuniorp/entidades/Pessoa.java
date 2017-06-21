 package br.paulorjuniorp.entidades;

public class Pessoa {

	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private String celular;
	private Logradouro logradouro = new Logradouro();
	private String numero;
	private String complemento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	public Logradouro getLogradouro() {
		return logradouro;
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
	
	
}
