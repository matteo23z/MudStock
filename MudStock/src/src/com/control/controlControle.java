package com.control;

import javax.swing.JOptionPane;

import com.mudstock.Controle;

import util.DB;

public class controlControle {
	public Controle Inserir() {
		DB c = new DB();
		try {
			String sql = "Insert into Controle (entrada,saida,valorpago,quantidade,dataentrada,datasaida) values('" 
				+ entrada + "', '" + saida + "', '" + valorPago + "','" + quantidade + "','" + dataEntrada + "','" + dataSaida + "')";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Inserido com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao inserir controle ");
		}finally {
			c.close();
		}
		return null;
	}
	public Controle Alterar(int id, String coluna, String var) {
		DB c = new DB();
		try {
			String sql = "Update Controle set "+coluna+"='"+var+" where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Alterado com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao ALterar controle ");
		}finally {
			c.close();
		}
		return null;
	}
	public Controle Buscar() {
		return null;
	}
	public Controle Excluir(int id) {
		DB c = new DB();
		try {
			String sql = "Delete from Controle where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Deletado com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao deletar controle ");
		}finally {
			c.close();
		}
		return null;
	}
}
