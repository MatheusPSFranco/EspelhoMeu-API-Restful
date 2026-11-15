package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesConsultoriaCapilar {

    private Long id;
    private Long perfilCabeloId;
    private LocalDateTime data;

    //construtores

    public DetalhesConsultoriaCapilar() {}

    public DetalhesConsultoriaCapilar(Long id, Long perfilCabeloId, LocalDateTime data) {
        this.id = id;
        this.perfilCabeloId = perfilCabeloId;
        this.data = data;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPerfilCabeloId() {
        return perfilCabeloId;
    }

    public void setPerfilCabeloId(Long perfilCabeloId) {
        this.perfilCabeloId = perfilCabeloId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}