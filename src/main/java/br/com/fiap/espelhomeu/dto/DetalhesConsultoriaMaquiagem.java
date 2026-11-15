package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesConsultoriaMaquiagem {

    private Long id;
    private Long perfilMaquiagemId;
    private LocalDateTime data;

    //construtores

    public DetalhesConsultoriaMaquiagem() {}

    public DetalhesConsultoriaMaquiagem(Long id, Long perfilMaquiagemId, LocalDateTime data) {
        this.id = id;
        this.perfilMaquiagemId = perfilMaquiagemId;
        this.data = data;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPerfilMaquiagemId() {
        return perfilMaquiagemId;
    }

    public void setPerfilMaquiagemId(Long perfilMaquiagemId) {
        this.perfilMaquiagemId = perfilMaquiagemId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}