package com.mudstock;

public class Producao {
	private int id;
	private String nome;
	private int idProduto;
	private int idFuncionario;
	private String dataInicial;
	private String dataFinal;
	private String quantidade;
	private String status;
	private String obs;
	
	public Producao () {
	}
	
	public Producao(String nome,int idProduto,String dataInicial,String dataFinal,String quantidade,String status,String obs) {
		this.nome = nome;
		this.setIdProduto(idProduto);
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.quantidade = quantidade;
		this.status = status;
		this.obs = obs;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
