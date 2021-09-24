package br.com.zup.TaDentro.indicacao.exceptionIndicacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class MensagemErroFiltroIndicacao extends RuntimeException{

    public MensagemErroFiltroIndicacao(String message) {
        super(message);
    }
}
