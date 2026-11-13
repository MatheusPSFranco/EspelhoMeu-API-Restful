package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class Favoritos {

    private Long id;
    private Usuario usuario;
    private Corte corte;
    private Maquiagem maquiagem;
    private CorCabelo corCabelo;
    private RecomendacaoCorte recomendacaoCorte;
    private RecomendacaoCor recomendacaoCor;
    private LocalDateTime data;

    //Constructor

    public Favoritos() {}

    public Favoritos(Long id, Usuario usuario, Corte corte, Maquiagem maquiagem, CorCabelo corCabelo, RecomendacaoCorte recomendacaoCorte, RecomendacaoCor recomendacaoCor, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.corte = corte;
        this.maquiagem = maquiagem;
        this.corCabelo = corCabelo;
        this.recomendacaoCorte = recomendacaoCorte;
        this.recomendacaoCor = recomendacaoCor;
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

    public Maquiagem getMaquiagem() {
        return maquiagem;
    }

    public void setMaquiagem(Maquiagem maquiagem) {
        this.maquiagem = maquiagem;
    }

    public CorCabelo getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(CorCabelo corCabelo) {
        this.corCabelo = corCabelo;
    }

    public RecomendacaoCorte getRecomendacaoCorte() {
        return recomendacaoCorte;
    }

    public void setRecomendacaoCorte(RecomendacaoCorte recomendacaoCorte) {
        this.recomendacaoCorte = recomendacaoCorte;
    }

    public RecomendacaoCor getRecomendacaoCor() {
        return recomendacaoCor;
    }

    public void setRecomendacaoCor(RecomendacaoCor recomendacaoCor) {
        this.recomendacaoCor = recomendacaoCor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
