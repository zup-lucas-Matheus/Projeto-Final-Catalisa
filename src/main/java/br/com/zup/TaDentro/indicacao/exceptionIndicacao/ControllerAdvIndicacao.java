package br.com.zup.TaDentro.indicacao.exceptionIndicacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvIndicacao {

    @ExceptionHandler (MensagemErroIndicacao.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroBadRequestIndicacao> handlerBadRequest(MensagemErroIndicacao exception) {
        return new ResponseEntity<>(
                ErroBadRequestIndicacao.builder()
                        .titulo("BAD REQUEST")
                        .mensagem(exception.getMessage())
                        .StatusCode(400)
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
