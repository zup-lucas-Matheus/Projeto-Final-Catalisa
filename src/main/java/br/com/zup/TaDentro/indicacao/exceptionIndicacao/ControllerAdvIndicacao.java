package br.com.zup.TaDentro.indicacao.exceptionIndicacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvIndicacao {

    public ResponseEntity<ErroBadRequestIndicacao> handlerBadRequest(MensagemErroIndicacao exception) {
        return new ResponseEntity<>(
                ErroBadRequestIndicacao.builder()
                        .titulo("BAD REQUEST")
                        .mensagem(exception.getMessage())
                        .StatusCode(400)
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
