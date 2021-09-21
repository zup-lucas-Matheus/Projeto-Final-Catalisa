package br.com.zup.TaDentro.Usuario.exceptionUsuario;

import lombok.Builder;
import lombok.Data;


public class MensagemErroUsuario extends RuntimeException{

    public MensagemErroUsuario(String message) {
        super(message);
    }
}
