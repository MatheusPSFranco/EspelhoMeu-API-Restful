package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoPenteadoDao;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaopenteado")
public class RecomendacaoPenteadoController {

    private final RecomendacaoPenteadoDao dao;

    public RecomendacaoPenteadoController(RecomendacaoPenteadoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoPenteado> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoPenteado> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<RecomendacaoPenteado> inserir(
            @RequestBody RecomendacaoPenteado recomendacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = uriBuilder
                .path("/recomendacaopenteado/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoPenteado> atualizar(
            @PathVariable long id,
            @RequestBody RecomendacaoPenteado recomendacao) throws SQLException {

        recomendacao.setId(id);

        return ResponseEntity.ok(dao.atualizar(recomendacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}