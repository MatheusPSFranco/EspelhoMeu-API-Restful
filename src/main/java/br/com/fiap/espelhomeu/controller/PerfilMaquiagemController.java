package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.PerfilMaquiagemDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoPerfilMaquiagem;
import br.com.fiap.espelhomeu.dto.CadastroPerfilMaquiagem;
import br.com.fiap.espelhomeu.dto.DetalhesPerfilMaquiagem;
import br.com.fiap.espelhomeu.model.PerfilMaquiagem;
import br.com.fiap.espelhomeu.model.Usuario;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/perfilmaquiagem")
public class PerfilMaquiagemController {

    private final PerfilMaquiagemDao dao;
    private final ModelMapper mapper;

    public PerfilMaquiagemController(PerfilMaquiagemDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesPerfilMaquiagem> listar()  {
        return dao.listar()
                .stream()
                .map(perfil -> {
                    DetalhesPerfilMaquiagem dto =
                            mapper.map(perfil, DetalhesPerfilMaquiagem.class);

                    dto.setUsuarioId(perfil.getUsuario().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPerfilMaquiagem> buscar(
            @PathVariable long id)  {

        PerfilMaquiagem perfil = dao.buscar(id);

        DetalhesPerfilMaquiagem dto =
                mapper.map(perfil, DetalhesPerfilMaquiagem.class);

        dto.setUsuarioId(perfil.getUsuario().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesPerfilMaquiagem> inserir(
            @RequestBody @Valid CadastroPerfilMaquiagem dto)  {

        PerfilMaquiagem perfil = mapper.map(dto, PerfilMaquiagem.class);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());

        perfil.setUsuario(usuario);
        perfil.setData(LocalDateTime.now());

        dao.inserir(perfil);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(perfil.getId())
                .toUri();

        DetalhesPerfilMaquiagem detalhes =
                mapper.map(perfil, DetalhesPerfilMaquiagem.class);

        detalhes.setUsuarioId(perfil.getUsuario().getId());

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesPerfilMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoPerfilMaquiagem dto)  {

        PerfilMaquiagem perfil = dao.buscar(id);

        mapper.map(dto, perfil);

        dao.atualizar(perfil);

        DetalhesPerfilMaquiagem detalhes =
                mapper.map(perfil, DetalhesPerfilMaquiagem.class);

        detalhes.setUsuarioId(perfil.getUsuario().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}