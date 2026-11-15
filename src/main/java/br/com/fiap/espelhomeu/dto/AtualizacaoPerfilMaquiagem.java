package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AtualizacaoPerfilMaquiagem {

    @NotBlank(message = "O tipo de pele é obrigatório")
    @Size(max = 30, message = "O tipo de pele deve ter no máximo 30 caracteres")
    private String tipopele;

    @NotBlank(message = "O tom de pele é obrigatório")
    @Size(max = 20, message = "O tom de pele deve ter no máximo 20 caracteres")
    private String tomPele;

    @NotBlank(message = "O subtom de pele é obrigatório")
    @Size(max = 20, message = "O subtom de pele deve ter no máximo 20 caracteres")
    private String subtomPele;

    @NotBlank(message = "A necessidade da pele é obrigatória")
    @Size(max = 40, message = "A necessidade da pele deve ter no máximo 40 caracteres")
    private String necessidadePele;

    @NotBlank(message = "O produto utilizado é obrigatório")
    @Size(max = 100, message = "O produto utilizado deve ter no máximo 100 caracteres")
    private String produtoUtilizado;

    @NotBlank(message = "A tonalidade utilizada é obrigatória")
    @Size(max = 100, message = "A tonalidade utilizada deve ter no máximo 100 caracteres")
    private String tonalidadeUtilizada;

    @NotBlank(message = "A dificuldade com a base é obrigatória")
    @Size(max = 50, message = "A dificuldade com a base deve ter no máximo 50 caracteres")
    private String dificuldadeBase;

    @NotBlank(message = "A dificuldade com o corretivo é obrigatória")
    @Size(max = 50, message = "A dificuldade com o corretivo deve ter no máximo 50 caracteres")
    private String dificuldadeCorretivo;

    @NotBlank(message = "A cobertura desejada é obrigatória")
    @Size(max = 40, message = "A cobertura desejada deve ter no máximo 40 caracteres")
    private String coberturaDesejada;

    @NotBlank(message = "O acabamento preferido é obrigatório")
    @Size(max = 40, message = "O acabamento preferido deve ter no máximo 40 caracteres")
    private String acabamentoPreferido;

    @NotBlank(message = "A faixa de preço é obrigatória")
    @Pattern(
            regexp = "^(Custo-benefício|Intermediário|Caro)$",
            message = "A faixa de preço deve ser Custo-benefício, Intermediário ou Caro"
    )
    private String faixaPreco;

    //construtores

    public AtualizacaoPerfilMaquiagem() {}

    //getters and setters

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
}