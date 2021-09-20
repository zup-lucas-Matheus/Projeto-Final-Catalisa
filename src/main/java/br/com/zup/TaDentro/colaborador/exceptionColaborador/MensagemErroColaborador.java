package br.com.zup.TaDentro.colaborador.exceptionColaborador;

public class MensagemErroColaborador extends RuntimeException{

    public MensagemErroColaborador(String message) {
        super(message);
    }
}
