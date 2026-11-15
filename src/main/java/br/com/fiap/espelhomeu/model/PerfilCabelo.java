package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class PerfilCabelo {

    private Long id;
    private Usuario usuario;
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
    public PerfilCabelo() {}

    public PerfilCabelo(Long id, Usuario usuario, String texturaCabelo, String formatoRosto, String comprimentoAtual, String comprimentoDesejado, String corAtual, String estiloPreferencia, String tempoDisponivel, String interesseColoracao, String preferenciaMudanca, String faixaPreco, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.texturaCabelo = texturaCabelo;
        this.formatoRosto = formatoRosto;
        this.comprimentoAtual = comprimentoAtual;
        this.comprimentoDesejado = comprimentoDesejado;
        this.corAtual = corAtual;
        this.estiloPreferencia = estiloPreferencia;
        this.tempoDisponivel = tempoDisponivel;
        this.interesseColoracao = interesseColoracao;
        this.preferenciaMudanca = preferenciaMudanca;
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

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Usuario: "+usuario.getId()+", Textura Cabelo: "+texturaCabelo+", Formato do Rosto: "+formatoRosto+", Comprimento Atual: "+ comprimentoAtual+
                ", Comprimento Desejado: "+comprimentoDesejado+", Cor Atual: "+corAtual+", Estilo Preferencia: "+estiloPreferencia+", Tempo Disponivel: "+tempoDisponivel+
                ", Interesse Coloracao: "+interesseColoracao+", Preferencia Mudanca: "+preferenciaMudanca+", Faixa de Preco: "+faixaPreco+", Data: "+data;
    }
}
