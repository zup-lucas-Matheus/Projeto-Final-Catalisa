package br.com.zup.TaDentro.expectionsValidation;

import java.util.ArrayList;
import java.util.List;

public class MensagemDeErroValidation {

    private int statusCode;
    private List<Erro> erros = new ArrayList<>();


    public MensagemDeErroValidation(int statusCode, List<Erro> erros) {
        this.statusCode = statusCode;
        this.erros = erros;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Erro> getErros() {
        return erros;
    }

    public void setErros(List<Erro> erros) {
        this.erros = erros;
    }
}
