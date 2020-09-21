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
	public Controle Registrar() {
		DB c = new DB();
		
		String sql = "Insert into Controle (entrada,saida,valorpago,quantidade,dataentrada,datasaida) values('" 
				+ entrada + "', '" + saida + "', '" + valorPago + "','" + quantidade + "','" + dataEntrada + "','" + dataSaida + "')";
		c.Comando(sql);
		return null;
	}
	public Controle Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update Controle set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public Controle Buscar() {
		return null;
	}
	public Controle Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from Controle where id="+id;
		c.Comando(sql);
		return null;
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
