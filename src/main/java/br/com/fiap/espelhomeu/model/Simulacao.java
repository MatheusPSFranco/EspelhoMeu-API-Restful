package br.com.fiap.espelhomeu.model;

public class Simulacao {

    private Long id;
    private Usuario usuario;
    private Corte corte;
    private CorCabelo corCabelo;
    private byte[] imagem;

    //Constructor

    public Simulacao() {}

    public Simulacao(Long id, Usuario usuario, Corte corte, CorCabelo corCabelo, byte[] imagem) {
        this.id = id;
        this.usuario = usuario;
        this.corte = corte;
        this.corCabelo = corCabelo;
        this.imagem = imagem;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Corte getCorte() {
        return corte;
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
    }

    public CorCabelo getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(CorCabelo corCabelo) {
        this.corCabelo = corCabelo;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
