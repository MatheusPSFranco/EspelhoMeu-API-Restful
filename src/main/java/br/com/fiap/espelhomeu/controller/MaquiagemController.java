package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.MaquiagemDao;
import br.com.fiap.espelhomeu.model.Maquiagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/maquiagem")
public class MaquiagemController {

    private final MaquiagemDao dao;

    public MaquiagemController(MaquiagemDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Maquiagem> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquiagem> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Maquiagem> inserir(
            @RequestBody Maquiagem maquiagem,
            UriComponentsBuilder uriBuilder) throws SQLException {

        dao.inserir(maquiagem);

        URI uri = uriBuilder
                .path("/maquiagem/{id}")
                .buildAndExpand(maquiagem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(maquiagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquiagem> atualizar(
            @PathVariable long id,
            @RequestBody Maquiagem maquiagem) throws SQLException {

        maquiagem.setId(id);

        return ResponseEntity.ok(dao.atualizar(maquiagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}