package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.UsuarioDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoUsuario;
import br.com.fiap.espelhomeu.dto.CadastroUsuario;
import br.com.fiap.espelhomeu.dto.DetalhesUsuario;
import br.com.fiap.espelhomeu.model.Usuario;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioDao dao;
    private final ModelMapper mapper;

    public UsuarioController(UsuarioDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesUsuario> listar()  {
        return dao.listar()
                .stream()
                .map(usuario -> mapper.map(usuario, DetalhesUsuario.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuario> buscar(
            @PathVariable long id)  {

        Usuario usuario = dao.buscar(id);

        DetalhesUsuario dto = mapper.map(usuario, DetalhesUsuario.class);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesUsuario> inserir(
            @RequestBody @Valid CadastroUsuario dto)  {

        Usuario usuario = mapper.map(dto, Usuario.class);

        dao.inserir(usuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        DetalhesUsuario detalhes = mapper.map(usuario, DetalhesUsuario.class);

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesUsuario> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoUsuario dto)  {

        Usuario usuario = dao.buscar(id);

        mapper.map(dto, usuario);

        dao.atualizar(usuario);

        DetalhesUsuario detalhes = mapper.map(usuario, DetalhesUsuario.class);

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}