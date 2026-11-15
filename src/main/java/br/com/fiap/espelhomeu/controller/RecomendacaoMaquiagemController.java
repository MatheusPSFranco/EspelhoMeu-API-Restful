package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.RecomendacaoMaquiagemDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoRecomendacaoMaquiagem;
import br.com.fiap.espelhomeu.dto.CadastroRecomendacaoMaquiagem;
import br.com.fiap.espelhomeu.dto.DetalhesRecomendacaoMaquiagem;
import br.com.fiap.espelhomeu.model.ConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.model.Maquiagem;
import br.com.fiap.espelhomeu.model.RecomendacaoMaquiagem;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacaomaquiagem")
public class RecomendacaoMaquiagemController {

    private final RecomendacaoMaquiagemDao dao;
    private final ModelMapper mapper;

    public RecomendacaoMaquiagemController(RecomendacaoMaquiagemDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesRecomendacaoMaquiagem> listar()  {

        return dao.listar().stream()
                .map(recomendacao -> {
                    DetalhesRecomendacaoMaquiagem dto = mapper.map(
                            recomendacao, DetalhesRecomendacaoMaquiagem.class
                    );

                    dto.setConsultoriaMaquiagemId(recomendacao.getConsultoriaMaquiagem().getId());
                    dto.setMaquiagemId(recomendacao.getMaquiagem().getId());

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoMaquiagem> buscar(
            @PathVariable long id)  {

        RecomendacaoMaquiagem recomendacao = dao.buscar(id);

        DetalhesRecomendacaoMaquiagem dto = mapper.map(
                recomendacao, DetalhesRecomendacaoMaquiagem.class
        );

        dto.setConsultoriaMaquiagemId(recomendacao.getConsultoriaMaquiagem().getId());
        dto.setMaquiagemId(recomendacao.getMaquiagem().getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesRecomendacaoMaquiagem> inserir(
            @RequestBody @Valid CadastroRecomendacaoMaquiagem dto,
            UriComponentsBuilder uriBuilder)  {

        RecomendacaoMaquiagem recomendacao = mapper.map(
                dto, RecomendacaoMaquiagem.class
        );

        ConsultoriaMaquiagem consultoriaMaquiagem = new ConsultoriaMaquiagem();
        consultoriaMaquiagem.setId(dto.getConsultoriaMaquiagemId());
        recomendacao.setConsultoriaMaquiagem(consultoriaMaquiagem);

        Maquiagem maquiagem = new Maquiagem();
        maquiagem.setId(dto.getMaquiagemId());
        recomendacao.setMaquiagem(maquiagem);

        dao.inserir(recomendacao);

        DetalhesRecomendacaoMaquiagem detalhes = mapper.map(
                recomendacao, DetalhesRecomendacaoMaquiagem.class
        );

        detalhes.setConsultoriaMaquiagemId(dto.getConsultoriaMaquiagemId());
        detalhes.setMaquiagemId(dto.getMaquiagemId());

        URI uri = uriBuilder
                .path("/recomendacaomaquiagem/{id}")
                .buildAndExpand(recomendacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesRecomendacaoMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoRecomendacaoMaquiagem dto)  {

        RecomendacaoMaquiagem recomendacao = dao.buscar(id);

        mapper.map(dto, recomendacao);

        ConsultoriaMaquiagem consultoriaMaquiagem = new ConsultoriaMaquiagem();
        consultoriaMaquiagem.setId(dto.getConsultoriaMaquiagemId());
        recomendacao.setConsultoriaMaquiagem(consultoriaMaquiagem);

        Maquiagem maquiagem = new Maquiagem();
        maquiagem.setId(dto.getMaquiagemId());
        recomendacao.setMaquiagem(maquiagem);

        dao.atualizar(recomendacao);

        DetalhesRecomendacaoMaquiagem detalhes = mapper.map(
                recomendacao, DetalhesRecomendacaoMaquiagem.class
        );

        detalhes.setConsultoriaMaquiagemId(dto.getConsultoriaMaquiagemId());
        detalhes.setMaquiagemId(dto.getMaquiagemId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {

        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}