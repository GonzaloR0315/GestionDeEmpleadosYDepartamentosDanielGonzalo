package Model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
	private int id;
	private String nombre;
	private double salario;
	LocalDate nacido;
	Departamento departamento;
}
