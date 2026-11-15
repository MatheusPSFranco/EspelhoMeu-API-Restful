package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CadastroConsultoriaCapilar {

    @NotNull(message = "O ID do perfil de cabelo é obrigatório")
    @Positive(message = "O ID do perfil de cabelo deve ser positivo")
    private Long perfilCabeloId;

    //construtores

    public CadastroConsultoriaCapilar() {}

    public CadastroConsultoriaCapilar(Long perfilCabeloId) {
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