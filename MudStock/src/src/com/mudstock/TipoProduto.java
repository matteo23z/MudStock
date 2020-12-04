package com.mudstock;

import util.DB;

public class TipoProduto {
	private int id;
	private String nome;
	private String situacao;
	
	public TipoProduto() {
		
	}
	public TipoProduto Registrar() {
		DB c = new DB();
		
		String sql = "Insert into TipoProduto (nome,situacao) values('" 
				+ nome + "', '" + situacao + ")";
		c.Comando(sql);
		return null;
	}
	public TipoProduto Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update TipoProduto set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public TipoProduto Buscar() {
		return null;
	}
	public TipoProduto Excluir() {
		DB c = new DB();
		
		String sql = "Delete from TipoProduto where id="+id;
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
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
}
