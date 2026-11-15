package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorProdutoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoCorProduto;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoCorProduto;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoCorProduto;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoCor;
import br.com.fiap.espelhomeu.model.RecomendacaoCorProduto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocorproduto")
public class RecomendacaoCorProdutoController {

    private final RecomendacaoCorProdutoDao dao;
    private final ModelMapper mapper;

    public RecomendacaoCorProdutoController(RecomendacaoCorProdutoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoCorProduto> listar()  {

        return dao.listar().stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoCorProduto dto = mapper.map(
                            recomendacao, DetalhesRecomendacaoCorProduto.class
                    );

                    dto.setRecomendacaoCorId(recomendacao.getRecomendacaoCor().getId());
                    dto.setProdutoId(recomendacao.getProduto().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{idRecomendacaoCor}/{idProduto}")
    public ResponseEntity<DetalhesRecomendacaoCorProduto> buscar(
            @PathVariable long idRecomendacaoCor,
            @PathVariable long idProduto)  {

        RecomendacaoCorProduto recomendacao = dao.buscar(idRecomendacaoCor, idProduto);

        DetalhesRecomendacaoCorProduto dto = mapper.map(
                recomendacao, DetalhesRecomendacaoCorProduto.class
        );

        dto.setRecomendacaoCorId(recomendacao.getRecomendacaoCor().getId());
        dto.setProdutoId(recomendacao.getProduto().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoCorProduto> inserir(
            @RequestBody @Valid CadastroRecomendacaoCorProduto dto,
            UriComponentsBuilder uriBuilder)  {

        RecomendacaoCorProduto recomendacao = mapper.map(dto, RecomendacaoCorProduto.class);

        RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
        recomendacaoCor.setId(dto.getRecomendacaoCorId());
        recomendacao.setRecomendacaoCor(recomendacaoCor);

        Produto produto = new Produto();
        produto.setId(dto.getProdutoId());
        recomendacao.setProduto(produto);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoCorProduto detalhes = mapper.map(
                recomendacao, DetalhesRecomendacaoCorProduto.class
        );

        detalhes.setRecomendacaoCorId(dto.getRecomendacaoCorId());
        detalhes.setProdutoId(dto.getProdutoId());

        URI uri = uriBuilder
                .path("/recomendacaocorproduto/{idRecomendacaoCor}/{idProduto}")
                .buildAndExpand(dto.getRecomendacaoCorId(), dto.getProdutoId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{idRecomendacaoCor}/{idProduto}")
    public ResponseEntity<DetalhesRecomendacaoCorProduto> atualizar(
            @PathVariable long idRecomendacaoCor,
            @PathVariable long idProduto,
            @RequestBody @Valid AtualizacaoRecomendacaoCorProduto dto)  {

        RecomendacaoCorProduto recomendacao = dao.buscar(idRecomendacaoCor, idProduto);

        mapper.map(dto, recomendacao);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoCorProduto detalhes = mapper.map(
                recomendacao, DetalhesRecomendacaoCorProduto.class
        );

        detalhes.setRecomendacaoCorId(recomendacao.getRecomendacaoCor().getId());
        detalhes.setProdutoId(recomendacao.getProduto().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{idRecomendacaoCor}/{idProduto}")
    public ResponseEntity<Void> deletar(
            @PathVariable long idRecomendacaoCor,
            @PathVariable long idProduto)  {

        dao.deletar(idRecomendacaoCor, idProduto);

        return ResponseEntity.noContent().build();
    }
}