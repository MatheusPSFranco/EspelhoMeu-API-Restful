package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CadastroCorCabelo {

    @NotBlank
    @Size(max = 30)
    private String nomeTonalidade;

    @NotNull
    @NotEmpty
    private byte[] imagem;

    @NotBlank
    @Size(max = 150)
    private String infotmacaoManutencao;

    //construtores

    public CadastroCorCabelo() {}

    public CadastroCorCabelo(String nomeTonalidade, byte[] imagem, String infotmacaoManutencao) {
        this.nomeTonalidade = nomeTonalidade;
        this.imagem = imagem;
        this.infotmacaoManutencao = infotmacaoManutencao;
    }

    //getters and setters

    public String getNomeTonalidade() {
        return nomeTonalidade;
    }

    public void setNomeTonalidade(String nomeTonalidade) {
        this.nomeTonalidade = nomeTonalidade;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getInfotmacaoManutencao() {
        return infotmacaoManutencao;
    }

    public void setInfotmacaoManutencao(String infotmacaoManutencao) {
        this.infotmacaoManutencao = infotmacaoManutencao;
    }
}