package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorDao;
import br.com.fiap.espelhomeu.model.RecomendacaoCor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocor")
public class RecomendacaoCorController {

    private final RecomendacaoCorDao dao;

    public RecomendacaoCorController(RecomendacaoCorDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoCor> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoCor> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<RecomendacaoCor> inserir(
            @RequestBody RecomendacaoCor recomendacao) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoCor> atualizar(
            @PathVariable long id,
            @RequestBody RecomendacaoCor recomendacao) throws SQLException {

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