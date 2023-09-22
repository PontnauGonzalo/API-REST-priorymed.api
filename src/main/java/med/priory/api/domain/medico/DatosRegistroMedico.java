package med.priory.api.domain.medico;

import jakarta.validation.constraints.*;
import med.priory.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;

public record DatosRegistroMedico(

        @NotBlank // validacion (nombre sea NO vacío NI nulo)
        String nombre,
        @NotBlank
        @Email // validacion (formato email)
        String email,
        @NotBlank
        @Size(min = 0, max = 15) // cant caracteres permitidos
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // patron de 4 a 6 dígitos
        String documento,
        @NotNull // validacion (especialidad sea NO nulo)
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {
}