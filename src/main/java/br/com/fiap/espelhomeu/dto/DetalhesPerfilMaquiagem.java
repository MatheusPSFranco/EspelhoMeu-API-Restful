package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesPerfilMaquiagem {

    private Long id;
    private Long usuarioId;
    private String tipopele;
    private String tomPele;
    private String subtomPele;
    private String necessidadePele;
    private String produtoUtilizado;
    private String tonalidadeUtilizada;
    private String dificuldadeBase;
    private String dificuldadeCorretivo;
    private String coberturaDesejada;
    private String acabamentoPreferido;
    private String faixaPreco;
    private LocalDateTime data;

    //construtores

    public DetalhesPerfilMaquiagem() {}

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipopele() {
        return tipopele;
    }

    public void setTipopele(String tipopele) {
        this.tipopele = tipopele;
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

    public String getNecessidadePele() {
        return necessidadePele;
    }

    public void setNecessidadePele(String necessidadePele) {
        this.necessidadePele = necessidadePele;
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