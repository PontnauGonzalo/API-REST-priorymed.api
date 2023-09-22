package med.priory.api.domain.consulta.validaciones;

import med.priory.api.domain.consulta.DatosAgendarConsulta;

public interface ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos);
}