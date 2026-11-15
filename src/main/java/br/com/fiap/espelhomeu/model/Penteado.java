package br.com.fiap.espelhomeu.model;

public class Penteado {

    private Long id;
    private String nome;
    private String descricao;
    private byte[] imagem;
    private String informacaoManutencao;

    //Constructors

    public Penteado() {}

    public Penteado(Long id, String nome, String descricao, byte[] imagem, String informacaoManutencao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.informacaoManutencao = informacaoManutencao;
    }

    //Getters and Setters

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

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Nome: "+nome+", Descricao: "+descricao+", Informacao Manutencao: "+informacaoManutencao;
    }
}
