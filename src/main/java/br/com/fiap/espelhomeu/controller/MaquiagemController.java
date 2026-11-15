package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.MaquiagemDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoMaquiagem;
import br.com.fiap.espelhomeu.dto.CadastroMaquiagem;
import br.com.fiap.espelhomeu.dto.DetalhesMaquiagem;
import br.com.fiap.espelhomeu.model.Maquiagem;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/maquiagem")
public class MaquiagemController {

    private final MaquiagemDao dao;
    private final ModelMapper mapper;

    public MaquiagemController(MaquiagemDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesMaquiagem> listar() throws SQLException {
        return dao.listar().stream()
                .map(maquiagem -> mapper.map(maquiagem, DetalhesMaquiagem.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMaquiagem> buscar(@PathVariable long id) throws SQLException {
        Maquiagem maquiagem = dao.buscar(id);

        return ResponseEntity.ok(mapper.map(maquiagem, DetalhesMaquiagem.class));
    }

    @PostMapping
    public ResponseEntity<DetalhesMaquiagem> inserir(
            @RequestBody @Valid CadastroMaquiagem dto,
            UriComponentsBuilder uriBuilder) throws SQLException {

        Maquiagem maquiagem = mapper.map(dto, Maquiagem.class);

        dao.inserir(maquiagem);

        DetalhesMaquiagem detalhes = mapper.map(maquiagem, DetalhesMaquiagem.class);

        URI uri = uriBuilder
                .path("/maquiagem/{id}")
                .buildAndExpand(maquiagem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoMaquiagem dto) throws SQLException {

        Maquiagem maquiagem = dao.buscar(id);

        mapper.map(dto, maquiagem);

        dao.atualizar(maquiagem);

        return ResponseEntity.ok(mapper.map(maquiagem, DetalhesMaquiagem.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}