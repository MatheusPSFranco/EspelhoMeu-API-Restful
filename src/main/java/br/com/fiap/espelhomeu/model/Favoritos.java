package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class Favoritos {

    private Long id;
    private Usuario usuario;
    private Corte corte;
    private Maquiagem maquiagem;
    private CorCabelo corCabelo;
    private RecomendacaoCorte recomendacaoCorte;
    private RecomendacaoCor recomendacaoCor;
    private RecomendacaoPenteado recomendacaoPenteado;
    private Penteado penteado;
    private Produto produto;
    private RecomendacaoMaquiagem recomendacaoMaquiagem;
    private Simulacao simulacao;
    private LocalDateTime data;

    //Constructor

    public Favoritos() {}

    public Favoritos(Long id, Usuario usuario, Corte corte, Maquiagem maquiagem, CorCabelo corCabelo, RecomendacaoCorte recomendacaoCorte, RecomendacaoCor recomendacaoCor, RecomendacaoPenteado recomendacaoPenteado, Penteado penteado, Produto produto, RecomendacaoMaquiagem recomendacaoMaquiagem, Simulacao simulacao, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.corte = corte;
        this.maquiagem = maquiagem;
        this.corCabelo = corCabelo;
        this.recomendacaoCorte = recomendacaoCorte;
        this.recomendacaoCor = recomendacaoCor;
        this.recomendacaoPenteado = recomendacaoPenteado;
        this.penteado = penteado;
        this.produto = produto;
        this.recomendacaoMaquiagem = recomendacaoMaquiagem;
        this.simulacao = simulacao;
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

    public Corte getCorte() {
        return corte;
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
    }

    public Maquiagem getMaquiagem() {
        return maquiagem;
    }

    public void setMaquiagem(Maquiagem maquiagem) {
        this.maquiagem = maquiagem;
    }

    public CorCabelo getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(CorCabelo corCabelo) {
        this.corCabelo = corCabelo;
    }

    public RecomendacaoCorte getRecomendacaoCorte() {
        return recomendacaoCorte;
    }

    public void setRecomendacaoCorte(RecomendacaoCorte recomendacaoCorte) {
        this.recomendacaoCorte = recomendacaoCorte;
    }

    public RecomendacaoCor getRecomendacaoCor() {
        return recomendacaoCor;
    }

    public void setRecomendacaoCor(RecomendacaoCor recomendacaoCor) {
        this.recomendacaoCor = recomendacaoCor;
    }

    public RecomendacaoPenteado getRecomendacaoPenteado() {
        return recomendacaoPenteado;
    }

    public void setRecomendacaoPenteado(RecomendacaoPenteado recomendacaoPenteado) {
        this.recomendacaoPenteado = recomendacaoPenteado;
    }

    public Penteado getPenteado() {
        return penteado;
    }

    public void setPenteado(Penteado penteado) {
        this.penteado = penteado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public RecomendacaoMaquiagem getRecomendacaoMaquiagem() {
        return recomendacaoMaquiagem;
    }

    public void setRecomendacaoMaquiagem(RecomendacaoMaquiagem recomendacaoMaquiagem) {
        this.recomendacaoMaquiagem = recomendacaoMaquiagem;
    }

    public Simulacao getSimulacao() {
        return simulacao;
    }

    public void setSimulacao(Simulacao simulacao) {
        this.simulacao = simulacao;
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
        return "Id: "+ id +", Usuario: "+usuario.getId()+", Corte: "+corte.getId()+", Maquiagem: "+maquiagem.getId()+", Cor de Cabelo"+corCabelo.getId()+
                ", Recomendacao Corte: "+recomendacaoCorte.getId()+", Recomendacao Cor: "+recomendacaoCor.getId()+", Recomendacao Penteado: "+recomendacaoPenteado.getId()+
                ", Penteado: "+penteado.getId()+", Produto: "+produto.getId()+", Recomendacao Maquiagem: "+recomendacaoMaquiagem.getId()+", Simulacao: "+simulacao.getId()+
                ", Data: "+data;
    }
}
