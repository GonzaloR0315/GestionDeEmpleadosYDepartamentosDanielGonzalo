package Model;

import java.time.LocalDate;

import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Empleado {
	private int id;
	@NonNull
	private String nombre;
	private double salario;
	LocalDate nacido;
	@NonNull
	Departamento departamento;
}
