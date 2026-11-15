package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class AtualizacaoRecomendacaoCor {

    @NotNull
    @Positive
    private Long consultoriaCapilarId;

    @NotNull
    @Positive
    private Long corCabeloId;

    @Size(max = 150)
    private String justificativa;

    private LocalDateTime data;

    //construtores

    public AtualizacaoRecomendacaoCor() {}

    public AtualizacaoRecomendacaoCor(Long consultoriaCapilarId, Long corCabeloId, String justificativa, LocalDateTime data) {
        this.consultoriaCapilarId = consultoriaCapilarId;
        this.corCabeloId = corCabeloId;
        this.justificativa = justificativa;
        this.data = data;
    }

    //getters and setters

    public Long getConsultoriaCapilarId() {
        return consultoriaCapilarId;
    }

    public void setConsultoriaCapilarId(Long consultoriaCapilarId) {
        this.consultoriaCapilarId = consultoriaCapilarId;
    }

    public Long getCorCabeloId() {
        return corCabeloId;
    }

    public void setCorCabeloId(Long corCabeloId) {
        this.corCabeloId = corCabeloId;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}