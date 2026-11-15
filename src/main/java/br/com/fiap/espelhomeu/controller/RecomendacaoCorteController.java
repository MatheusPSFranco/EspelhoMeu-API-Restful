package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorteDao;
import br.com.fiap.espelhomeu.model.RecomendacaoCorte;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocorte")
public class RecomendacaoCorteController {

    private final RecomendacaoCorteDao dao;

    public RecomendacaoCorteController(RecomendacaoCorteDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoCorte> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoCorte> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<RecomendacaoCorte> inserir(
            @RequestBody RecomendacaoCorte recomendacao) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoCorte> atualizar(
            @PathVariable long id,
            @RequestBody RecomendacaoCorte recomendacao) throws SQLException {

        recomendacao.setId(id);
        dao.atualizar(recomendacao);

        return ResponseEntity.ok(recomendacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}