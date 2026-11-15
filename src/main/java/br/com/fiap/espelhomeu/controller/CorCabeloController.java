package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.CorCabeloDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoCorCabelo;
import br.com.fiap.espelhomeu.dto.CadastroCorCabelo;
import br.com.fiap.espelhomeu.dto.DetalhesCorCabelo;
import br.com.fiap.espelhomeu.model.CorCabelo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/corcabelo")
public class CorCabeloController {

    private final CorCabeloDao dao;
    private final ModelMapper mapper;

    public CorCabeloController(CorCabeloDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesCorCabelo> listar()  {
        return dao.listar()
                .stream()
                .map(cor -> mapper.map(cor, DetalhesCorCabelo.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCorCabelo> buscar(@PathVariable long id)  {
        CorCabelo cor = dao.buscar(id);

        DetalhesCorCabelo detalhes = mapper.map(cor, DetalhesCorCabelo.class);

        return ResponseEntity.ok(detalhes);
    }

    @PostMapping
    public ResponseEntity<DetalhesCorCabelo> inserir(
            @RequestBody @Valid CadastroCorCabelo dto)  {

        CorCabelo cor = mapper.map(dto, CorCabelo.class);

        dao.inserir(cor);

        DetalhesCorCabelo detalhes = mapper.map(cor, DetalhesCorCabelo.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cor.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesCorCabelo> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoCorCabelo dto)  {

        CorCabelo cor = dao.buscar(id);

        mapper.map(dto, cor);

        dao.atualizar(cor);

        DetalhesCorCabelo detalhes = mapper.map(cor, DetalhesCorCabelo.class);

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}