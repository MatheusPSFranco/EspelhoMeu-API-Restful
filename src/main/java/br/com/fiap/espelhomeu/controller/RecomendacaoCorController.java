package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoCorDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoCor;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoCor;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoCor;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.CorCabelo;
import br.com.fiap.espelhomeu.model.RecomendacaoCor;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaocor")
public class RecomendacaoCorController {

    private final RecomendacaoCorDao dao;
    private final ModelMapper mapper;

    public RecomendacaoCorController(RecomendacaoCorDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoCor> listar()  {
        return dao.listar()
                .stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoCor detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCor.class);

                    detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
                    detalhes.setCorCabeloId(recomendacao.getCorCabelo().getId());

                    return detalhes;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoCor> buscar(@PathVariable long id)  {
        RecomendacaoCor recomendacao = dao.buscar(id);

        DetalhesRecomendacaoCor detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCor.class);

        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setCorCabeloId(recomendacao.getCorCabelo().getId());

        return ResponseEntity.ok(detalhes);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoCor> inserir(
            @RequestBody @Valid CadastroRecomendacaoCor dto)  {

        RecomendacaoCor recomendacao = mapper.map(dto, RecomendacaoCor.class);

        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
        consultoria.setId(dto.getConsultoriaCapilarId());
        recomendacao.setConsultoriaCapilar(consultoria);

        CorCabelo cor = new CorCabelo();
        cor.setId(dto.getCorCabeloId());
        recomendacao.setCorCabelo(cor);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoCor detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCor.class);

        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setCorCabeloId(recomendacao.getCorCabelo().getId());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoCor> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoRecomendacaoCor dto)  {

        RecomendacaoCor recomendacao = dao.buscar(id);

        mapper.map(dto, recomendacao);

        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
        consultoria.setId(dto.getConsultoriaCapilarId());
        recomendacao.setConsultoriaCapilar(consultoria);

        CorCabelo cor = new CorCabelo();
        cor.setId(dto.getCorCabeloId());
        recomendacao.setCorCabelo(cor);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoCor detalhes = mapper.map(recomendacao, DetalhesRecomendacaoCor.class);

        detalhes.setConsultoriaCapilarId(recomendacao.getConsultoriaCapilar().getId());
        detalhes.setCorCabeloId(recomendacao.getCorCabelo().getId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}