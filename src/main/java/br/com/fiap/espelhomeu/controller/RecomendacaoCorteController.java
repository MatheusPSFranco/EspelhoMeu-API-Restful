package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorteDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoCorte;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoCorte;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoCorte;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.Corte;
import br.com.fiap.espelhomeu.model.RecomendacaoCorte;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocorte")
public class RecomendacaoCorteController {

    private final RecomendacaoCorteDao dao;
    private final ModelMapper mapper;

    public RecomendacaoCorteController(RecomendacaoCorteDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoCorte> listar()  {
        return dao.listar()
                .stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoCorte detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCorte.class);

                    detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
                    detalhes.setCorteId(recomendacao.getCorte().getId());

                    return detalhes;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoCorte> buscar(@PathVariable long id)  {
        RecomendacaoCorte recomendacao = dao.buscar(id);

        DetalhesRecomendacaoCorte detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCorte.class);

        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setCorteId(recomendacao.getCorte().getId());

        return ResponseEntity.ok(detalhes);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoCorte> inserir(
            @RequestBody @Valid CadastroRecomendacaoCorte dto)  {

        RecomendacaoCorte recomendacao = mapper.map(dto, RecomendacaoCorte.class);

        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
        consultoria.setId(dto.getConsultoriaCapilarId());
        recomendacao.setConsultoriaCapilar(consultoria);

        Corte corte = new Corte();
        corte.setId(dto.getCorteId());
        recomendacao.setCorte(corte);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoCorte detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCorte.class);

        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setCorteId(recomendacao.getCorte().getId());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoCorte> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoRecomendacaoCorte dto)  {

        RecomendacaoCorte recomendacao = dao.buscar(id);

        mapper.map(dto, recomendacao);

        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
        consultoria.setId(dto.getConsultoriaCapilarId());
        recomendacao.setConsultoriaCapilar(consultoria);

        Corte corte = new Corte();
        corte.setId(dto.getCorteId());
        recomendacao.setCorte(corte);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoCorte detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCorte.class);

        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setCorteId(recomendacao.getCorte().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}