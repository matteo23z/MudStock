package com.mudstock;

import java.sql.ResultSet;

public class Produtos {
	private int id;
	private String nome;
	private String descricao;
	private String obs;
	private String marca;
	private String situacao;
	
	public Produtos(){
		
	}
	public Produtos Registrar() {
		DB c = new DB();
		
		String sql = "Insert into Produto (nome,descricao,obs,marca,situacao) values('" 
				+ nome + "', '" + descricao + "', '" + obs + "', " + marca + "', " + situacao + ")";
		c.Comando(sql);
		return null;
	}
	public Produtos Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update Produto set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public void BuscarAll() {
		DB c = new DB();
		String sql = "Select * from Produto";
		ResultSet rs = c.Select(sql);
		
		try {
			System.out.println("ID - NOME - DATANACIMENTO - USER - EMAIL - SENHA");
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String obs = rs.getString("obs");
				String marca = rs.getString("marca");
				String situacao = rs.getString("situacao");
				System.out.println(id+" - "+nome+" - "+descricao+" - "+obs+" - "+marca+" - "+situacao);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Produtos Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from Produto where id="+id;
		c.Comando(sql);
		return null;
	}
}
