package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorteProdutoDao;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoCorte;
import br.com.fiap.espelhomeu.model.RecomendacaoCorteProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocorteproduto")
public class RecomendacaoCorteProdutoController {

    private final RecomendacaoCorteProdutoDao dao;

    public RecomendacaoCorteProdutoController(RecomendacaoCorteProdutoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoCorteProduto> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{idProduto}/{idRecomendacaoCorte}")
    public ResponseEntity<RecomendacaoCorteProduto> buscar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoCorte) throws SQLException {

        return ResponseEntity.ok(dao.buscar(idProduto, idRecomendacaoCorte));
    }

    @PostMapping
    public ResponseEntity<RecomendacaoCorteProduto> inserir(
            @RequestBody RecomendacaoCorteProduto recomendacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = uriBuilder
                .path("/recomendacaocorteproduto/{idProduto}/{idRecomendacaoCorte}")
                .buildAndExpand(
                        recomendacao.getProduto().getId(),
                        recomendacao.getRecomendacaoCorte().getId()
                )
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{idProduto}/{idRecomendacaoCorte}")
    public ResponseEntity<RecomendacaoCorteProduto> atualizar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoCorte,
            @RequestBody RecomendacaoCorteProduto recomendacao) throws SQLException {

        Produto produto = new Produto();
        produto.setId(idProduto);
        recomendacao.setProduto(produto);

        RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
        recomendacaoCorte.setId(idRecomendacaoCorte);
        recomendacao.setRecomendacaoCorte(recomendacaoCorte);

        return ResponseEntity.ok(dao.atualizar(recomendacao));
    }

    @DeleteMapping("/{idProduto}/{idRecomendacaoCorte}")
    public ResponseEntity<Void> deletar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoCorte) throws SQLException {

        dao.deletar(idProduto, idRecomendacaoCorte);
        return ResponseEntity.noContent().build();
    }
}