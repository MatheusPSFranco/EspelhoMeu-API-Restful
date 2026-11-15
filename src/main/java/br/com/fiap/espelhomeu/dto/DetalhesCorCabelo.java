package br.com.fiap.espelhomeu.dto;

public class DetalhesCorCabelo {

    private Long id;
    private String nomeTonalidade;
    private byte[] imagem;
    private String infotmacaoManutencao;

    //construtores

    public DetalhesCorCabelo() {}

    public DetalhesCorCabelo(Long id, String nomeTonalidade, byte[] imagem, String infotmacaoManutencao) {
        this.id = id;
        this.nomeTonalidade = nomeTonalidade;
        this.imagem = imagem;
        this.infotmacaoManutencao = infotmacaoManutencao;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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