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
		this.id = id;
		this.nome = nome;
		this.data = data; 
		this.quantidade = quantidade;
		this.tamanho = tamanho;
		this.obs = obs;
		return null;
	}
	public Estoque Alterar(int Id, String nome) {
		return null;
	}
	public Estoque Buscar(int Id, String nome) {
		return null;
	}
	public Estoque Excluir(int Id, String nome) {
		return null;
	}
}
