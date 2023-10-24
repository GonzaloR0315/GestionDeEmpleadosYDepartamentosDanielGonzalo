package view;

import Dao.BD;
import Dao.Empresa;
import IO.Teclado;
import Model.Departamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {
		BD base= new BD();
		Empresa e = new Empresa();
		System.out.println("1.Crear,2.Modificar,3.Eliminar,0.Salir");
		int option = Teclado.leerEntero("Introduce una de las opciones");
		switch (option){
			case 1:
				System.out.println("¿Quieres crear (D)epartamento u (E)mpleado?");
				String  create = null;
				if(create.equals("D"))  {
				Departamento departamento =	e.selectAllFromTable(BD.getConnection(),"Departamentos");
				}else{
					Empresa
				}
			break;
			case 2:
				


		}
	}
}
