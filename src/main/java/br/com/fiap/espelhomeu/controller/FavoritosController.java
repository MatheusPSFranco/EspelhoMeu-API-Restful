package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.FavoritosDao;
import br.com.fiap.espelhomeu.model.Favoritos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritosDao dao;

    public FavoritosController(FavoritosDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Favoritos> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public Favoritos buscar(@PathVariable long id) throws SQLException {
        return dao.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Favoritos> inserir(
            @RequestBody Favoritos favoritos,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(favoritos);

        URI uri = uriBuilder.path("/favoritos/{id}")
                .buildAndExpand(favoritos.getId())
                .toUri();

        return ResponseEntity.created(uri).body(favoritos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favoritos> atualizar(
            @PathVariable long id,
            @RequestBody Favoritos favoritos) throws SQLException {

        favoritos.setId(id);
        dao.atualizar(favoritos);

        return ResponseEntity.ok(favoritos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}