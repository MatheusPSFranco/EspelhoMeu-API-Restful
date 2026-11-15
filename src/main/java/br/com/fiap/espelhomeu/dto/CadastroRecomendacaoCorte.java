package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CadastroRecomendacaoCorte {

    @NotNull
    @Positive
    private Long consultoriaCapilarId;

    @NotNull
    @Positive
    private Long corteId;

    @Size(max = 150)
    private String justificativa;

    private LocalDateTime data;

    //construtores

    public CadastroRecomendacaoCorte() {}

    public CadastroRecomendacaoCorte(Long consultoriaCapilarId, Long corteId, String justificativa, LocalDateTime data) {
        this.consultoriaCapilarId = consultoriaCapilarId;
        this.corteId = corteId;
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

    public Long getCorteId() {
        return corteId;
    }

    public void setCorteId(Long corteId) {
        this.corteId = corteId;
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