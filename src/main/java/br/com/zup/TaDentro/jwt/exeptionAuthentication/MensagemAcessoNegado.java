package br.com.zup.TaDentro.jwt.exeptionAuthentication;

public class MensagemAcessoNegado extends RuntimeException{

    public MensagemAcessoNegado(String message) {
        super(message);
    }
}
