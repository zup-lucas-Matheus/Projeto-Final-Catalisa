package br.com.zup.TaDentro.colaborador.exceptionColaborador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvColaborador {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MensagemErroColaborador.class)
    public ResponseEntity<ErroBadRequestColaborador> capturaException(MensagemErroColaborador exceptionColab){
        return new ResponseEntity<>(
                ErroBadRequestColaborador.builder()
                .mensagem(exceptionColab.getMessage())
                .StatusCode(404)
                .titulo("BAD REQUEST")
                .build(), HttpStatus.BAD_REQUEST
        );
    }


}
