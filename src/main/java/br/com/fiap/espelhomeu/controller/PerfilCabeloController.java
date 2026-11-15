package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.PerfilCabeloDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoPerfilCabelo;
import br.com.fiap.espelhomeu.dto.CadastroPerfilCabelo;
import br.com.fiap.espelhomeu.dto.DetalhesPerfilCabelo;
import br.com.fiap.espelhomeu.model.PerfilCabelo;
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
@RequestMapping("/perfilcabelo")
public class PerfilCabeloController {

    private final PerfilCabeloDao dao;
    private final ModelMapper mapper;

    public PerfilCabeloController(PerfilCabeloDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesPerfilCabelo> listar()  {
        return dao.listar()
                .stream()
                .map(perfil -> {
                    DetalhesPerfilCabelo dto =
                            mapper.map(perfil, DetalhesPerfilCabelo.class);

                    dto.setUsuarioId(perfil.getUsuario().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPerfilCabelo> buscar(
            @PathVariable long id)  {

        PerfilCabelo perfil = dao.buscar(id);

        DetalhesPerfilCabelo dto =
                mapper.map(perfil, DetalhesPerfilCabelo.class);

        dto.setUsuarioId(perfil.getUsuario().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesPerfilCabelo> inserir(
            @RequestBody @Valid CadastroPerfilCabelo dto)  {

        PerfilCabelo perfil = mapper.map(dto, PerfilCabelo.class);

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

        DetalhesPerfilCabelo detalhes =
                mapper.map(perfil, DetalhesPerfilCabelo.class);

        detalhes.setUsuarioId(perfil.getUsuario().getId());

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesPerfilCabelo> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoPerfilCabelo dto)  {

        PerfilCabelo perfil = dao.buscar(id);

        mapper.map(dto, perfil);

        dao.atualizar(perfil);

        DetalhesPerfilCabelo detalhes =
                mapper.map(perfil, DetalhesPerfilCabelo.class);

        detalhes.setUsuarioId(perfil.getUsuario().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}