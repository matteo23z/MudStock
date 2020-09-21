package com.mudstock;

public class Estoque {
	private int id;
	private String nome;
	private String data;
	private int quantidade;
	private float tamanho;
	private String obs;
	
	public Estoque() {
		
	}
	public Estoque Registrar(int id, String nome, String data, int quantidade,float tamanho, String obs) {
		DB c = new DB();
		
		String sql = "Insert into Estoque (nome,data,qunatidade,tamanho,obs) values('" 
				+ nome + "', '" + data + "', '" + quantidade + "', " + tamanho + "', '" + obs + "')";
		c.Comando(sql);
		return null;
	}
	public Estoque Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update Funcionario set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public Estoque Buscar() {
		return null;
	}
	public Estoque Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from Funcionario where id="+id;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getTamanho() {
		return tamanho;
	}
	public void setTamanho(float tamanho) {
		this.tamanho = tamanho;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
