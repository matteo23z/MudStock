package com.mudstock;

public class Funcionario {
	private int id;
	private String nome;
	private String nomeUser;
	private String email;
	private int senha;
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
	public Funcionario alterar(int Id, String nomeuser, Funcionario list) {
		return null;
	}
	public Funcionario buscar(int Id, String nomeuser, Funcionario list) {
		return null;
	}
	public Funcionario excluir(int Id, String nomeuser, Funcionario list) {
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

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}


}
	

