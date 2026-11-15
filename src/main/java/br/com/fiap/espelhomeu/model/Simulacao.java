package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class Simulacao {

    private Long id;
    private Usuario usuario;
    private Corte corte;
    private CorCabelo corCabelo;
    private Penteado penteado;
    private byte[] imagem;
    private LocalDateTime data;

    //Constructor

    public Simulacao() {}

    public Simulacao(Long id, Usuario usuario, Corte corte, CorCabelo corCabelo, Penteado penteado, byte[] imagem, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.corte = corte;
        this.corCabelo = corCabelo;
        this.penteado = penteado;
        this.imagem = imagem;
        this.data = data;
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

    public Penteado getPenteado() {
        return penteado;
    }

    public void setPenteado(Penteado penteado) {
        this.penteado = penteado;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Usuario: "+usuario.getId()+", Corte: "+corte.getId()+", Cor: "+corCabelo.getId()+", Penteado: "+penteado.getId()+", Data: "+data;
    }
}
