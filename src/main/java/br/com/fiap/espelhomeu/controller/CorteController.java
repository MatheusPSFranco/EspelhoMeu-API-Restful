package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.CorteDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoCorte;
import br.com.fiap.espelhomeu.dto.CadastroCorte;
import br.com.fiap.espelhomeu.dto.DetalhesCorte;
import br.com.fiap.espelhomeu.model.Corte;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/corte")
public class CorteController {

    private final CorteDao dao;
    private final ModelMapper mapper;

    public CorteController(CorteDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesCorte> listar()  {
        return dao.listar()
                .stream()
                .map(corte -> mapper.map(corte, DetalhesCorte.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCorte> buscar(
            @PathVariable long id)  {

        Corte corte = dao.buscar(id);

        DetalhesCorte dto = mapper.map(corte, DetalhesCorte.class);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesCorte> inserir(
            @RequestBody @Valid CadastroCorte dto)  {

        Corte corte = mapper.map(dto, Corte.class);

        dao.inserir(corte);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(corte.getId())
                .toUri();

        DetalhesCorte detalhes = mapper.map(corte, DetalhesCorte.class);

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesCorte> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoCorte dto)  {

        Corte corte = dao.buscar(id);

        mapper.map(dto, corte);

        dao.atualizar(corte);

        DetalhesCorte detalhes = mapper.map(corte, DetalhesCorte.class);

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}