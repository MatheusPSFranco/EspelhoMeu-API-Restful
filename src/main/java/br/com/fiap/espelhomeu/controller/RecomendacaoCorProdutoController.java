package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorProdutoDao;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoCor;
import br.com.fiap.espelhomeu.model.RecomendacaoCorProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocorproduto")
public class RecomendacaoCorProdutoController {

    private final RecomendacaoCorProdutoDao dao;

    public RecomendacaoCorProdutoController(RecomendacaoCorProdutoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoCorProduto> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{idRecomendacaoCor}/{idProduto}")
    public ResponseEntity<RecomendacaoCorProduto> buscar(
            @PathVariable long idRecomendacaoCor,
            @PathVariable long idProduto) throws SQLException {

        return ResponseEntity.ok(dao.buscar(idRecomendacaoCor, idProduto));
    }

    @PostMapping
    public ResponseEntity<RecomendacaoCorProduto> inserir(
            @RequestBody RecomendacaoCorProduto recomendacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = uriBuilder
                .path("/recomendacaocorproduto/{idRecomendacaoCor}/{idProduto}")
                .buildAndExpand(
                        recomendacao.getRecomendacaoCor().getId(),
                        recomendacao.getProduto().getId()
                )
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{idRecomendacaoCor}/{idProduto}")
    public ResponseEntity<RecomendacaoCorProduto> atualizar(
            @PathVariable long idRecomendacaoCor,
            @PathVariable long idProduto,
            @RequestBody RecomendacaoCorProduto recomendacao) throws SQLException {

        RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
        recomendacaoCor.setId(idRecomendacaoCor);
        recomendacao.setRecomendacaoCor(recomendacaoCor);

        Produto produto = new Produto();
        produto.setId(idProduto);
        recomendacao.setProduto(produto);

        return ResponseEntity.ok(dao.atualizar(recomendacao));
    }

    @DeleteMapping("/{idRecomendacaoCor}/{idProduto}")
    public ResponseEntity<Void> deletar(
            @PathVariable long idRecomendacaoCor,
            @PathVariable long idProduto) throws SQLException {

        dao.deletar(idRecomendacaoCor, idProduto);
        return ResponseEntity.noContent().build();
    }
}