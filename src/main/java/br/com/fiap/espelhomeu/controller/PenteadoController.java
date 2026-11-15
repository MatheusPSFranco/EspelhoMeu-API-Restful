package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.PenteadoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoPenteado;
import br.com.fiap.espelhomeu.dto.CadastroPenteado;
import br.com.fiap.espelhomeu.dto.DetalhesPenteado;
import br.com.fiap.espelhomeu.model.Penteado;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/penteado")
public class PenteadoController {

    private final PenteadoDao dao;
    private final ModelMapper mapper;

    public PenteadoController(PenteadoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesPenteado> listar()  {
        return dao.listar()
                .stream()
                .map(penteado -> mapper.map(penteado, DetalhesPenteado.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPenteado> buscar(@PathVariable long id)  {
        Penteado penteado = dao.buscar(id);

        DetalhesPenteado detalhes = mapper.map(penteado, DetalhesPenteado.class);

        return ResponseEntity.ok(detalhes);
    }

    @PostMapping
    public ResponseEntity<DetalhesPenteado> inserir(
            @RequestBody @Valid CadastroPenteado dto)  {

        Penteado penteado = mapper.map(dto, Penteado.class);

        dao.inserir(penteado);

        DetalhesPenteado detalhes = mapper.map(penteado, DetalhesPenteado.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(penteado.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesPenteado> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoPenteado dto)  {

        Penteado penteado = dao.buscar(id);

        mapper.map(dto, penteado);

        dao.atualizar(penteado);

        DetalhesPenteado detalhes = mapper.map(penteado, DetalhesPenteado.class);

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}