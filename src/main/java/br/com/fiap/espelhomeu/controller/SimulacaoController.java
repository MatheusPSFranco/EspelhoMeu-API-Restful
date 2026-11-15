package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.SimulacaoDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoSimulacao;
import br.com.fiap.espelhomeu.dto.CadastroSimulacao;
import br.com.fiap.espelhomeu.dto.DetalhesSimulacao;
import br.com.fiap.espelhomeu.model.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {

    private final SimulacaoDao dao;
    private final ModelMapper mapper;

    public SimulacaoController(SimulacaoDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesSimulacao> listar()  {
        return dao.listar().stream().map(simulacao -> {
            DetalhesSimulacao detalhes = mapper.map(simulacao, DetalhesSimulacao.class);

            detalhes.setUsuarioId(simulacao.getUsuario().getId());

            detalhes.setCorteId(
                    simulacao.getCorte() != null ? simulacao.getCorte().getId() : null
            );

            detalhes.setCorCabeloId(
                    simulacao.getCorCabelo() != null ? simulacao.getCorCabelo().getId() : null
            );

            detalhes.setPenteadoId(
                    simulacao.getPenteado() != null ? simulacao.getPenteado().getId() : null
            );

            return detalhes;
        }).toList();
    }

    @GetMapping("/{id}")
    public DetalhesSimulacao buscar(@PathVariable long id)  {
        Simulacao simulacao = dao.buscar(id);

        DetalhesSimulacao detalhes = mapper.map(simulacao, DetalhesSimulacao.class);

        detalhes.setUsuarioId(simulacao.getUsuario().getId());

        detalhes.setCorteId(
                simulacao.getCorte() != null ? simulacao.getCorte().getId() : null
        );

        detalhes.setCorCabeloId(
                simulacao.getCorCabelo() != null ? simulacao.getCorCabelo().getId() : null
        );

        detalhes.setPenteadoId(
                simulacao.getPenteado() != null ? simulacao.getPenteado().getId() : null
        );

        return detalhes;
    }

    @PostMapping
    public ResponseEntity<DetalhesSimulacao> inserir(
            @RequestBody @Valid CadastroSimulacao dto,
            UriComponentsBuilder uriBuilder)  {

        Simulacao simulacao = mapper.map(dto, Simulacao.class);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        simulacao.setUsuario(usuario);

        if (dto.getCorteId() != null) {
            Corte corte = new Corte();
            corte.setId(dto.getCorteId());
            simulacao.setCorte(corte);
        }

        if (dto.getCorCabeloId() != null) {
            CorCabelo corCabelo = new CorCabelo();
            corCabelo.setId(dto.getCorCabeloId());
            simulacao.setCorCabelo(corCabelo);
        }

        if (dto.getPenteadoId() != null) {
            Penteado penteado = new Penteado();
            penteado.setId(dto.getPenteadoId());
            simulacao.setPenteado(penteado);
        }

        dao.inserir(simulacao);

        DetalhesSimulacao detalhes = mapper.map(simulacao, DetalhesSimulacao.class);

        detalhes.setUsuarioId(simulacao.getUsuario().getId());

        detalhes.setCorteId(
                simulacao.getCorte() != null ? simulacao.getCorte().getId() : null
        );

        detalhes.setCorCabeloId(
                simulacao.getCorCabelo() != null ? simulacao.getCorCabelo().getId() : null
        );

        detalhes.setPenteadoId(
                simulacao.getPenteado() != null ? simulacao.getPenteado().getId() : null
        );

        URI uri = uriBuilder.path("/simulacao/{id}")
                .buildAndExpand(simulacao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesSimulacao> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoSimulacao dto)  {

        Simulacao simulacao = dao.buscar(id);

        mapper.map(dto, simulacao);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        simulacao.setUsuario(usuario);

        simulacao.setCorte(null);
        if (dto.getCorteId() != null) {
            Corte corte = new Corte();
            corte.setId(dto.getCorteId());
            simulacao.setCorte(corte);
        }

        simulacao.setCorCabelo(null);
        if (dto.getCorCabeloId() != null) {
            CorCabelo corCabelo = new CorCabelo();
            corCabelo.setId(dto.getCorCabeloId());
            simulacao.setCorCabelo(corCabelo);
        }

        simulacao.setPenteado(null);
        if (dto.getPenteadoId() != null) {
            Penteado penteado = new Penteado();
            penteado.setId(dto.getPenteadoId());
            simulacao.setPenteado(penteado);
        }

        dao.atualizar(simulacao);

        DetalhesSimulacao detalhes = mapper.map(simulacao, DetalhesSimulacao.class);

        detalhes.setUsuarioId(simulacao.getUsuario().getId());

        detalhes.setCorteId(
                simulacao.getCorte() != null ? simulacao.getCorte().getId() : null
        );

        detalhes.setCorCabeloId(
                simulacao.getCorCabelo() != null ? simulacao.getCorCabelo().getId() : null
        );

        detalhes.setPenteadoId(
                simulacao.getPenteado() != null ? simulacao.getPenteado().getId() : null
        );

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}