package Dao;

import Model.Departamento;
import Model.Empleado;

import java.sql.Connection;
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
}
