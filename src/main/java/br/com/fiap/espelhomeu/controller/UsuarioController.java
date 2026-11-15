package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.UsuarioDao;
import br.com.fiap.espelhomeu.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioDao dao;
    public UsuarioController(UsuarioDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Usuario> listar() throws SQLException {
        return dao.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id)
            throws SQLException {

        Usuario usuario = dao.buscar(id);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionar(
            @RequestBody Usuario usuario,
            UriComponentsBuilder uriBuilder) throws SQLException {


        dao.inserir(usuario);

        URI uri = uriBuilder.path("/usuario/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) throws SQLException {

        usuario.setId(id);

        dao.atualizar(usuario);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id)
            throws SQLException {

        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
