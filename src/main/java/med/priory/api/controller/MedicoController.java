package med.priory.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.priory.api.domain.direccion.DatosDireccion;
import med.priory.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController // HTTP
@RequestMapping ("/medicos")
@SecurityRequirement (name = "bearer-key")
public class MedicoController {
    @Autowired // métodos para acceder y manipular datos relacionados con médicos en la base de datos
    private MedicoRepository medicoRepository;

    @PostMapping // request 1 (envía datos)
    @Transactional
    @Operation(summary = "Registra un nuevo medico en la base de datos")
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                                                                            medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                                    medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                                    medico.getDireccion().getComplemento()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); // URL dinamico
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }

    @GetMapping // request 2 (obtiene datos)
    @Operation (summary = "Obtiene el listado de medicos")
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping // request 3 (actualiza datos)
    @Transactional
    @Operation (summary = "Actualiza los datos de un medico existente")
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                                                           medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                                    medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                                    medico.getDireccion().getComplemento())));
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}") // request 3 (delete de datos mediante id es decir una pathvariable)
    @Transactional // commit de la BD
    @Operation (summary = "Elimina un medico registrado - inactivo")
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    @Operation (summary = "Obtiene los registros del medico con ID")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        // Busca en el repositorio de médicos un médico con el ID proporcionado.
        Medico medico = medicoRepository.getReferenceById(id);
        // Crea un objeto de tipo DatosRespuestaMedico con los datos del médico encontrado.
        var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                                                    medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                                    medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                                    medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);
    }
}