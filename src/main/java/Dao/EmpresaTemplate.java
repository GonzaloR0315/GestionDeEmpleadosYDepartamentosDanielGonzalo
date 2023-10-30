package Dao;

import Model.Empleado;
import Model.Departamento;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpresaTemplate {
	
	

    public static Boolean insertEmpleado(Connection connection, Empleado empleado) {
        try {
            Statement statement = (Statement) connection.createStatement();
            String query = "Insert INTO Empleados ( nombre, salario, nacido, departamento_id) VALUES (" + empleado.getNombre() + ", " + empleado.getSalario() + ", " + empleado.getNacido() + ", " + empleado.getDepartamento().getId() + ")";
            ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(query);
            resultSet.close();
            ((Connection) statement).close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	

    public static Boolean insertDepartamento(Connection connection, Departamento departamento) {
        try {
            Statement statement = (Statement) connection.createStatement();
            String query = "Insert INTO Departamentos ( nombre, jefe_id) VALUES (" + departamento.getNombre() + ", " + departamento.getJefe().getId() + ")";
            ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(query);
            resultSet.close();
            ((Connection) statement).close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static ArrayList<Object> selectAllFromTable(Connection connection, String tableName) {
        ArrayList<Object> lista = new ArrayList<Object>();
        try {
            Statement statement = (Statement) connection.createStatement();
            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(query);
            if( tableName.equalsIgnoreCase( "Empleados") ) {
	            while (resultSet.next()) {
	            	Empleado empleado = new Empleado(resultSet.getInt("id"),
	            			resultSet.getString("nombre"),
	            			resultSet.getDouble("salario"),
	            			 (LocalDate)resultSet.getDate("nacido").toLocalDate(),new Departamento());
                    empleado.getDepartamento().setNombre(resultSet.getString("departamento_id"));
	            	Object objeto = empleado;
	            	lista.add(objeto);
	            }
            }
            else if( tableName.equalsIgnoreCase( "Departamentos") ) {
            while (resultSet.next()) {
                Departamento departamento = new Departamento(resultSet.getInt("id"),resultSet.getString("nombre"),new Empleado());
                departamento.getJefe().setNombre(resultSet.getString("jefe_id"));
                Object objeto = departamento;
                lista.add(objeto);
            }
            }
            resultSet.close();
            ((Connection) statement).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista ;
    }
	
	public static void selectIdFromTable(Connection connection, String tableName, int ID) {
        try {
            Statement statement = (Statement) connection.createStatement();
            String query = "SELECT * FROM " + tableName + " where id = " + ID;
            ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(query);
            while (resultSet.next()) {
                // Aquí puedes recuperar los datos de cada fila y procesarlos según tus necesidades.
                // Ejemplo: String columna1 = resultSet.getString("nombre_columna1");
                //         int columna2 = resultSet.getInt("nombre_columna2");
                //         // Realiza las operaciones que desees con los datos.
            }
            resultSet.close();
            ((Connection) statement).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	public static Boolean eliminarFromTable(Connection connection, String tableNameDel, String tableNameNull, int ID) {
        try {
            
        	Statement statement = (Statement) connection.createStatement();
        	String query1 = "";
            if(tableNameNull.equalsIgnoreCase("empleados")) {
	        	query1 = "Update " + tableNameNull + " set departamento = 'NULL' where departamento_id = (SELECT id FROM " + tableNameDel + " where jefe_id = " + ID + ")"   ;
            }else {
            	query1 = "Update " + tableNameNull + " set jefe = 'NULL' where jefe_id = (SELECT id FROM " + tableNameDel + " where departamento_id = " + ID + ")"   ;
            }
            String query2 = "Delete * FROM " + tableNameDel + " where id = " + ID;
            ResultSet resultSet1 = ((java.sql.Statement) statement).executeQuery(query1);
            ResultSet resultSet2 = ((java.sql.Statement) statement).executeQuery(query2);
            resultSet1.close();
            resultSet2.close();
            ((Connection) statement).close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static Boolean updateEmpleados(Connection connection,Empleado empleado) {
		try {
			 Statement statement = (Statement) connection.createStatement();
	         String query = "Update Empleados set nombre = " + empleado.getNombre() + ", salario = " + empleado.getSalario() + ", nacido = " + empleado.getNacido() + ", departamento_id = " + empleado.getDepartamento().getNombre() + " where id = " + empleado.getId() + ")";
	         ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(query);
	         resultSet.close();
	         ((Connection) statement).close();
	         return true;
	    } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	    }
	}

	public static Boolean updateDepartamentos(Connection connection,Departamento departamento) {
		try {
			 Statement statement = (Statement) connection.createStatement();
	         String query = "Update Empleados set nombre = " + departamento.getNombre() + ", jefe_id = " + departamento.getJefe().getId() + " where id = " + departamento.getId() + ")";
	         ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(query);
	         resultSet.close();
	         ((Connection) statement).close();
	         return true;
	    } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	    }
	}





}
