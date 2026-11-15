package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoPenteadoProdutoDao;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteado;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteadoProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaopenteadoproduto")
public class RecomendacaoPenteadoProdutoController {

    private final RecomendacaoPenteadoProdutoDao dao;

    public RecomendacaoPenteadoProdutoController(RecomendacaoPenteadoProdutoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoPenteadoProduto> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{idProduto}/{idRecomendacaoPenteado}")
    public ResponseEntity<RecomendacaoPenteadoProduto> buscar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoPenteado) throws SQLException {

        return ResponseEntity.ok(dao.buscar(idProduto, idRecomendacaoPenteado));
    }

    @PostMapping
    public ResponseEntity<RecomendacaoPenteadoProduto> inserir(
            @RequestBody RecomendacaoPenteadoProduto recomendacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = uriBuilder
                .path("/recomendacaopenteadoproduto/{idProduto}/{idRecomendacaoPenteado}")
                .buildAndExpand(
                        recomendacao.getProduto().getId(),
                        recomendacao.getRecomendacaoPenteado().getId()
                )
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{idProduto}/{idRecomendacaoPenteado}")
    public ResponseEntity<RecomendacaoPenteadoProduto> atualizar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoPenteado,
            @RequestBody RecomendacaoPenteadoProduto recomendacao) throws SQLException {

        Produto produto = new Produto();
        produto.setId(idProduto);
        recomendacao.setProduto(produto);

        RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
        recomendacaoPenteado.setId(idRecomendacaoPenteado);
        recomendacao.setRecomendacaoPenteado(recomendacaoPenteado);

        return ResponseEntity.ok(dao.atualizar(recomendacao));
    }

    @DeleteMapping("/{idProduto}/{idRecomendacaoPenteado}")
    public ResponseEntity<Void> deletar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoPenteado) throws SQLException {

        dao.deletar(idProduto, idRecomendacaoPenteado);
        return ResponseEntity.noContent().build();
    }
}