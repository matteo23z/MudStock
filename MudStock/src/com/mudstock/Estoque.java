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
