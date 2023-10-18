package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dsn = "jdbc:sqlite:datos.sqlite";
		String sql;
		
		try {
			Connection conn = DriverManager.getConnection(dsn);
			
			sql = "CREATE TABLE IF NOT EXISTS tabla(a INTEGER, b STRING)";
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			
//			sql = "INSERT INTO tabla (a, b) VALUES (13, \"trece\")";
//			int afectadas = stmt.executeUpdate(sql);
//			System.out.println("Filas afectadas : " + afectadas);
			
			int registro = 11;
			sql = "SELECT a, b FROM tabla WHERE a > ?"
					+ " AND b LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, registro);
			ps.setString(2,  "%d%");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("a") + ":" + rs.getString("b"));
			}
			
			ps.setInt(1, registro + 1);
			ps.setString(2,  "%a%");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("a") + ":" + rs.getString("b"));
			}

			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
