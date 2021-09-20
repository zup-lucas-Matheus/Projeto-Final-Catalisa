package br.com.zup.TaDentro.expections;

import java.util.ArrayList;
import java.util.List;

public class MensagemDeErroValidation {

    private int statusCode;
    private List<Erro> erros = new ArrayList<>();


    public MensagemDeErroValidation(int statusCode, List<Erro> erros) {
        this.statusCode = statusCode;
        this.erros = erros;
    }


}
