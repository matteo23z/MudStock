package com.mudstock;

import java.sql.ResultSet;
import java.sql.SQLException;

public class controlDB {
	public controlDB consultaAll() throws SQLException {
		DB c = new DB();
		ResultSet rs = c.Select("select * from Funcionario");
		while(rs.next()) {
			String nome = rs.getString("nome");
		}
		return null;
	}
}
