package com.mudstock;

public class Controle {
	private int id;
	private boolean entrada;
	private boolean saida;
	private double valorPago;
	private int quantidade;
	private String dataEntrada;
	private String dataSaida;
	
	public Controle() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEntrada() {
		return entrada;
	}
	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}
	public boolean isSaida() {
		return saida;
	}
	public void setSaida(boolean saida) {
		this.saida = saida;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	
}
