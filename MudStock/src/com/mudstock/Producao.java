package com.mudstock;

public class Producao {
	private int id;
	private String nome;
	private String dataInicial;
	private String dataFinal;
	private int quantidade;
	private String status;
	private String obs;
	
	public Producao() {
		
	}
	public Producao Registrar() {
		DB c = new DB();
		
		String sql = "Insert into Producao (nome,datainicial,datafinal,quantidade,status,obs) values('" 
				+ nome + "', '" + dataInicial + "', '" + dataFinal + "', " + quantidade + "', " + status + "','" + obs + "')";
		c.Comando(sql);
		return null;
	}
	public Producao Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update Producao set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public Producao Buscar() {
		return null;
	}
	public Producao Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from Producao where id="+id;
		c.Comando(sql);
		return null;
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
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
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
	
	
}
