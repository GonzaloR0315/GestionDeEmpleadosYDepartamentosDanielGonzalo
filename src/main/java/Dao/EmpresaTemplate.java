package Dao;

import Model.Empleado;
import Model.Departamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpresaTemplate {



    public static boolean insertEmpleado(Connection connection, Empleado empleado) {
        try {
            String query = "INSERT INTO Empleados (nombre, salario, nacido, departamento_id) VALUES (?, ?, ?, ?)";

            // Usamos PreparedStatement en lugar de Statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setDouble(2, empleado.getSalario());
            preparedStatement.setDate(3, Date.valueOf(empleado.getNacido()));
            preparedStatement.setInt(4, empleado.getDepartamento().getId());

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean insertDepartamento(Connection connection, Departamento departamento) {
        try {
            String query = "INSERT INTO Departamentos (nombre, jefe_id) VALUES (?, ?)";

            // Usamos PreparedStatement en lugar de Statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departamento.getNombre());
            preparedStatement.setInt(2, departamento.getJefe().getId());

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;
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
         //   ((Connection) statement).close();
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
            	query1 = "Update " + tableNameNull + " set jefe_id = 'NULL' where jefe_id = (SELECT id FROM " + tableNameDel + " where departamento_id = " + ID + ")"   ;
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

    public static Boolean updateEmpleado(Connection connection, Empleado empleado) {
        try {
            String query = "UPDATE Empleados SET nombre = ?, salario = ?, nacido = ?, departamento_id = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empleado.getNombre());

            if (empleado.getSalario() != null) {
                preparedStatement.setDouble(2, empleado.getSalario());
            } else {
                preparedStatement.setNull(2, java.sql.Types.DOUBLE);
            }

            if (empleado.getNacido() != null) {
                preparedStatement.setDate(3, Date.valueOf(empleado.getNacido()));
            } else {
                preparedStatement.setNull(3, java.sql.Types.DATE);
            }

            if (empleado.getDepartamento() != null && empleado.getDepartamento().getId() != null) {
                preparedStatement.setInt(4, empleado.getDepartamento().getId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }

            if (empleado.getId() != null) {
                preparedStatement.setInt(5, empleado.getId());
            } else {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static Boolean updateDepartamentos(Connection connection,Departamento departamento) {
        try {
            String query = "UPDATE Departamentos SET nombre = ? WHERE Id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departamento.getNombre());
            preparedStatement.setInt(2, departamento.getId());

            int rowsAffected = preparedStatement.executeUpdate(); // Utiliza executeUpdate
            preparedStatement.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}





}
