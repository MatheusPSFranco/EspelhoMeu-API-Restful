package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.PerfilMaquiagemDao;
import br.com.fiap.espelhomeu.model.PerfilMaquiagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/perfilmaquiagem")
public class PerfilMaquiagemController {
    private final PerfilMaquiagemDao dao;
    public PerfilMaquiagemController(PerfilMaquiagemDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<PerfilMaquiagem> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilMaquiagem> buscar(@PathVariable Long id)
            throws SQLException {

        PerfilMaquiagem perfilMaquiagem = dao.buscar(id);

        return ResponseEntity.ok(perfilMaquiagem);
    }

    @PostMapping
    public ResponseEntity<PerfilMaquiagem> adicionar(
            @RequestBody PerfilMaquiagem perfilMaquiagem,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(perfilMaquiagem);

        URI uri = uriBuilder.path("/perfilmaquiagem/{id}")
                .buildAndExpand(perfilMaquiagem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(perfilMaquiagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody PerfilMaquiagem perfilMaquiagem) throws SQLException {

        perfilMaquiagem.setId(id);

        dao.atualizar(perfilMaquiagem);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id)
            throws SQLException {

        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}