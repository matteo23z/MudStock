package com.mudstock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB {

	//	 variavel que faz a conexão	
	private Connection com;				
	public Connection getCom() {
		return com;
	}
	public void setCom(Connection com) {
		this.com = com;
	}
	//	 variavel que da os comandos de SQL	
	private Statement st;				
	//	Variaveis que fazem a url pra conexão do banco
	private String driver, user, pass, endereco;
	private ResultSet rs;

	public DB() {
		try {
			// Dados da conexão
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
				     "instance=SQLEXPRESS;databaseName=DBMudStock;user=admin;password=admin";
			
				  com = DriverManager.getConnection(connectionUrl);

			//Variavel que permite a execução de comandos sql 
			st = com.createStatement();

		}catch (Exception e){

			JOptionPane.showMessageDialog(null,"Nao foi possivel conectar ao banco\n:"+e.toString(), "Alerta",JOptionPane.INFORMATION_MESSAGE );

		}

	}

	//	 Funcao que executa a sql
	public void Comando(String sql){

		try{
			st.executeUpdate(sql);
		}

		catch(Exception e){

			JOptionPane.showMessageDialog(null,"Erro na operacao requisitada\n"+e.toString(), "Alerta",JOptionPane.INFORMATION_MESSAGE );
		}
	}

	public ResultSet Select(String sql){
		try{
			rs = st.executeQuery(sql);

		}
		catch(Exception e){

			JOptionPane.showMessageDialog(null,"Erro no select\n"+e.toString(), "Alerta",JOptionPane.INFORMATION_MESSAGE );
		}

		return(rs);
	}

	//Fecha a conexao do banco
	public void close(){
		try{
			com.close();
			JOptionPane.showMessageDialog(null,"Conexção Fechada");

		}catch (Exception e){

			JOptionPane.showMessageDialog(null,"Erro para fechar a conexao\n"+e.toString(), "Alerta",JOptionPane.INFORMATION_MESSAGE );
		}
	}
	//
	public boolean isConnected(){
		try{
			if(com.isClosed() == true)
				return false;
			else
				return true;

		}catch (Exception e){
			return false;
		}
	}
	
	public void consulta() throws SQLException{
		ResultSet rs = Select("select * from tables");
		while(rs.next()){
			String nome = rs.getString("nome");
			String end = rs.getString("end");
		}
	}
}
