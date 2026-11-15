package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AtualizacaoConsultoriaCapilar {

    @NotNull(message = "O ID do perfil de cabelo é obrigatório")
    @Positive(message = "O ID do perfil de cabelo deve ser positivo")
    private Long perfilCabeloId;

    //construtores

    public AtualizacaoConsultoriaCapilar() {}

    public AtualizacaoConsultoriaCapilar(Long perfilCabeloId) {
        this.perfilCabeloId = perfilCabeloId;
    }

    //getters and setters

    public Long getPerfilCabeloId() {
        return perfilCabeloId;
    }

    public void setPerfilCabeloId(Long perfilCabeloId) {
        this.perfilCabeloId = perfilCabeloId;
    }
}