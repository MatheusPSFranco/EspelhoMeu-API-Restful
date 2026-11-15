package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.CorCabeloDao;
import br.com.fiap.espelhomeu.model.CorCabelo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/corcabelo")
public class CorCabeloController {

    private final CorCabeloDao dao;

    public CorCabeloController(CorCabeloDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<CorCabelo> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorCabelo> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CorCabelo> inserir(
            @RequestBody CorCabelo cor) throws SQLException {

        dao.inserir(cor);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cor.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorCabelo> atualizar(
            @PathVariable long id,
            @RequestBody CorCabelo cor) throws SQLException {

        cor.setId(id);
        dao.atualizar(cor);

        return ResponseEntity.ok(cor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}