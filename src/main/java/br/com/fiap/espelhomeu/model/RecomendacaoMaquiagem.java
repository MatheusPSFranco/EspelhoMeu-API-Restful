package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class RecomendacaoMaquiagem {

    private Long Id;
    private ConsultoriaMaquiagem consultoriaMaquiagem;
    private Maquiagem maquiagem;
    private String justificativa;
    private LocalDateTime data;
}
