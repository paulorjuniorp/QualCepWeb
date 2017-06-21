package br.paulorjuniorp.entidades;

public class Logradouro {

	private Long id;
	private String cep;
	private String nome;
	private Bairro bairro = new Bairro();
	private Cidade cidade = new Cidade();
	private Estado estado = new Estado();
	private TiposLogradouro tipoLogradouro = new TiposLogradouro();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public TiposLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(TiposLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
}
