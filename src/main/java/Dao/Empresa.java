package Dao;

import Model.Departamento;
import Model.Empleado;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Empresa extends EmpresaTemplate {
    public static Boolean insertEmpleado(Connection connection, String nombre, Double salario, LocalDate nacido, Departamento departamento) {
            Empleado e = new Empleado(nombre,departamento);
            e.setNacido(nacido);
            e.setSalario(salario);
           return insertEmpleado(connection,e);

    }
    public static Boolean insertDepartamento(Connection connection, String nombre, Empleado jefe) {
       return insertDepartamento(connection,new Departamento(nombre,jefe));
    }
    public static Boolean updateEmpleados(Connection connection,Integer id, String nombre, Double salario, LocalDate nacido, Departamento departamento) {
        return updateEmpleados(connection,new Empleado(id,nombre,salario,nacido,departamento));
    }

    public static Boolean updateDepartamentos(Connection connection,Integer id, String nombre, Empleado jefe) {
        return updateDepartamentos(connection,new Departamento(id,nombre,jefe));
    }
}
