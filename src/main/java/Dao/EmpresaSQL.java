package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import Dao.BD;

public class EmpresaSQL {
	private Connection conn = null;
	
	private void createTables() {
		String sql = null;
		if (BD.typeDB.equals("sqlite")) {
			sql = """
						CREATE TABLE IF NOT EXISTS agenda (
							uuid STRING PRIMARY KEY,
							nombre STRING NOT NULL,
							telefono STRING,
							edad INTEGER
						)
					""";
		}
		if (BD.typeDB.equals("mariadb")) {
			sql = """
						CREATE TABLE IF NOT EXISTS agenda (
						  uuid CHAR(36) PRIMARY KEY,
						  nombre VARCHAR(255) NOT NULL,
						  telefono VARCHAR(255) DEFAULT NULL,
						  edad INT DEFAULT NULL,
						  PRIMARY KEY (uuid)
						)
					""";
		}
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
		}
	}
}
