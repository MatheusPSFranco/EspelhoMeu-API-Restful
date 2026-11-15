package br.com.fiap.espelhomeu.dto;

public class DetalhesPenteado {

    private Long id;
    private String nome;
    private String descricao;
    private byte[] imagem;
    private String informacaoManutencao;

    //construtores

    public DetalhesPenteado() {}

    public DetalhesPenteado(Long id, String nome, String descricao, byte[] imagem, String informacaoManutencao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.informacaoManutencao = informacaoManutencao;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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