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

import java.math.BigDecimal;
import java.net.URI;
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
    public List<DetalhesProduto> listar()  {
        return dao.listar().stream()
                .map(produto -> mapper.map(produto, DetalhesProduto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProduto> buscar(@PathVariable long id)  {
        Produto produto = dao.buscar(id);

        return ResponseEntity.ok(mapper.map(produto, DetalhesProduto.class));
    }

    @PostMapping
    public ResponseEntity<DetalhesProduto> inserir(
            @RequestBody @Valid CadastroProduto dto,
            UriComponentsBuilder uriBuilder)  {

        Produto produto = mapper.map(dto, Produto.class);

        definirFaixaPreco(produto);

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
            @RequestBody @Valid AtualizacaoProduto dto)  {

        Produto produto = dao.buscar(id);

        mapper.map(dto, produto);

        definirFaixaPreco(produto);

        dao.atualizar(produto);

        return ResponseEntity.ok(mapper.map(produto, DetalhesProduto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private void definirFaixaPreco(Produto produto) {

        BigDecimal preco = produto.getPrecoReferencia();

        if (preco == null) {
            produto.setFaixaPreco(null);
            return;
        }

        if (preco.compareTo(new BigDecimal("50.00")) < 0) {
            produto.setFaixaPreco("Custo-beneficio");

        } else if (preco.compareTo(new BigDecimal("100.00")) < 0) {
            produto.setFaixaPreco("Intermediário");

        } else {
            produto.setFaixaPreco("Caro");
        }
    }
}