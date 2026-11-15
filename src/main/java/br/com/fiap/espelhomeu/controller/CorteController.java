package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.CorteDao;
import br.com.fiap.espelhomeu.model.Corte;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/corte")
public class CorteController {

    private final CorteDao dao;

    public CorteController(CorteDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Corte> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Corte> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Corte> inserir(
            @RequestBody Corte corte) throws SQLException {

        dao.inserir(corte);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(corte.getId())
                .toUri();

        return ResponseEntity.created(uri).body(corte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Corte> atualizar(
            @PathVariable long id,
            @RequestBody Corte corte) throws SQLException {

        corte.setId(id);
        dao.atualizar(corte);

        return ResponseEntity.ok(corte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}