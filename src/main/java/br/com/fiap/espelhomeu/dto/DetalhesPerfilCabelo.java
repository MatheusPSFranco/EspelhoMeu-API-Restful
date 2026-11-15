package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesPerfilCabelo {

    private Long id;
    private Long usuarioId;
    private String texturaCabelo;
    private String formatoRosto;
    private String comprimentoAtual;
    private String comprimentoDesejado;
    private String corAtual;
    private String estiloPreferencia;
    private String tempoDisponivel;
    private String interesseColoracao;
    private String preferenciaMudanca;
    private String faixaPreco;
    private LocalDateTime data;

    //Constructor

    public DetalhesPerfilCabelo() {}

    //Getters and Setters

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

    public String getTexturaCabelo() {
        return texturaCabelo;
    }

    public void setTexturaCabelo(String texturaCabelo) {
        this.texturaCabelo = texturaCabelo;
    }

    public String getFormatoRosto() {
        return formatoRosto;
    }

    public void setFormatoRosto(String formatoRosto) {
        this.formatoRosto = formatoRosto;
    }

    public String getComprimentoAtual() {
        return comprimentoAtual;
    }

    public void setComprimentoAtual(String comprimentoAtual) {
        this.comprimentoAtual = comprimentoAtual;
    }

    public String getComprimentoDesejado() {
        return comprimentoDesejado;
    }

    public void setComprimentoDesejado(String comprimentoDesejado) {
        this.comprimentoDesejado = comprimentoDesejado;
    }

    public String getCorAtual() {
        return corAtual;
    }

    public void setCorAtual(String corAtual) {
        this.corAtual = corAtual;
    }

    public String getEstiloPreferencia() {
        return estiloPreferencia;
    }

    public void setEstiloPreferencia(String estiloPreferencia) {
        this.estiloPreferencia = estiloPreferencia;
    }

    public String getTempoDisponivel() {
        return tempoDisponivel;
    }

    public void setTempoDisponivel(String tempoDisponivel) {
        this.tempoDisponivel = tempoDisponivel;
    }

    public String getInteresseColoracao() {
        return interesseColoracao;
    }

    public void setInteresseColoracao(String interesseColoracao) {
        this.interesseColoracao = interesseColoracao;
    }

    public String getPreferenciaMudanca() {
        return preferenciaMudanca;
    }

    public void setPreferenciaMudanca(String preferenciaMudanca) {
        this.preferenciaMudanca = preferenciaMudanca;
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