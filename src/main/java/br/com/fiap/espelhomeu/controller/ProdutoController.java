package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.ProdutoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoProduto;
import br.com.fiap.espelhomeu.dto.CadastroProduto;
import br.com.fiap.espelhomeu.dto.DetalhesProduto;
import br.com.fiap.espelhomeu.model.Produto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoDao dao;
    private final ModelMapper mapper;

    public ProdutoController(ProdutoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesProduto> listar() throws SQLException {
        return dao.listar().stream()
                .map(produto -> mapper.map(produto, DetalhesProduto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProduto> buscar(@PathVariable long id) throws SQLException {
        Produto produto = dao.buscar(id);

        return ResponseEntity.ok(mapper.map(produto, DetalhesProduto.class));
    }

    @PostMapping
    public ResponseEntity<DetalhesProduto> inserir(
            @RequestBody @Valid CadastroProduto dto,
            UriComponentsBuilder uriBuilder) throws SQLException {

        Produto produto = mapper.map(dto, Produto.class);

        dao.inserir(produto);

        DetalhesProduto detalhes = mapper.map(produto, DetalhesProduto.class);

        URI uri = uriBuilder
                .path("/produto/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesProduto> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoProduto dto) throws SQLException {

        Produto produto = dao.buscar(id);

        mapper.map(dto, produto);

        dao.atualizar(produto);

        return ResponseEntity.ok(mapper.map(produto, DetalhesProduto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) throws SQLException {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}