package med.priory.api.domain.paciente;

import med.priory.api.domain.direccion.Direccion;

public record DatosDetallesPaciente(Long id, String nombre, String email, String documento, String telefono, Direccion direccion) {

    public DatosDetallesPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(), paciente.getTelefono(), paciente.getDireccion());
    }
}