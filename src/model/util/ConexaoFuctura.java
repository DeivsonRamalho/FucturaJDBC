package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFuctura {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "ISABELLABD";
	private String password = "Isabella16";

	public Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				System.out.println("Banco Conectado");
			}
		} catch (Exception e) {
			System.out.println("Erro na conexão com o banco. " + e.getMessage());

		}
		return conn;
		
	}

	public void fecharConexao(Connection conn) {
		try {
			conn.close();
		}catch(SQLException e) {
			System.out.println("Erro ao fechar conexão. " + e.getMessage());
		}
	}
}
