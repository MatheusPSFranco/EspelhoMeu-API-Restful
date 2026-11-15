package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.PerfilCabeloDao;
import br.com.fiap.espelhomeu.model.PerfilCabelo;
import br.com.fiap.espelhomeu.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/perfilcabelo")
public class PerfilCabeloController {
    private final PerfilCabeloDao dao;
    public PerfilCabeloController(PerfilCabeloDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<PerfilCabelo> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilCabelo> buscar(@PathVariable Long id)
            throws SQLException {

        PerfilCabelo perfilCabelo = dao.buscar(id);

        return ResponseEntity.ok(perfilCabelo);
    }

    @PostMapping
    public ResponseEntity<PerfilCabelo> adicionar(
            @RequestBody PerfilCabelo perfilCabelo,
            UriComponentsBuilder uriBuilder) throws SQLException {


        dao.inserir(perfilCabelo);

        URI uri = uriBuilder.path("/perfilCabelo/{id}")
                .buildAndExpand(perfilCabelo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(perfilCabelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody PerfilCabelo perfilCabelo) throws SQLException {

        perfilCabelo.setId(id);

        dao.atualizar(perfilCabelo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id)
            throws SQLException {

        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
