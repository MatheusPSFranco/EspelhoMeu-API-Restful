package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AtualizacaoConsultoriaMaquiagem {

    @NotNull(message = "O ID do perfil de maquiagem é obrigatório")
    @Positive(message = "O ID do perfil de maquiagem deve ser positivo")
    private Long perfilMaquiagemId;

    //construtores

    public AtualizacaoConsultoriaMaquiagem() {}

    public AtualizacaoConsultoriaMaquiagem(Long perfilMaquiagemId) {
        this.perfilMaquiagemId = perfilMaquiagemId;
    }

    //getters and setters

    public Long getPerfilMaquiagemId() {
        return perfilMaquiagemId;
    }

    public void setPerfilMaquiagemId(Long perfilMaquiagemId) {
        this.perfilMaquiagemId = perfilMaquiagemId;
    }
}