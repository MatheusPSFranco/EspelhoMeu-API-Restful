package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorteProdutoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoCorteProduto;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoCorteProduto;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoCorteProduto;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoCorte;
import br.com.fiap.espelhomeu.model.RecomendacaoCorteProduto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocorteproduto")
public class RecomendacaoCorteProdutoController {

    private final RecomendacaoCorteProdutoDao dao;
    private final ModelMapper mapper;

    public RecomendacaoCorteProdutoController(RecomendacaoCorteProdutoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoCorteProduto> listar()  {
        return dao.listar().stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoCorteProduto dto = mapper.map(recomendacao, DetalhesRecomendacaoCorteProduto.class);
                    dto.setProdutoId(recomendacao.getProduto().getId());
                    dto.setRecomendacaoCorteId(recomendacao.getRecomendacaoCorte().getId());
                    return dto;
                })
                .toList();
    }

    @GetMapping("/{idProduto}/{idRecomendacaoCorte}")
    public ResponseEntity<DetalhesRecomendacaoCorteProduto> buscar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoCorte)  {

        RecomendacaoCorteProduto recomendacao = dao.buscar(idProduto, idRecomendacaoCorte);

        DetalhesRecomendacaoCorteProduto dto = mapper.map(recomendacao, DetalhesRecomendacaoCorteProduto.class);
        dto.setProdutoId(recomendacao.getProduto().getId());
        dto.setRecomendacaoCorteId(recomendacao.getRecomendacaoCorte().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoCorteProduto> inserir(
            @RequestBody @Valid CadastroRecomendacaoCorteProduto dto,
            UriComponentsBuilder uriBuilder)  {

        RecomendacaoCorteProduto recomendacao = mapper.map(dto, RecomendacaoCorteProduto.class);

        Produto produto = new Produto();
        produto.setId(dto.getProdutoId());
        recomendacao.setProduto(produto);

        RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
        recomendacaoCorte.setId(dto.getRecomendacaoCorteId());
        recomendacao.setRecomendacaoCorte(recomendacaoCorte);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoCorteProduto detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCorteProduto.class);
        detalhes.setProdutoId(recomendacao.getProduto().getId());
        detalhes.setRecomendacaoCorteId(recomendacao.getRecomendacaoCorte().getId());

        URI uri = uriBuilder
                .path("/recomendacaocorteproduto/{idProduto}/{idRecomendacaoCorte}")
                .buildAndExpand(
                        recomendacao.getProduto().getId(),
                        recomendacao.getRecomendacaoCorte().getId()
                )
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{idProduto}/{idRecomendacaoCorte}")
    public ResponseEntity<DetalhesRecomendacaoCorteProduto> atualizar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoCorte,
            @RequestBody @Valid AtualizacaoRecomendacaoCorteProduto dto)  {

        RecomendacaoCorteProduto recomendacao = dao.buscar(idProduto, idRecomendacaoCorte);

        mapper.map(dto, recomendacao);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoCorteProduto detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCorteProduto.class);
        detalhes.setProdutoId(recomendacao.getProduto().getId());
        detalhes.setRecomendacaoCorteId(recomendacao.getRecomendacaoCorte().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{idProduto}/{idRecomendacaoCorte}")
    public ResponseEntity<Void> deletar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoCorte)  {

        dao.deletar(idProduto, idRecomendacaoCorte);
        return ResponseEntity.noContent().build();
    }
}