package br.com.fiap.espelhomeu.exception;

public class ErrorResponseDto {
    private String erro;
    private String detalhe;

    public ErrorResponseDto(String erro, String detalhe) {
        this.erro = erro;
        this.detalhe = detalhe;
    }

    //Getters and Setters

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
}
