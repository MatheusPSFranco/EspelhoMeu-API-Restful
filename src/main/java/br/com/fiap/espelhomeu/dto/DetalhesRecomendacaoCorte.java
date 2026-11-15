package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesRecomendacaoCorte {

    private Long id;
    private Long consultoriaCapilarId;
    private Long corteId;
    private String justificativa;
    private LocalDateTime data;

    //construtores

    public DetalhesRecomendacaoCorte() {}

    public DetalhesRecomendacaoCorte(Long id, Long consultoriaCapilarId, Long corteId, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaCapilarId = consultoriaCapilarId;
        this.corteId = corteId;
        this.justificativa = justificativa;
        this.data = data;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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