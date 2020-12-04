package com.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.mudstock.Funcionario;

import util.DB;

public class controlFuncionario {
	public Funcionario Inserir(String nome,String nomeUser,String email, String senha,String dataNasc) {
		Funcionario Func = new Funcionario(nome,nomeUser,email,senha,dataNasc);
		DB c = new DB();
		try {
			String sql = "Insert into Funcionario (nome,datanacimento,nomeuser,email,senha) values('" 
					+ Func.getNome() + "','" + Func.getDataNasc() + "','" + Func.getNomeUser() + "', '" +
					Func.getEmail() + "', " + Func.getSenha() + ")";
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso");
		}finally {
			c.close();
		}
		return null;
	}
	public Funcionario Alterar(int id, String coluna, String var) {
		DB c = new DB();
		try {
			String sql = "Update Funcionario set "+coluna+"='"+var+" where id="+id;
			JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso");
			c.Comando(sql);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao realizar Alteração");
		}finally {
			c.close();
		}
		return null;
	}
	public void BuscarAll() {
		DB c = new DB();
		String sql = "Select * from Funcionario";
		ResultSet rs = c.Select(sql);
		
		try {
			System.out.println("ID - NOME - DATANACIMENTO - USER - EMAIL - SENHA");
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String datanasc = rs.getString("datanacimento");
				String user = rs.getString("nomeuser");
				String email = rs.getString("email");
				int sen = rs.getInt("senha");
				System.out.println(id+" - "+nome+" - "+datanasc+" - "+user+" - "+email+" - "+sen);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
	}
	public Funcionario Excluir(int id) {
		DB c = new DB();
		try {
			String sql = "Delete from Funcionario where id="+id;
			c.Comando(sql);
			JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao tentar Deletar");
		}finally {
			c.close();
		}
		return null;
	}
	
	public boolean checkLogin(String nuser, String sen) {
		DB c = new DB();
		String sql = "Select nomeuser,senha from Funcionario where nomeuser='"+nuser+"'";
		ResultSet rs = c.Select(sql);
		String user, senha;
		
		try {
			while(rs.next()) {
				user = rs.getString("nomeuser");
				senha = rs.getString("senha");
				return user.equals(nuser) && senha.equals(sen);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	return false;
	}
	
	
	public boolean checkCadastro(String nuser, String mail) throws SQLException {
		DB c = new DB();
		String sql = "Select nomeuser, email from Funcionario where nomeuser='"+nuser+"' or email='"+mail+"'";
		ResultSet rs = c.Select(sql);
		String user = null, email = null;
		boolean resultado = false;
		try {
			while(rs.next()) {
				user = rs.getString("nomeuser");
				email = rs.getString("email");
			}
			if(user==null && email==null) {
				resultado = true;
			}else {
				resultado = false;
			}
		}finally {
			c.close();
		}
	return resultado;
	}
	
	public boolean checkEmail(String email) {
		    boolean isEmailIdValid = false;
		    if (email != null && email.length() > 0) {
		        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		        Matcher matcher = pattern.matcher(email);
		        if (matcher.matches()) {
		            isEmailIdValid = true;
		        }
		    }
		    return isEmailIdValid;
		}
	
}
