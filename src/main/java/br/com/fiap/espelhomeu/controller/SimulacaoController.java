package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.SimulacaoDao;
import br.com.fiap.espelhomeu.model.Simulacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {

    private final SimulacaoDao dao;

    public SimulacaoController(SimulacaoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Simulacao> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public Simulacao buscar(@PathVariable long id) throws SQLException {
        return dao.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Simulacao> inserir(
            @RequestBody Simulacao simulacao,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(simulacao);

        URI uri = uriBuilder.path("/simulacao/{id}")
                .buildAndExpand(simulacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(simulacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Simulacao> atualizar(
            @PathVariable long id,
            @RequestBody Simulacao simulacao) throws SQLException {

        simulacao.setId(id);
        dao.atualizar(simulacao);

        return ResponseEntity.ok(simulacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}