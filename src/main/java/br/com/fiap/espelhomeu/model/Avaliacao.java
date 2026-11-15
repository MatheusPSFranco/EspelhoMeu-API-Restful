package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class Avaliacao {

    private Long id;
    private Usuario usuario;
    private RecomendacaoCor recomendacaoCor;
    private RecomendacaoCorte recomendacaoCorte;
    private RecomendacaoPenteado recomendacaoPenteado;
    private RecomendacaoMaquiagem recomendacaoMaquiagem;
    private Double nota;
    private String mensagem;
    private LocalDateTime data;

    //Constructors

    public Avaliacao() {}

    public Avaliacao(Long id, Usuario usuario, RecomendacaoCor recomendacaoCor, RecomendacaoCorte recomendacaoCorte, RecomendacaoPenteado recomendacaoPenteado, RecomendacaoMaquiagem recomendacaoMaquiagem, Double nota, String mensagem, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.recomendacaoCor = recomendacaoCor;
        this.recomendacaoCorte = recomendacaoCorte;
        this.recomendacaoPenteado = recomendacaoPenteado;
        this.recomendacaoMaquiagem = recomendacaoMaquiagem;
        this.nota = nota;
        this.mensagem = mensagem;
        this.data = data;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RecomendacaoCor getRecomendacaoCor() {
        return recomendacaoCor;
    }

    public void setRecomendacaoCor(RecomendacaoCor recomendacaoCor) {
        this.recomendacaoCor = recomendacaoCor;
    }

    public RecomendacaoCorte getRecomendacaoCorte() {
        return recomendacaoCorte;
    }

    public void setRecomendacaoCorte(RecomendacaoCorte recomendacaoCorte) {
        this.recomendacaoCorte = recomendacaoCorte;
    }

    public RecomendacaoPenteado getRecomendacaoPenteado() {
        return recomendacaoPenteado;
    }

    public void setRecomendacaoPenteado(RecomendacaoPenteado recomendacaoPenteado) {
        this.recomendacaoPenteado = recomendacaoPenteado;
    }

    public RecomendacaoMaquiagem getRecomendacaoMaquiagem() {
        return recomendacaoMaquiagem;
    }

    public void setRecomendacaoMaquiagem(RecomendacaoMaquiagem recomendacaoMaquiagem) {
        this.recomendacaoMaquiagem = recomendacaoMaquiagem;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Usuario: "+ usuario.getId() +", Recomendacao Cor: " +recomendacaoCor.getId() + ", Recomendacao Corte: "+ recomendacaoCorte.getId()+
                ", Recomendacao Penteado: " + recomendacaoPenteado.getId()+ ", Recomendacao Maquiagem: " + recomendacaoMaquiagem.getId()+ ", Nota: "+ nota+
                ", Mensagem: "+mensagem+", Data: "+ data;
    }
}
