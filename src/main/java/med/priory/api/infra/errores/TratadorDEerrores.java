package med.priory.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDEerrores {

    @ExceptionHandler (EntityNotFoundException.class) // manejando excepciones del tipo EntityNotFoundException.
    public ResponseEntity tratarError404() {  // Construimos una respuesta HTTP con el código de estado 404 (Not Found)
        return ResponseEntity.notFound().build(); // Para indicar que la entidad solicitada no se ha encontrado.
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        // errores de validación luego, los mapeamos a objetos de tipo DatosErrorValidacion.
        return ResponseEntity.badRequest().body(errores);
    }// el body es para avisar al cliente donde es el error

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosErrorValidacion(String campo, String error) { // DTO
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage()); // mapeado
        }
    }
}

