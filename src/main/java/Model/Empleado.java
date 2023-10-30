package Model;

import java.time.LocalDate;

import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Empleado {
	private Integer id;
	private String nombre;
	private Double salario;
	LocalDate nacido;
	Departamento departamento;

	public Empleado( String nombre, Departamento departamento) {
		this.nombre = nombre;
		this.departamento = departamento;
	}

}
