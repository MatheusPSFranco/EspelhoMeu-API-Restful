package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.ProdutoDao;
import br.com.fiap.espelhomeu.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoDao dao;

    public ProdutoController(ProdutoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Produto> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Produto> inserir(
            @RequestBody Produto produto,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(produto);

        URI uri = uriBuilder
                .path("/produto/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable long id,
            @RequestBody Produto produto) throws SQLException {

        produto.setId(id);

        return ResponseEntity.ok(dao.atualizar(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}