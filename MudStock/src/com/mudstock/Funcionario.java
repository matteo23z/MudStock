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
	
	public Funcionario Registrar(String nome, String nomeuser, String email, int senha,String datanasc) {
		this.nome = nome;
		this.nomeUser = nomeuser;
		this.email = email;
		this.senha = senha;
		this.dataNasc = datanasc;
		return null;
	}
	public Funcionario Alterar(int Id, String nomeuser, Funcionario list) {
		return list;
	}
	public Funcionario Buscar(int Id, String nomeuser, Funcionario list) {
		return list;
	}
	public Funcionario Excluir(int Id, String nomeuser, Funcionario list) {
		return list;
	}
}
