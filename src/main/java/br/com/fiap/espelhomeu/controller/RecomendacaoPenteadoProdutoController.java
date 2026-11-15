package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoPenteadoProdutoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoPenteadoProduto;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoPenteadoProduto;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoPenteadoProduto;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteado;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteadoProduto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaopenteadoproduto")
public class RecomendacaoPenteadoProdutoController {

    private final RecomendacaoPenteadoProdutoDao dao;
    private final ModelMapper mapper;

    public RecomendacaoPenteadoProdutoController(RecomendacaoPenteadoProdutoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoPenteadoProduto> listar()  {

        return dao.listar().stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoPenteadoProduto dto = mapper.map(
                            recomendacao, DetalhesRecomendacaoPenteadoProduto.class
                    );

                    dto.setProdutoId(recomendacao.getProduto().getId());
                    dto.setRecomendacaoPenteadoId(recomendacao.getRecomendacaoPenteado().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{idProduto}/{idRecomendacaoPenteado}")
    public ResponseEntity<DetalhesRecomendacaoPenteadoProduto> buscar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoPenteado)  {

        RecomendacaoPenteadoProduto recomendacao = dao.buscar(idProduto, idRecomendacaoPenteado);

        DetalhesRecomendacaoPenteadoProduto dto = mapper.map(
                recomendacao, DetalhesRecomendacaoPenteadoProduto.class
        );

        dto.setProdutoId(recomendacao.getProduto().getId());
        dto.setRecomendacaoPenteadoId(recomendacao.getRecomendacaoPenteado().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoPenteadoProduto> inserir(
            @RequestBody @Valid CadastroRecomendacaoPenteadoProduto dto,
            UriComponentsBuilder uriBuilder)  {

        RecomendacaoPenteadoProduto recomendacao = mapper.map(
                dto, RecomendacaoPenteadoProduto.class
        );

        Produto produto = new Produto();
        produto.setId(dto.getProdutoId());
        recomendacao.setProduto(produto);

        RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
        recomendacaoPenteado.setId(dto.getRecomendacaoPenteadoId());
        recomendacao.setRecomendacaoPenteado(recomendacaoPenteado);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoPenteadoProduto detalhes = mapper.map(
                recomendacao, DetalhesRecomendacaoPenteadoProduto.class
        );

        detalhes.setProdutoId(dto.getProdutoId());
        detalhes.setRecomendacaoPenteadoId(dto.getRecomendacaoPenteadoId());

        URI uri = uriBuilder
                .path("/recomendacaopenteadoproduto/{idProduto}/{idRecomendacaoPenteado}")
                .buildAndExpand(dto.getProdutoId(), dto.getRecomendacaoPenteadoId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{idProduto}/{idRecomendacaoPenteado}")
    public ResponseEntity<DetalhesRecomendacaoPenteadoProduto> atualizar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoPenteado,
            @RequestBody @Valid AtualizacaoRecomendacaoPenteadoProduto dto)  {

        RecomendacaoPenteadoProduto recomendacao = dao.buscar(idProduto, idRecomendacaoPenteado);

        mapper.map(dto, recomendacao);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoPenteadoProduto detalhes = mapper.map(
                recomendacao, DetalhesRecomendacaoPenteadoProduto.class
        );

        detalhes.setProdutoId(recomendacao.getProduto().getId());
        detalhes.setRecomendacaoPenteadoId(recomendacao.getRecomendacaoPenteado().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{idProduto}/{idRecomendacaoPenteado}")
    public ResponseEntity<Void> deletar(
            @PathVariable long idProduto,
            @PathVariable long idRecomendacaoPenteado)  {

        dao.deletar(idProduto, idRecomendacaoPenteado);

        return ResponseEntity.noContent().build();
    }
}