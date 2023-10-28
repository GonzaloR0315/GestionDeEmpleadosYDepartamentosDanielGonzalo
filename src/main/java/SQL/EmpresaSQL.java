package SQL;

import java.sql.Connection;
import java.sql.SQLException;

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
							id INTEGER PRIMARY KEY,
							nombre STRING NOT NULL,
							salario DOUBLE,
							nacido DATE,
							departamento_id INTEGER
						)
						
						CREATE TABLE IF NOT EXISTS Departamentos (
								Id INTEGER PRIMARY KEY,
								nombre STRING NOT NULL,
								jefe_id not null
							)
					""";
		}
		if (BD.typeDB.equals("mariadb")) {
			sql = """
						CREATE TABLE IF NOT EXISTS Empleados (
						  uuid INT PRIMARY KEY,
						  nombre VARCHAR(255) NOT NULL,
						  salario DOUBLE DEFAULT NULL,
						  nacido DATE,
						  departamento_id INT,
						  PRIMARY KEY (uuid)
						)
						
						CREATE TABLE IF NOT EXISTS Departamentos (
								  Id Int PRIMARY KEY,
								  nombre VARCHAR(255) NOT NULL,
								  jefe_id INT NOT NULL,
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
