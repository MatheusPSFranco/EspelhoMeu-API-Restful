package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.ConsultoriaCapilarDao;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/consultoriacapilar")
public class ConsultoriaCapilarController {

    private final ConsultoriaCapilarDao dao;

    public ConsultoriaCapilarController(ConsultoriaCapilarDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<ConsultoriaCapilar> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultoriaCapilar> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<ConsultoriaCapilar> inserir(
            @RequestBody ConsultoriaCapilar consultoria) throws SQLException {

        dao.inserir(consultoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultoria.getId())
                .toUri();

        return ResponseEntity.created(uri).body(consultoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultoriaCapilar> atualizar(
            @PathVariable long id,
            @RequestBody ConsultoriaCapilar consultoria) throws SQLException {

        consultoria.setId(id);
        dao.atualizar(consultoria);

        return ResponseEntity.ok(consultoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}