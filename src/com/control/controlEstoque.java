package com.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mudstock.Estoque;

import util.DB;

public class controlEstoque {
	public Estoque Inserir(int id , String data, String quantidade, String obs) {
		DB c = new DB();
		try {
			String sql = "Insert into Estoque (idProduto,dataAtualizacao,quantidade,observacao) values(" 
					+ id + ", '" + data + "', " + quantidade + ",'"+ obs +"')";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Criação realizado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		return null;
	}
	public Estoque Alterar(String sql) {
		DB c = new DB();
		try {
			c.Comando(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		return null;
	}
	public Estoque Buscar() {
		return null;
	}
	public Estoque Excluir(int id) {
		DB c = new DB();
		try {
			String sql = "Delete from Estoque where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Cadastro deletado com sucesso");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Erro ao tentar deletar cadastro");
		}finally {
			c.close();
		}
		return null;
	}
	public String checkBusca (String Nome){
		String sql = null;

		if(!Nome.isEmpty()) {
			if(sql==null) {
				sql = "Select * from Estoque where nome='"+Nome+"'";
				System.out.println(sql);
			}
		}
		return sql;
		
	}
}
