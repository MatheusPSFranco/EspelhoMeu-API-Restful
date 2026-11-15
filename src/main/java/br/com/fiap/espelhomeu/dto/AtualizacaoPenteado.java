package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtualizacaoPenteado {

    @Size(max = 30)
    private String nome;

    @Size(max = 100)
    private String descricao;

    @NotNull
    @NotEmpty
    private byte[] imagem;

    @Size(max = 150)
    private String informacaoManutencao;

    //construtores

    public AtualizacaoPenteado() {}

    public AtualizacaoPenteado(String nome, String descricao, byte[] imagem, String informacaoManutencao) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.informacaoManutencao = informacaoManutencao;
    }

    //getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getInformacaoManutencao() {
        return informacaoManutencao;
    }

    public void setInformacaoManutencao(String informacaoManutencao) {
        this.informacaoManutencao = informacaoManutencao;
    }
}