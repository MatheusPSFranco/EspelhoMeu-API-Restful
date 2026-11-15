package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.PenteadoDao;
import br.com.fiap.espelhomeu.model.Penteado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/penteado")
public class PenteadoController {

    private final PenteadoDao dao;

    public PenteadoController(PenteadoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Penteado> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penteado> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Penteado> inserir(
            @RequestBody Penteado penteado) throws SQLException {

        dao.inserir(penteado);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(penteado.getId())
                .toUri();

        return ResponseEntity.created(uri).body(penteado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Penteado> atualizar(
            @PathVariable long id,
            @RequestBody Penteado penteado) throws SQLException {

        penteado.setId(id);
        dao.atualizar(penteado);

        return ResponseEntity.ok(penteado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}