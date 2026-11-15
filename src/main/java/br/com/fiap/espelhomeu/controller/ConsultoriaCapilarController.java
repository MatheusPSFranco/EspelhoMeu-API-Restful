package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.ConsultoriaCapilarDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoConsultoriaCapilar;
import br.com.fiap.espelhomeu.dto.CadastroConsultoriaCapilar;
import br.com.fiap.espelhomeu.dto.DetalhesConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.PerfilCabelo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultoriacapilar")
public class ConsultoriaCapilarController {

    private final ConsultoriaCapilarDao dao;
    private final ModelMapper mapper;

    public ConsultoriaCapilarController(
            ConsultoriaCapilarDao dao,
            ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesConsultoriaCapilar> listar()  {
        return dao.listar()
                .stream()
                .map(consultoria -> {
                    DetalhesConsultoriaCapilar dto =
                            mapper.map(consultoria, DetalhesConsultoriaCapilar.class);

                    dto.setPerfilCabeloId(consultoria.getPerfilCabelo().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesConsultoriaCapilar> buscar(
            @PathVariable long id)  {

        ConsultoriaCapilar consultoria = dao.buscar(id);

        DetalhesConsultoriaCapilar dto =
                mapper.map(consultoria, DetalhesConsultoriaCapilar.class);

        dto.setPerfilCabeloId(consultoria.getPerfilCabelo().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesConsultoriaCapilar> inserir(
            @RequestBody @Valid CadastroConsultoriaCapilar dto)  {

        ConsultoriaCapilar consultoria =
                mapper.map(dto, ConsultoriaCapilar.class);

        PerfilCabelo perfilCabelo = new PerfilCabelo();
        perfilCabelo.setId(dto.getPerfilCabeloId());

        consultoria.setPerfilCabelo(perfilCabelo);
        consultoria.setData(LocalDateTime.now());

        dao.inserir(consultoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultoria.getId())
                .toUri();

        DetalhesConsultoriaCapilar detalhes =
                mapper.map(consultoria, DetalhesConsultoriaCapilar.class);

        detalhes.setPerfilCabeloId(consultoria.getPerfilCabelo().getId());

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesConsultoriaCapilar> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoConsultoriaCapilar dto)  {

        ConsultoriaCapilar consultoria = dao.buscar(id);

        mapper.map(dto, consultoria);

        PerfilCabelo perfilCabelo = new PerfilCabelo();
        perfilCabelo.setId(dto.getPerfilCabeloId());

        consultoria.setPerfilCabelo(perfilCabelo);

        dao.atualizar(consultoria);

        DetalhesConsultoriaCapilar detalhes =
                mapper.map(consultoria, DetalhesConsultoriaCapilar.class);

        detalhes.setPerfilCabeloId(consultoria.getPerfilCabelo().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}