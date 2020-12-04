package com.mudstock;

public class Estoque {
	private int id;
	private String nome;
	private String data;
	private int quantidade;
	private String obs;
	
	public Estoque() {
	}
	
	public Estoque(int id) {
		this.id = id;
	}
	
	public Estoque(String nome, String data, int quantidade, float tamanho, String obs) {
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
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
}
