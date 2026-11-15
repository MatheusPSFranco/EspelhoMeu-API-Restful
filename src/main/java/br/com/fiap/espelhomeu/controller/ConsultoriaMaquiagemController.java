package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.ConsultoriaMaquiagemDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.dto.CadastroConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.dto.DetalhesConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.model.ConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.model.PerfilMaquiagem;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultoriamaquiagem")
public class ConsultoriaMaquiagemController {

    private final ConsultoriaMaquiagemDao dao;
    private final ModelMapper mapper;

    public ConsultoriaMaquiagemController(
            ConsultoriaMaquiagemDao dao,
            ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesConsultoriaMaquiagem> listar()  {
        return dao.listar()
                .stream()
                .map(consultoria -> {
                    DetalhesConsultoriaMaquiagem dto =
                            mapper.map(consultoria, DetalhesConsultoriaMaquiagem.class);

                    dto.setPerfilMaquiagemId(consultoria.getPerfilMaquiagem().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesConsultoriaMaquiagem> buscar(
            @PathVariable long id)  {

        ConsultoriaMaquiagem consultoria = dao.buscar(id);

        DetalhesConsultoriaMaquiagem dto =
                mapper.map(consultoria, DetalhesConsultoriaMaquiagem.class);

        dto.setPerfilMaquiagemId(consultoria.getPerfilMaquiagem().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesConsultoriaMaquiagem> inserir(
            @RequestBody @Valid CadastroConsultoriaMaquiagem dto)  {

        ConsultoriaMaquiagem consultoria =
                mapper.map(dto, ConsultoriaMaquiagem.class);

        PerfilMaquiagem perfilMaquiagem = new PerfilMaquiagem();
        perfilMaquiagem.setId(dto.getPerfilMaquiagemId());

        consultoria.setPerfilMaquiagem(perfilMaquiagem);
        consultoria.setData(LocalDateTime.now());

        dao.inserir(consultoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultoria.getId())
                .toUri();

        DetalhesConsultoriaMaquiagem detalhes =
                mapper.map(consultoria, DetalhesConsultoriaMaquiagem.class);

        detalhes.setPerfilMaquiagemId(consultoria.getPerfilMaquiagem().getId());

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesConsultoriaMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoConsultoriaMaquiagem dto)  {

        ConsultoriaMaquiagem consultoria = dao.buscar(id);

        mapper.map(dto, consultoria);

        PerfilMaquiagem perfilMaquiagem = new PerfilMaquiagem();
        perfilMaquiagem.setId(dto.getPerfilMaquiagemId());

        consultoria.setPerfilMaquiagem(perfilMaquiagem);

        dao.atualizar(consultoria);

        DetalhesConsultoriaMaquiagem detalhes =
                mapper.map(consultoria, DetalhesConsultoriaMaquiagem.class);

        detalhes.setPerfilMaquiagemId(consultoria.getPerfilMaquiagem().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}