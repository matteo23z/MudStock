package com.mudstock;

public class Funcionario {
	private int id;
	private String nome;
	private String nomeUser;
	private String email;
	private String senha;
	private String dataNasc;
	
	public Funcionario() {
	}
	
	public Funcionario(int id) {
		this.id = id;
	}
	
	public Funcionario(String nome,String nomeUser,String email, String senha,String dataNasc) {
		this.nome = nome;
		this.nomeUser = nomeUser;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
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