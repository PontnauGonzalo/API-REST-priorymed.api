package med.priory.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.priory.api.domain.consulta.ConsultaRepository;
import med.priory.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos)  {

        var primerHorario = datos.fecha().withHour(7); // se puede asignar consulta desde
        var ultimoHorario= datos.fecha().withHour(18); // 7hs a 18hs

        var pacienteConConsulta=repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("el paciente ya tiene una consulta para ese dia");
        }

    }
}