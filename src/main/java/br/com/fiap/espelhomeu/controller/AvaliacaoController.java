package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.AvaliacaoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoAvaliacao;
import br.com.fiap.espelhomeu.dto.CadastroAvaliacao;
import br.com.fiap.espelhomeu.dto.DetalhesAvaliacao;
import br.com.fiap.espelhomeu.model.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoDao dao;
    private final ModelMapper mapper;

    public AvaliacaoController(AvaliacaoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesAvaliacao> listar() {

        return dao.listar().stream()
                .map(avaliacao -> {
                    DetalhesAvaliacao dto = mapper.map(avaliacao, DetalhesAvaliacao.class);

                    dto.setUsuarioId(avaliacao.getUsuario().getId());

                    dto.setRecomendacaoCorId(
                            avaliacao.getRecomendacaoCor() != null
                                    ? avaliacao.getRecomendacaoCor().getId() : null
                    );

                    dto.setRecomendacaoCorteId(
                            avaliacao.getRecomendacaoCorte() != null
                                    ? avaliacao.getRecomendacaoCorte().getId() : null
                    );

                    dto.setRecomendacaoPenteadoId(
                            avaliacao.getRecomendacaoPenteado() != null
                                    ? avaliacao.getRecomendacaoPenteado().getId() : null
                    );

                    dto.setRecomendacaoMaquiagemId(
                            avaliacao.getRecomendacaoMaquiagem() != null
                                    ? avaliacao.getRecomendacaoMaquiagem().getId() : null
                    );

                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesAvaliacao> buscar(@PathVariable long id) {

        Avaliacao avaliacao = dao.buscar(id);

        DetalhesAvaliacao dto = mapper.map(avaliacao, DetalhesAvaliacao.class);

        dto.setUsuarioId(avaliacao.getUsuario().getId());

        dto.setRecomendacaoCorId(
                avaliacao.getRecomendacaoCor() != null
                        ? avaliacao.getRecomendacaoCor().getId() : null
        );

        dto.setRecomendacaoCorteId(
                avaliacao.getRecomendacaoCorte() != null
                        ? avaliacao.getRecomendacaoCorte().getId() : null
        );

        dto.setRecomendacaoPenteadoId(
                avaliacao.getRecomendacaoPenteado() != null
                        ? avaliacao.getRecomendacaoPenteado().getId() : null
        );

        dto.setRecomendacaoMaquiagemId(
                avaliacao.getRecomendacaoMaquiagem() != null
                        ? avaliacao.getRecomendacaoMaquiagem().getId() : null
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DetalhesAvaliacao> inserir(
            @RequestBody @Valid CadastroAvaliacao dto,
            UriComponentsBuilder uriBuilder)  {

        Avaliacao avaliacao = mapper.map(dto, Avaliacao.class);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        avaliacao.setUsuario(usuario);

        if (dto.getRecomendacaoCorId() != null) {
            RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
            recomendacaoCor.setId(dto.getRecomendacaoCorId());
            avaliacao.setRecomendacaoCor(recomendacaoCor);
        }

        if (dto.getRecomendacaoCorteId() != null) {
            RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
            recomendacaoCorte.setId(dto.getRecomendacaoCorteId());
            avaliacao.setRecomendacaoCorte(recomendacaoCorte);
        }

        if (dto.getRecomendacaoPenteadoId() != null) {
            RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
            recomendacaoPenteado.setId(dto.getRecomendacaoPenteadoId());
            avaliacao.setRecomendacaoPenteado(recomendacaoPenteado);
        }

        if (dto.getRecomendacaoMaquiagemId() != null) {
            RecomendacaoMaquiagem recomendacaoMaquiagem = new RecomendacaoMaquiagem();
            recomendacaoMaquiagem.setId(dto.getRecomendacaoMaquiagemId());
            avaliacao.setRecomendacaoMaquiagem(recomendacaoMaquiagem);
        }

        dao.inserir(avaliacao);

        DetalhesAvaliacao detalhes = mapper.map(avaliacao, DetalhesAvaliacao.class);

        detalhes.setUsuarioId(dto.getUsuarioId());
        detalhes.setRecomendacaoCorId(dto.getRecomendacaoCorId());
        detalhes.setRecomendacaoCorteId(dto.getRecomendacaoCorteId());
        detalhes.setRecomendacaoPenteadoId(dto.getRecomendacaoPenteadoId());
        detalhes.setRecomendacaoMaquiagemId(dto.getRecomendacaoMaquiagemId());

        URI uri = uriBuilder
                .path("/avaliacao/{id}")
                .buildAndExpand(avaliacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesAvaliacao> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoAvaliacao dto)  {

        Avaliacao avaliacao = dao.buscar(id);

        mapper.map(dto, avaliacao);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        avaliacao.setUsuario(usuario);

        avaliacao.setRecomendacaoCor(null);
        avaliacao.setRecomendacaoCorte(null);
        avaliacao.setRecomendacaoPenteado(null);
        avaliacao.setRecomendacaoMaquiagem(null);

        if (dto.getRecomendacaoCorId() != null) {
            RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
            recomendacaoCor.setId(dto.getRecomendacaoCorId());
            avaliacao.setRecomendacaoCor(recomendacaoCor);
        }

        if (dto.getRecomendacaoCorteId() != null) {
            RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
            recomendacaoCorte.setId(dto.getRecomendacaoCorteId());
            avaliacao.setRecomendacaoCorte(recomendacaoCorte);
        }

        if (dto.getRecomendacaoPenteadoId() != null) {
            RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
            recomendacaoPenteado.setId(dto.getRecomendacaoPenteadoId());
            avaliacao.setRecomendacaoPenteado(recomendacaoPenteado);
        }

        if (dto.getRecomendacaoMaquiagemId() != null) {
            RecomendacaoMaquiagem recomendacaoMaquiagem = new RecomendacaoMaquiagem();
            recomendacaoMaquiagem.setId(dto.getRecomendacaoMaquiagemId());
            avaliacao.setRecomendacaoMaquiagem(recomendacaoMaquiagem);
        }

        dao.atualizar(avaliacao);

        DetalhesAvaliacao detalhes = mapper.map(avaliacao, DetalhesAvaliacao.class);

        detalhes.setUsuarioId(dto.getUsuarioId());
        detalhes.setRecomendacaoCorId(dto.getRecomendacaoCorId());
        detalhes.setRecomendacaoCorteId(dto.getRecomendacaoCorteId());
        detalhes.setRecomendacaoPenteadoId(dto.getRecomendacaoPenteadoId());
        detalhes.setRecomendacaoMaquiagemId(dto.getRecomendacaoMaquiagemId());

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {

        dao.deletar(id);

        return ResponseEntity.noContent().build();
    }
}