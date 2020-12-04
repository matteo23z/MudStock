package com.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mudstock.Produtos;

import util.DB;

public class controlProdutos {
	public Produtos Inserir(String nome, int TipoProduto ,String descricao , String obs ,String marca , String situacao) {
		DB c = new DB();
		try {
			String sql = "Insert into Produto (nome,IdTipoProduto,descricao,observacao,marca,situacao) values('" 
				+ nome + "', " + TipoProduto + ", '" + descricao + "', '" + obs + "','" + marca + "', '" + situacao + "')";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Inserirdo com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao inserir");
		}finally {
			c.close();
		}
		return null;
	}
	public Produtos Alterar(int id, String coluna, String var) {
		DB c = new DB();
		try {
			String sql = "Update Produto set"+coluna+"='"+var+" where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Alteradodo com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao alterar");
		}finally {
			c.close();
		}
		return null;
	}
	public Produtos Alterar(String sql) {
		DB c = new DB();
		try {
			c.Comando(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao alterar");
		}finally {
			c.close();
		}
		return null;
	}
	public Produtos Excluir(int id) {
		DB c = new DB();
		try {
			String sql = "update Funcionario set situacao='I' where id"+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao deletar");
		}finally {
			c.close();
		}
		return null;
	}
	public String checkBusca (String Nome, String Tipo, String Marca, String  Situacao , String Descr, String  Obs){
		String sql = null;

		if(!Nome.isEmpty()) {
			if(sql==null) {
				sql = "Select * from Produto where nome='"+Nome+"'";
				System.out.println(sql);
			}
		}
		if(!Tipo.isEmpty()) {
			int idTipo = 0;
			DB c = new DB();
			try {    
				ResultSet rs = c.Select("Select id from tipoproduto where nome='"+Tipo+"'");
				while(rs.next()) {
					idTipo = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				c.close();
			}
			if(idTipo!=0) {
				if(sql==null) {
					sql = "Select * from Produto where idTipoProduto='"+idTipo+"'";
				}else {
					sql = sql+("and idTipoProduto='"+idTipo+"'");
				}
			}
		}
		if(!Marca.isEmpty()) {
			if(sql==null) {
				sql = "Select * from Produto where Marca='"+Marca+"'";
			}else {
				sql = sql+("and Marca='"+Marca+"'");
			}
		}
		if(!Situacao.isEmpty()) {
			if(sql==null) {
				sql = "Select * from Produto where situacao='"+Situacao+"'";
			}else {
				sql = sql+("and situacao='"+Situacao+"'");
			}
		}
		if(!Descr.isEmpty()) {
			if(sql==null) {
				sql = "Select * from Produto where Descricao='"+Descr+"'";
			}else {
				sql = sql+("and Descricao='"+Descr+"'");
			}
		}
		if(!Obs.isEmpty()) {
			if(sql==null) {
				sql = "Select * from Produto where Observacao='"+Obs+"'";
			}else {
				sql = sql+("and Observacao='"+Obs+"'");
			}
		}
		return sql;
		
	}
	public Produtos InserirTipo(String nome, String situacao) {
		DB c = new DB();
		try {
			String sql = "Insert into TipoProduto (nome,situacao) values('" 
				+ nome +"', '" + situacao + "')";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Inserirdo com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao inserir");
		}finally {
			c.close();
		}
		return null;
	}
	public Produtos AlterarTipo(String sql) {
		DB c = new DB();
		try {
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Alteradodo com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao alterar");
		}finally {
			c.close();
		}
		return null;
	}
	public Produtos ExcluirTipo (int id) {
		DB c = new DB();
		try {
			String sql = "Delete from tipoProduto where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao deletar");
		}finally {
			c.close();
		}
		return null;
	}
	public String buscarNomeProduto(int id) {
		DB c = new DB();
		String sql = "Select nome from Produto where id="+id;
		String nome = null;
		try {
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				nome = rs.getString("nome");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		return nome;
	}
	public int buscarIdProduto(String nome) {
		DB c = new DB();
		String sql = "Select id from Produto where nome='"+nome+"'";
		int id = 0;
		try {
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		return id;
	}
	public String buscarNomeTipo(int id) {
		DB c = new DB();
		String sql = "Select nome from TipoProduto where id="+id;
		String nome = null;
		try {
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				nome = rs.getString("nome");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		return nome;
	}
	public int buscarIdTipo(String nome) {
		DB c = new DB();
		String sql = "Select id from TipoProduto where nome='"+nome+"'";
		int id = 0;
		try {
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		return id;
	}
	public String checkBuscaTipo (String Nome,String  Situacao){
		String sql = null;

		if(!Nome.isEmpty()) {
			if(sql==null) {
				sql = "Select * from TipoProduto where nome='"+Nome+"'";
				System.out.println(sql);
			}
		}
		if(!Situacao.isEmpty()) {
			if(sql==null) {
				sql = "Select * from TipoProduto where situacao='"+Situacao+"'";
			}else {
				sql = sql+("and situacao='"+Situacao+"'");
			}
		}
		return sql;
		
	}
}
