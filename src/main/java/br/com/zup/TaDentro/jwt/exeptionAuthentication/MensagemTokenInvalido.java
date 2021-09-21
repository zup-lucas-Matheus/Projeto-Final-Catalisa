package br.com.zup.TaDentro.jwt.exeptionAuthentication;

public class MensagemTokenInvalido extends RuntimeException{

    public MensagemTokenInvalido(String message) {
        super(message);
    }
}
