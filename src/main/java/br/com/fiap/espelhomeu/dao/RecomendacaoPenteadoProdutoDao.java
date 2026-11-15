package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteado;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteadoProduto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoPenteadoProdutoDao {

    private final DataSource dataSource;

    public RecomendacaoPenteadoProdutoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoPenteadoProduto> listar() {
        List<RecomendacaoPenteadoProduto> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_REC_PEN_PRO";

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RecomendacaoPenteadoProduto recomendacao = new RecomendacaoPenteadoProduto();

                Produto produto = new Produto();
                produto.setId(rs.getLong("ID_PRODUTO"));
                recomendacao.setProduto(produto);

                RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
                recomendacaoPenteado.setId(rs.getLong("ID_RECOMENDACAO_PENTEADO"));
                recomendacao.setRecomendacaoPenteado(recomendacaoPenteado);

                recomendacao.setJustificativa(rs.getString("JT_PRODUTO"));

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar recomendações de produtos para penteados", e);
        }

        return lista;
    }

    public RecomendacaoPenteadoProduto buscar(long idProduto, long idRecomendacaoPenteado) {
        String sql = "SELECT * FROM T_EM_REC_PEN_PRO " +
                "WHERE ID_PRODUTO = ? AND ID_RECOMENDACAO_PENTEADO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProduto);
            stmt.setLong(2, idRecomendacaoPenteado);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RecomendacaoPenteadoProduto recomendacao = new RecomendacaoPenteadoProduto();

                    Produto produto = new Produto();
                    produto.setId(rs.getLong("ID_PRODUTO"));
                    recomendacao.setProduto(produto);

                    RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
                    recomendacaoPenteado.setId(rs.getLong("ID_RECOMENDACAO_PENTEADO"));
                    recomendacao.setRecomendacaoPenteado(recomendacaoPenteado);

                    recomendacao.setJustificativa(rs.getString("JT_PRODUTO"));

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar recomendação de produto para penteado", e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de produto para penteado não encontrada");
    }

    public RecomendacaoPenteadoProduto inserir(RecomendacaoPenteadoProduto recomendacao) {
        String sql = "INSERT INTO T_EM_REC_PEN_PRO " +
                "(ID_PRODUTO, ID_RECOMENDACAO_PENTEADO, JT_PRODUTO) " +
                "VALUES (?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, recomendacao.getProduto().getId());
            stmt.setLong(2, recomendacao.getRecomendacaoPenteado().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir recomendação de produto para penteado", e);
        }

        return recomendacao;
    }

    public RecomendacaoPenteadoProduto atualizar(RecomendacaoPenteadoProduto recomendacao) {
        String sql = "UPDATE T_EM_REC_PEN_PRO SET " +
                "JT_PRODUTO = ? " +
                "WHERE ID_PRODUTO = ? AND ID_RECOMENDACAO_PENTEADO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, recomendacao.getJustificativa());
            stmt.setLong(2, recomendacao.getProduto().getId());
            stmt.setLong(3, recomendacao.getRecomendacaoPenteado().getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de produto para penteado não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar recomendação de produto para penteado", e);
        }

        return recomendacao;
    }

    public void deletar(long idProduto, long idRecomendacaoPenteado) {
        String sql = "DELETE FROM T_EM_REC_PEN_PRO " +
                "WHERE ID_PRODUTO = ? AND ID_RECOMENDACAO_PENTEADO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProduto);
            stmt.setLong(2, idRecomendacaoPenteado);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de produto para penteado não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar recomendação de produto para penteado", e);
        }
    }
}