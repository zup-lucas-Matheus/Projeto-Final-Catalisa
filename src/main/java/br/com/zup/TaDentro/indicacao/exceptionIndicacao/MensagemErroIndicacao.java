package br.com.zup.TaDentro.indicacao.exceptionIndicacao;

public class MensagemErroIndicacao extends RuntimeException{

    public MensagemErroIndicacao(String message) {
        super(message);
    }
}
