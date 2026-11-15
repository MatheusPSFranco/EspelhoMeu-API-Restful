package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.AvaliacaoDao;
import br.com.fiap.espelhomeu.model.Avaliacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoDao dao;

    public AvaliacaoController(AvaliacaoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Avaliacao> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public Avaliacao buscar(@PathVariable long id) throws SQLException {
        return dao.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> inserir(
            @RequestBody Avaliacao avaliacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(avaliacao);

        URI uri = uriBuilder.path("/avaliacao/{id}")
                .buildAndExpand(avaliacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(
            @PathVariable long id,
            @RequestBody Avaliacao avaliacao) throws SQLException {

        avaliacao.setId(id);
        dao.atualizar(avaliacao);

        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}