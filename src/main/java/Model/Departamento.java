package Model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Departamento {

	Integer id;
	@NonNull
	String nombre;
	@NonNull
	Empleado jefe;
}
