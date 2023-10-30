package Model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Departamento {

	Integer id;
	String nombre;
	Empleado jefe;

	public Departamento( String nombre, Empleado jefe) {
		this.nombre = nombre;
		this.jefe = jefe;
	}
}
