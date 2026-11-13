package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class PerfilMaquiagem {

    private Long id;
    private Usuario usuario;
    private String tomPele;
    private String subtomPele;
    private String produtoUtilizado;
    private String tonalidadeUtilizada;
    private String dificuldadeBase;
    private String dificuldadeCorretivo;
    private String coberturaDesejada;
    private String acabamentoPreferido;
    private String faixaPreco;
    private LocalDateTime data;

    //Constructor

    public PerfilMaquiagem() {}

    public PerfilMaquiagem(Long id, Usuario usuario, String tomPele, String subtomPele, String produtoUtilizado, String tonalidadeUtilizada, String dificuldadeBase, String dificuldadeCorretivo, String coberturaDesejada, String acabamentoPreferido, String faixaPreco, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.tomPele = tomPele;
        this.subtomPele = subtomPele;
        this.produtoUtilizado = produtoUtilizado;
        this.tonalidadeUtilizada = tonalidadeUtilizada;
        this.dificuldadeBase = dificuldadeBase;
        this.dificuldadeCorretivo = dificuldadeCorretivo;
        this.coberturaDesejada = coberturaDesejada;
        this.acabamentoPreferido = acabamentoPreferido;
        this.faixaPreco = faixaPreco;
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

    public String getTomPele() {
        return tomPele;
    }

    public void setTomPele(String tomPele) {
        this.tomPele = tomPele;
    }

    public String getSubtomPele() {
        return subtomPele;
    }

    public void setSubtomPele(String subtomPele) {
        this.subtomPele = subtomPele;
    }

    public String getProdutoUtilizado() {
        return produtoUtilizado;
    }

    public void setProdutoUtilizado(String produtoUtilizado) {
        this.produtoUtilizado = produtoUtilizado;
    }

    public String getTonalidadeUtilizada() {
        return tonalidadeUtilizada;
    }

    public void setTonalidadeUtilizada(String tonalidadeUtilizada) {
        this.tonalidadeUtilizada = tonalidadeUtilizada;
    }

    public String getDificuldadeBase() {
        return dificuldadeBase;
    }

    public void setDificuldadeBase(String dificuldadeBase) {
        this.dificuldadeBase = dificuldadeBase;
    }

    public String getDificuldadeCorretivo() {
        return dificuldadeCorretivo;
    }

    public void setDificuldadeCorretivo(String dificuldadeCorretivo) {
        this.dificuldadeCorretivo = dificuldadeCorretivo;
    }

    public String getCoberturaDesejada() {
        return coberturaDesejada;
    }

    public void setCoberturaDesejada(String coberturaDesejada) {
        this.coberturaDesejada = coberturaDesejada;
    }

    public String getAcabamentoPreferido() {
        return acabamentoPreferido;
    }

    public void setAcabamentoPreferido(String acabamentoPreferido) {
        this.acabamentoPreferido = acabamentoPreferido;
    }

    public String getFaixaPreco() {
        return faixaPreco;
    }

    public void setFaixaPreco(String faixaPreco) {
        this.faixaPreco = faixaPreco;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
