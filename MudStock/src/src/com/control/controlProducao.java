package com.control;

import javax.swing.JOptionPane;

import com.mudstock.Producao;

import util.DB;

public class controlProducao {
	
	public Producao Inserir(String nome,int idProduto,int idFuncionario,String dataI,String dataF,String quant,String status,String obs) {
		
		Producao Prod = new Producao(nome,idProduto,dataI,dataF,quant, status,obs);
		DB c = new DB();
		try {
			String sql = "Insert into Producao (nome,idProduto,idFuncionario,datainicial,datafinal,quantidade,pstatus,observacao) values('"
					+ Prod.getNome() + "', " + Prod.getIdProduto() + ", " + idFuncionario + ", '"
					 + Prod.getDataInicial() + "', '" + Prod.getDataFinal() + "', " + Prod.getQuantidade() +
					",'" + Prod.getStatus() + "','" + Prod.getObs() + "')";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Produção criada com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro na criação da prdução!");
		}finally {
			c.close();
		}
		return null;
	}
	public Producao Alterar(String sql) {
		DB c = new DB();
		try {
		c.Comando(sql);
		JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao alterar");
		}finally {
			c.close();
		}
		return null;
	}
	public Producao Buscar() {
		return null;
	}
	public Producao Excluir(int id) {
		DB c = new DB();
		try {
			String sql = "Delete from Producao where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao tentar deletar");
		}finally {
			c.close();
		}
		return null;
	}
}
