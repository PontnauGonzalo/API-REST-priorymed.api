package med.priory.api.domain.medico;

import med.priory.api.domain.direccion.DatosDireccion;

// DTO (data transfer obj) en este caso Datos respuesta medicos
// Como proposito general, encapsulo un conjunto de datos relacionados en una estructura
public record DatosRespuestaMedico(Long id, String nombre, String email,
                                   String telefono, String documento, DatosDireccion direccion) {

}