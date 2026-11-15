package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.FavoritosDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoFavoritos;
import br.com.fiap.espelhomeu.dto.CadastroFavoritos;
import br.com.fiap.espelhomeu.dto.DetalhesFavoritos;
import br.com.fiap.espelhomeu.model.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritosDao dao;
    private final ModelMapper mapper;

    public FavoritosController(FavoritosDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesFavoritos> listar()  {
        return dao.listar().stream().map(favoritos -> {
            DetalhesFavoritos detalhes = mapper.map(favoritos, DetalhesFavoritos.class);

            detalhes.setUsuarioId(favoritos.getUsuario().getId());

            detalhes.setCorteId(favoritos.getCorte() != null ? favoritos.getCorte().getId() : null);
            detalhes.setMaquiagemId(favoritos.getMaquiagem() != null ? favoritos.getMaquiagem().getId() : null);
            detalhes.setCorCabeloId(favoritos.getCorCabelo() != null ? favoritos.getCorCabelo().getId() : null);
            detalhes.setPenteadoId(favoritos.getPenteado() != null ? favoritos.getPenteado().getId() : null);

            detalhes.setRecomendacaoCorteId(favoritos.getRecomendacaoCorte() != null ? favoritos.getRecomendacaoCorte().getId() : null);
            detalhes.setRecomendacaoCorId(favoritos.getRecomendacaoCor() != null ? favoritos.getRecomendacaoCor().getId() : null);
            detalhes.setRecomendacaoPenteadoId(favoritos.getRecomendacaoPenteado() != null ? favoritos.getRecomendacaoPenteado().getId() : null);
            detalhes.setRecomendacaoMaquiagemId(favoritos.getRecomendacaoMaquiagem() != null ? favoritos.getRecomendacaoMaquiagem().getId() : null);

            detalhes.setProdutoId(favoritos.getProduto() != null ? favoritos.getProduto().getId() : null);
            detalhes.setSimulacaoId(favoritos.getSimulacao() != null ? favoritos.getSimulacao().getId() : null);

            return detalhes;
        }).toList();
    }

    @GetMapping("/{id}")
    public DetalhesFavoritos buscar(@PathVariable long id)  {
        Favoritos favoritos = dao.buscar(id);

        DetalhesFavoritos detalhes = mapper.map(favoritos, DetalhesFavoritos.class);

        detalhes.setUsuarioId(favoritos.getUsuario().getId());

        detalhes.setCorteId(favoritos.getCorte() != null ? favoritos.getCorte().getId() : null);
        detalhes.setMaquiagemId(favoritos.getMaquiagem() != null ? favoritos.getMaquiagem().getId() : null);
        detalhes.setCorCabeloId(favoritos.getCorCabelo() != null ? favoritos.getCorCabelo().getId() : null);
        detalhes.setPenteadoId(favoritos.getPenteado() != null ? favoritos.getPenteado().getId() : null);

        detalhes.setRecomendacaoCorteId(favoritos.getRecomendacaoCorte() != null ? favoritos.getRecomendacaoCorte().getId() : null);
        detalhes.setRecomendacaoCorId(favoritos.getRecomendacaoCor() != null ? favoritos.getRecomendacaoCor().getId() : null);
        detalhes.setRecomendacaoPenteadoId(favoritos.getRecomendacaoPenteado() != null ? favoritos.getRecomendacaoPenteado().getId() : null);
        detalhes.setRecomendacaoMaquiagemId(favoritos.getRecomendacaoMaquiagem() != null ? favoritos.getRecomendacaoMaquiagem().getId() : null);

        detalhes.setProdutoId(favoritos.getProduto() != null ? favoritos.getProduto().getId() : null);
        detalhes.setSimulacaoId(favoritos.getSimulacao() != null ? favoritos.getSimulacao().getId() : null);

        return detalhes;
    }

    @PostMapping
    public ResponseEntity<DetalhesFavoritos> inserir(
            @RequestBody @Valid CadastroFavoritos dto,
            UriComponentsBuilder uriBuilder)  {

        Favoritos favoritos = mapper.map(dto, Favoritos.class);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        favoritos.setUsuario(usuario);

        if (dto.getCorteId() != null) {
            Corte corte = new Corte();
            corte.setId(dto.getCorteId());
            favoritos.setCorte(corte);
        }

        if (dto.getMaquiagemId() != null) {
            Maquiagem maquiagem = new Maquiagem();
            maquiagem.setId(dto.getMaquiagemId());
            favoritos.setMaquiagem(maquiagem);
        }

        if (dto.getCorCabeloId() != null) {
            CorCabelo corCabelo = new CorCabelo();
            corCabelo.setId(dto.getCorCabeloId());
            favoritos.setCorCabelo(corCabelo);
        }

        if (dto.getPenteadoId() != null) {
            Penteado penteado = new Penteado();
            penteado.setId(dto.getPenteadoId());
            favoritos.setPenteado(penteado);
        }

        if (dto.getRecomendacaoCorteId() != null) {
            RecomendacaoCorte recomendacao = new RecomendacaoCorte();
            recomendacao.setId(dto.getRecomendacaoCorteId());
            favoritos.setRecomendacaoCorte(recomendacao);
        }

        if (dto.getRecomendacaoCorId() != null) {
            RecomendacaoCor recomendacao = new RecomendacaoCor();
            recomendacao.setId(dto.getRecomendacaoCorId());
            favoritos.setRecomendacaoCor(recomendacao);
        }

        if (dto.getRecomendacaoPenteadoId() != null) {
            RecomendacaoPenteado recomendacao = new RecomendacaoPenteado();
            recomendacao.setId(dto.getRecomendacaoPenteadoId());
            favoritos.setRecomendacaoPenteado(recomendacao);
        }

        if (dto.getProdutoId() != null) {
            Produto produto = new Produto();
            produto.setId(dto.getProdutoId());
            favoritos.setProduto(produto);
        }

        if (dto.getRecomendacaoMaquiagemId() != null) {
            RecomendacaoMaquiagem recomendacao = new RecomendacaoMaquiagem();
            recomendacao.setId(dto.getRecomendacaoMaquiagemId());
            favoritos.setRecomendacaoMaquiagem(recomendacao);
        }

        if (dto.getSimulacaoId() != null) {
            Simulacao simulacao = new Simulacao();
            simulacao.setId(dto.getSimulacaoId());
            favoritos.setSimulacao(simulacao);
        }

        dao.inserir(favoritos);

        DetalhesFavoritos detalhes = mapper.map(favoritos, DetalhesFavoritos.class);

        detalhes.setUsuarioId(favoritos.getUsuario().getId());

        detalhes.setCorteId(favoritos.getCorte() != null ? favoritos.getCorte().getId() : null);
        detalhes.setMaquiagemId(favoritos.getMaquiagem() != null ? favoritos.getMaquiagem().getId() : null);
        detalhes.setCorCabeloId(favoritos.getCorCabelo() != null ? favoritos.getCorCabelo().getId() : null);
        detalhes.setPenteadoId(favoritos.getPenteado() != null ? favoritos.getPenteado().getId() : null);

        detalhes.setRecomendacaoCorteId(favoritos.getRecomendacaoCorte() != null ? favoritos.getRecomendacaoCorte().getId() : null);
        detalhes.setRecomendacaoCorId(favoritos.getRecomendacaoCor() != null ? favoritos.getRecomendacaoCor().getId() : null);
        detalhes.setRecomendacaoPenteadoId(favoritos.getRecomendacaoPenteado() != null ? favoritos.getRecomendacaoPenteado().getId() : null);
        detalhes.setRecomendacaoMaquiagemId(favoritos.getRecomendacaoMaquiagem() != null ? favoritos.getRecomendacaoMaquiagem().getId() : null);

        detalhes.setProdutoId(favoritos.getProduto() != null ? favoritos.getProduto().getId() : null);
        detalhes.setSimulacaoId(favoritos.getSimulacao() != null ? favoritos.getSimulacao().getId() : null);

        URI uri = uriBuilder.path("/favoritos/{id}")
                .buildAndExpand(favoritos.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesFavoritos> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoFavoritos dto)  {

        Favoritos favoritos = dao.buscar(id);

        mapper.map(dto, favoritos);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        favoritos.setUsuario(usuario);

        favoritos.setCorte(null);
        favoritos.setMaquiagem(null);
        favoritos.setCorCabelo(null);
        favoritos.setPenteado(null);
        favoritos.setRecomendacaoCorte(null);
        favoritos.setRecomendacaoCor(null);
        favoritos.setRecomendacaoPenteado(null);
        favoritos.setProduto(null);
        favoritos.setRecomendacaoMaquiagem(null);
        favoritos.setSimulacao(null);

        if (dto.getCorteId() != null) {
            Corte corte = new Corte();
            corte.setId(dto.getCorteId());
            favoritos.setCorte(corte);
        }

        if (dto.getMaquiagemId() != null) {
            Maquiagem maquiagem = new Maquiagem();
            maquiagem.setId(dto.getMaquiagemId());
            favoritos.setMaquiagem(maquiagem);
        }

        if (dto.getCorCabeloId() != null) {
            CorCabelo corCabelo = new CorCabelo();
            corCabelo.setId(dto.getCorCabeloId());
            favoritos.setCorCabelo(corCabelo);
        }

        if (dto.getPenteadoId() != null) {
            Penteado penteado = new Penteado();
            penteado.setId(dto.getPenteadoId());
            favoritos.setPenteado(penteado);
        }

        if (dto.getRecomendacaoCorteId() != null) {
            RecomendacaoCorte recomendacao = new RecomendacaoCorte();
            recomendacao.setId(dto.getRecomendacaoCorteId());
            favoritos.setRecomendacaoCorte(recomendacao);
        }

        if (dto.getRecomendacaoCorId() != null) {
            RecomendacaoCor recomendacao = new RecomendacaoCor();
            recomendacao.setId(dto.getRecomendacaoCorId());
            favoritos.setRecomendacaoCor(recomendacao);
        }

        if (dto.getRecomendacaoPenteadoId() != null) {
            RecomendacaoPenteado recomendacao = new RecomendacaoPenteado();
            recomendacao.setId(dto.getRecomendacaoPenteadoId());
            favoritos.setRecomendacaoPenteado(recomendacao);
        }

        if (dto.getProdutoId() != null) {
            Produto produto = new Produto();
            produto.setId(dto.getProdutoId());
            favoritos.setProduto(produto);
        }

        if (dto.getRecomendacaoMaquiagemId() != null) {
            RecomendacaoMaquiagem recomendacao = new RecomendacaoMaquiagem();
            recomendacao.setId(dto.getRecomendacaoMaquiagemId());
            favoritos.setRecomendacaoMaquiagem(recomendacao);
        }

        if (dto.getSimulacaoId() != null) {
            Simulacao simulacao = new Simulacao();
            simulacao.setId(dto.getSimulacaoId());
            favoritos.setSimulacao(simulacao);
        }

        dao.atualizar(favoritos);

        DetalhesFavoritos detalhes = mapper.map(favoritos, DetalhesFavoritos.class);

        detalhes.setUsuarioId(favoritos.getUsuario().getId());

        detalhes.setCorteId(favoritos.getCorte() != null ? favoritos.getCorte().getId() : null);
        detalhes.setMaquiagemId(favoritos.getMaquiagem() != null ? favoritos.getMaquiagem().getId() : null);
        detalhes.setCorCabeloId(favoritos.getCorCabelo() != null ? favoritos.getCorCabelo().getId() : null);
        detalhes.setPenteadoId(favoritos.getPenteado() != null ? favoritos.getPenteado().getId() : null);

        detalhes.setRecomendacaoCorteId(favoritos.getRecomendacaoCorte() != null ? favoritos.getRecomendacaoCorte().getId() : null);
        detalhes.setRecomendacaoCorId(favoritos.getRecomendacaoCor() != null ? favoritos.getRecomendacaoCor().getId() : null);
        detalhes.setRecomendacaoPenteadoId(favoritos.getRecomendacaoPenteado() != null ? favoritos.getRecomendacaoPenteado().getId() : null);
        detalhes.setRecomendacaoMaquiagemId(favoritos.getRecomendacaoMaquiagem() != null ? favoritos.getRecomendacaoMaquiagem().getId() : null);

        detalhes.setProdutoId(favoritos.getProduto() != null ? favoritos.getProduto().getId() : null);
        detalhes.setSimulacaoId(favoritos.getSimulacao() != null ? favoritos.getSimulacao().getId() : null);

        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}