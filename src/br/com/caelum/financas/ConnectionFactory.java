package br.com.caelum.financas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/financas", "root", "pc1000");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
