package com.mudstock;

import java.sql.ResultSet;

public class Funcionario {
	private int id;
	private String nome;
	private String nomeUser;
	private String email;
	private String senha;
	private String dataNasc;
	
	public Funcionario() {
	
	}
	
	public Funcionario inserir() {
		DB c = new DB();
		
		String sql = "Insert into Funcionario (nome,datanascimento,nomeuser,email,senha) values('" 
				+ nome + "', '" + nomeUser + "', '" + email + "', " + senha + ")";
		c.Comando(sql);
		return null;
	}
	public Funcionario alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update Funcionario set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public void buscarAll() {
		DB c = new DB();
		String sql = "Select * from Funcionario";
		ResultSet rs = c.Select(sql);
		
		try {
			System.out.println("ID - NOME - DATANACIMENTO - USER - EMAIL - SENHA");
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String datanasc = rs.getString("datanacimento");
				String user = rs.getString("nomeuser");
				String email = rs.getString("email");
				int sen = rs.getInt("senha");
				System.out.println(id+" - "+nome+" - "+datanasc+" - "+user+" - "+email+" - "+sen);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Funcionario excluir(int Id) {
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

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}


}
	

