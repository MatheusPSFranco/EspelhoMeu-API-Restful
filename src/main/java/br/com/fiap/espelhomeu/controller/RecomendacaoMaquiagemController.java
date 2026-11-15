package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoMaquiagemDao;
import br.com.fiap.espelhomeu.model.RecomendacaoMaquiagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recomendacaomaquiagem")
public class RecomendacaoMaquiagemController {

    private final RecomendacaoMaquiagemDao dao;

    public RecomendacaoMaquiagemController(RecomendacaoMaquiagemDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<RecomendacaoMaquiagem> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public RecomendacaoMaquiagem buscar(@PathVariable long id) throws SQLException {
        return dao.buscar(id);
    }

    @PostMapping
    public ResponseEntity<RecomendacaoMaquiagem> inserir(
            @RequestBody RecomendacaoMaquiagem recomendacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(recomendacao);

        URI uri = uriBuilder.path("/recomendacaomaquiagem/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(recomendacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody RecomendacaoMaquiagem recomendacao) throws SQLException {

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