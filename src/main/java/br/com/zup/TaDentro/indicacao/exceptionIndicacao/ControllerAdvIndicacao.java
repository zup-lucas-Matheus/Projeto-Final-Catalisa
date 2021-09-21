package br.com.zup.TaDentro.indicacao.exceptionIndicacao;

import br.com.zup.TaDentro.Usuario.exceptionUsuario.ErroBadRequestUsuario;
import br.com.zup.TaDentro.Usuario.exceptionUsuario.MensagemErroUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvIndicacao {

    public ResponseEntity<ErroBadRequestUsuario> handlerBadRequest(MensagemErroUsuario ex){
        return new ResponseEntity<>(
                ErroBadRequestUsuario.builder()
                        .titulo("BAD REQUEST")
                        .mensagem(ex.getMessage())
                        .StatusCode(400)
                        .build(), HttpStatus.BAD_REQUEST);
}
