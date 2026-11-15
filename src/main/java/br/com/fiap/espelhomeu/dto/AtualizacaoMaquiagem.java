package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AtualizacaoMaquiagem {

    @Size(max = 20)
    private String tipo;

    @Size(max = 30)
    private String cor;

    @Pattern(regexp = "^(Custo-beneficio|Intermediário|Caro)$")
    private String faixaPreco;

    @Size(max = 40)
    private String marca;

    @Size(max = 50)
    private String linhaProduto;

    @Size(max = 20)
    private String codigoTonalidade;

    @Size(max = 50)
    private String tipoCobertura;

    @Size(max = 50)
    private String acabamento;

    @Digits(integer = 1, fraction = 2)
    private Double avaliacao;

    //construtores

    public AtualizacaoMaquiagem() {}

    public AtualizacaoMaquiagem(String tipo, String cor, String faixaPreco, String marca, String linhaProduto, String codigoTonalidade, String tipoCobertura, String acabamento, Double avaliacao) {
        this.tipo = tipo;
        this.cor = cor;
        this.faixaPreco = faixaPreco;
        this.marca = marca;
        this.linhaProduto = linhaProduto;
        this.codigoTonalidade = codigoTonalidade;
        this.tipoCobertura = tipoCobertura;
        this.acabamento = acabamento;
        this.avaliacao = avaliacao;
    }

    //getters and setters

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFaixaPreco() {
        return faixaPreco;
    }

    public void setFaixaPreco(String faixaPreco) {
        this.faixaPreco = faixaPreco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinhaProduto() {
        return linhaProduto;
    }

    public void setLinhaProduto(String linhaProduto) {
        this.linhaProduto = linhaProduto;
    }

    public String getCodigoTonalidade() {
        return codigoTonalidade;
    }

    public void setCodigoTonalidade(String codigoTonalidade) {
        this.codigoTonalidade = codigoTonalidade;
    }

    public String getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(String tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}