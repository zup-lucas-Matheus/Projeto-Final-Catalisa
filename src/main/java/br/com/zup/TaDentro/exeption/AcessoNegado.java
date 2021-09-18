package br.com.zup.TaDentro.exeption;

public class AcessoNegado extends RuntimeException{

    private int statusCode = 403;

    public AcessoNegado(){
        super("Acesso negado");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
