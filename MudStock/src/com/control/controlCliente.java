package com.control;

import javax.swing.JOptionPane;

import com.mudstock.Cliente;

import util.DB;

public class controlCliente {
	public Cliente Inserir() {
		DB c = new DB();
		try {
			String sql = "Insert into Cliente (nome,email,rg,cpf,telefone,situacao) values('" 
				+ nome + "', '" + email + "', '" + rg + "', " + cpf + "', '" + telefone + "', '" + situacao +  "')";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Inserido com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao inserir cliente ");
		}finally {
			c.close();
		}
		return null;
	}
	public Cliente Alterar(int id, String coluna, String var) {
		DB c = new DB();
		try {
			String sql = "Update Cliente set "+coluna+"='"+var+" where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Alterado com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao ALterar cliente ");
		}finally {
			c.close();
		}
		return null;
	}
	public Cliente Buscar() {
		return null;
	}
	public Cliente Excluir(int id) {
		DB c = new DB();
		try {
			String sql = "Delete from Cliente where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Deletado com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao deletar cliente ");
		}finally {
			c.close();
		}
		return null;
	}
}
