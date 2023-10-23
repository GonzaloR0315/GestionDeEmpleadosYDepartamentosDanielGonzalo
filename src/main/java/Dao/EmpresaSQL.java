package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import Dao.BD;
import Model.Empleado;

public class EmpresaSQL {
	private Connection conn = null;
	
	public EmpresaSQL() {
		conn = BD.getConnection();
		createTables();
	}
	
	public void close() {
		BD.close();
	}
	
	/**
	 * Crea el esquema de la base de datos
	 * 
	 * @throws SQLException
	 */
	private void createTables() {
		String sql = null;
		if (BD.typeDB.equals("sqlite")) {
			sql = """
						CREATE TABLE IF NOT EXISTS Empleados (
							uuid CHAR(36) PRIMARY KEY,
							nombre STRING NOT NULL,
							telefono STRING,
							edad INTEGER
						)
						
						CREATE TABLE IF NOT EXISTS Departamentos (
								Id int PRIMARY KEY,
								nombre STRING NOT NULL,
								telefono STRING,
								edad INTEGER
							)
					""";
		}
		if (BD.typeDB.equals("mariadb")) {
			sql = """
						CREATE TABLE IF NOT EXISTS Empleados (
						  uuid CHAR(36) PRIMARY KEY,
						  nombre VARCHAR(255) NOT NULL,
						  telefono VARCHAR(255) DEFAULT NULL,
						  edad INT DEFAULT NULL,
						  PRIMARY KEY (uuid)
						)
						
						CREATE TABLE IF NOT EXISTS Departamentos (
								  Id Int PRIMARY KEY,
								  nombre VARCHAR(255) NOT NULL,
								  telefono VARCHAR(255) DEFAULT NULL,
								  edad INT DEFAULT NULL,
								  PRIMARY KEY (Id)
								)
					""";
						
		}
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
		}
	}
}
