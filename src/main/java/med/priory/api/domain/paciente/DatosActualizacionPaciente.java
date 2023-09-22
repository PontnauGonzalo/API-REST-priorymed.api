package med.priory.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.priory.api.domain.direccion.DatosDireccion;

public record DatosActualizacionPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion) {
}