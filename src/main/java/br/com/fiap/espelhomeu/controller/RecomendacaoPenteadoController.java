package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoPenteadoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoPenteado;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoPenteado;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoPenteado;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.Penteado;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteado;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaopenteado")
public class RecomendacaoPenteadoController {

    private final RecomendacaoPenteadoDao dao;
    private final ModelMapper mapper;

    public RecomendacaoPenteadoController(RecomendacaoPenteadoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoPenteado> listar()  {
        return dao.listar().stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoPenteado dto = mapper.map(recomendacao, DetalhesRecomendacaoPenteado.class);
                    dto.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
                    dto.setPenteadoId(recomendacao.getPenteado().getId());
                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoPenteado> buscar(@PathVariable long id)  {
        RecomendacaoPenteado recomendacao = dao.buscar(id);

        DetalhesRecomendacaoPenteado dto = mapper.map(recomendacao, DetalhesRecomendacaoPenteado.class);
        dto.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        dto.setPenteadoId(recomendacao.getPenteado().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoPenteado> inserir(
            @RequestBody @Valid CadastroRecomendacaoPenteado dto,
            UriComponentsBuilder uriBuilder)  {

        RecomendacaoPenteado recomendacao = mapper.map(dto, RecomendacaoPenteado.class);

        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
        consultoria.setId(dto.getConsultoriaCapilarId());
        recomendacao.setConsultoriaCapilar(consultoria);

        Penteado penteado = new Penteado();
        penteado.setId(dto.getPenteadoId());
        recomendacao.setPenteado(penteado);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoPenteado detalhes = mapper.map(recomendacao, DetalhesRecomendacaoPenteado.class);
        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setPenteadoId(recomendacao.getPenteado().getId());

        URI uri = uriBuilder
                .path("/recomendacaopenteado/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoPenteado> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoRecomendacaoPenteado dto)  {

        RecomendacaoPenteado recomendacao = dao.buscar(id);

        mapper.map(dto, recomendacao);

        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
        consultoria.setId(dto.getConsultoriaCapilarId());
        recomendacao.setConsultoriaCapilar(consultoria);

        Penteado penteado = new Penteado();
        penteado.setId(dto.getPenteadoId());
        recomendacao.setPenteado(penteado);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoPenteado detalhes = mapper.map(recomendacao, DetalhesRecomendacaoPenteado.class);
        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setPenteadoId(recomendacao.getPenteado().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}