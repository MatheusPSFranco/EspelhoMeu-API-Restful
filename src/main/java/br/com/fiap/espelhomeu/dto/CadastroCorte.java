package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CadastroCorte {

    @NotBlank(message = "O nome do corte é obrigatório")
    @Size(max = 40, message = "O nome deve ter no máximo 40 caracteres")
    private String nome;

    @NotBlank(message = "A descrição do corte é obrigatória")
    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    private String descricao;

    @NotNull(message = "A imagem do corte é obrigatória")
    @NotEmpty(message = "A imagem não pode estar vazia")
    private byte[] imagem;

    @NotBlank(message = "A informação de manutenção é obrigatória")
    @Size(max = 150, message = "A informação de manutenção deve ter no máximo 150 caracteres")
    private String informacaoManutencao;

    //construtores

    public CadastroCorte() {}

    public CadastroCorte(String nome, String descricao, byte[] imagem, String informacaoManutencao) {
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