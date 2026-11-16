package br.com.fiap.espelhomeu.controller;

import br.com.fiap.espelhomeu.dao.MaquiagemDao;
import br.com.fiap.espelhomeu.dto.AtualizacaoMaquiagem;
import br.com.fiap.espelhomeu.dto.CadastroMaquiagem;
import br.com.fiap.espelhomeu.dto.DetalhesMaquiagem;
import br.com.fiap.espelhomeu.model.Maquiagem;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/maquiagem")
public class MaquiagemController {

    private final MaquiagemDao dao;
    private final ModelMapper mapper;

    public MaquiagemController(MaquiagemDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DetalhesMaquiagem> listar()  {
        return dao.listar().stream()
                .map(maquiagem -> mapper.map(maquiagem, DetalhesMaquiagem.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMaquiagem> buscar(@PathVariable long id)  {
        Maquiagem maquiagem = dao.buscar(id);

        return ResponseEntity.ok(mapper.map(maquiagem, DetalhesMaquiagem.class));
    }

    @PostMapping
    public ResponseEntity<DetalhesMaquiagem> inserir(
            @RequestBody @Valid CadastroMaquiagem dto,
            UriComponentsBuilder uriBuilder)  {

        Maquiagem maquiagem = mapper.map(dto, Maquiagem.class);

        definirFaixaPreco(maquiagem);

        dao.inserir(maquiagem);

        DetalhesMaquiagem detalhes = mapper.map(maquiagem, DetalhesMaquiagem.class);

        URI uri = uriBuilder
                .path("/maquiagem/{id}")
                .buildAndExpand(maquiagem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesMaquiagem> atualizar(
            @PathVariable long id,
            @RequestBody @Valid AtualizacaoMaquiagem dto)  {

        Maquiagem maquiagem = dao.buscar(id);

        mapper.map(dto, maquiagem);

        definirFaixaPreco(maquiagem);

        dao.atualizar(maquiagem);

        return ResponseEntity.ok(mapper.map(maquiagem, DetalhesMaquiagem.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id)  {
        dao.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private void definirFaixaPreco(Maquiagem maquiagem) {

        BigDecimal preco = maquiagem.getPrecoReferencia();
        String tipo = maquiagem.getTipo();

        if (preco == null || tipo == null) {
            maquiagem.setFaixaPreco(null);
            return;
        }

        switch (tipo) {

            case "Base":
                if (preco.compareTo(new BigDecimal("50.00")) < 0) {
                    maquiagem.setFaixaPreco("Custo-beneficio");
                } else if (preco.compareTo(new BigDecimal("180.00")) < 0) {
                    maquiagem.setFaixaPreco("Intermediário");
                } else {
                    maquiagem.setFaixaPreco("Caro");
                }
                break;

            case "Corretivo":
                if (preco.compareTo(new BigDecimal("40.00")) < 0) {
                    maquiagem.setFaixaPreco("Custo-beneficio");
                } else if (preco.compareTo(new BigDecimal("120.00")) < 0) {
                    maquiagem.setFaixaPreco("Intermediário");
                } else {
                    maquiagem.setFaixaPreco("Caro");
                }
                break;

            case "Pó Compacto":
                if (preco.compareTo(new BigDecimal("40.00")) < 0) {
                    maquiagem.setFaixaPreco("Custo-beneficio");
                } else if (preco.compareTo(new BigDecimal("100.00")) < 0) {
                    maquiagem.setFaixaPreco("Intermediário");
                } else {
                    maquiagem.setFaixaPreco("Caro");
                }
                break;

            case "Pó Solto":
                if (preco.compareTo(new BigDecimal("50.00")) < 0) {
                    maquiagem.setFaixaPreco("Custo-beneficio");
                } else if (preco.compareTo(new BigDecimal("110.00")) < 0) {
                    maquiagem.setFaixaPreco("Intermediário");
                } else {
                    maquiagem.setFaixaPreco("Caro");
                }
                break;
        }
    }
}