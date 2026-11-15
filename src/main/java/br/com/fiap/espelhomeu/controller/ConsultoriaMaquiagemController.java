package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.ConsultoriaMaquiagemDao;
import br.com.fiap.espelhomeu.model.ConsultoriaMaquiagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/consultoriamaquiagem")
public class ConsultoriaMaquiagemController {

    private final ConsultoriaMaquiagemDao dao;

    public ConsultoriaMaquiagemController(ConsultoriaMaquiagemDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<ConsultoriaMaquiagem> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultoriaMaquiagem> buscar(@PathVariable long id) throws SQLException {
        return ResponseEntity.ok(dao.buscar(id));
    }

    @PostMapping
    public ResponseEntity<ConsultoriaMaquiagem> inserir(
            @RequestBody ConsultoriaMaquiagem consultoria) throws SQLException {

        dao.inserir(consultoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultoria.getId())
                .toUri();

        return ResponseEntity.created(uri).body(consultoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultoriaMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody ConsultoriaMaquiagem consultoria) throws SQLException {

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