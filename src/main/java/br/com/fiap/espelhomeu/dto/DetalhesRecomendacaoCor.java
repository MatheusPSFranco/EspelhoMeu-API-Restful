package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesRecomendacaoCor {

    private Long id;
    private Long consultoriaCapilarId;
    private Long corCabeloId;
    private String justificativa;
    private LocalDateTime data;

    //construtores

    public DetalhesRecomendacaoCor() {}

    public DetalhesRecomendacaoCor(Long id, Long consultoriaCapilarId, Long corCabeloId, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaCapilarId = consultoriaCapilarId;
        this.corCabeloId = corCabeloId;
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