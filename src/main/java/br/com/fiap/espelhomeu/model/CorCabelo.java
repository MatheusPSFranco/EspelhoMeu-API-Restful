package br.com.fiap.espelhomeu.model;

public class CorCabelo {

    private Long id;
    private String nomeTonalidade;
    private byte[] imagem;
    private String infotmacaoManutencao;

    //Constructor

    public CorCabelo() {}

    public CorCabelo(Long id, String nomeTonalidade, byte[] imagem, String infotmacaoManutencao) {
        this.id = id;
        this.nomeTonalidade = nomeTonalidade;
        this.imagem = imagem;
        this.infotmacaoManutencao = infotmacaoManutencao;
    }

    //Getters and Setters

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

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Nome da Tonalidade: "+nomeTonalidade+", Informacao Manutencao: "+infotmacaoManutencao;
    }
}
