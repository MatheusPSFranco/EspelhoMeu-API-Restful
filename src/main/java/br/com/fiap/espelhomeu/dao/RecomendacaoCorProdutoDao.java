package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoCor;
import br.com.fiap.espelhomeu.model.RecomendacaoCorProduto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoCorProdutoDao {

    private final DataSource dataSource;

    public RecomendacaoCorProdutoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoCorProduto> listar() {
        List<RecomendacaoCorProduto> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_REC_COR_PRO";

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RecomendacaoCorProduto recomendacao = new RecomendacaoCorProduto();

                RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
                recomendacaoCor.setId(rs.getLong("ID_RECOMENDACAO_COR"));
                recomendacao.setRecomendacaoCor(recomendacaoCor);

                Produto produto = new Produto();
                produto.setId(rs.getLong("ID_PRODUTO"));
                recomendacao.setProduto(produto);

                recomendacao.setJustificativa(rs.getString("JT_PRODUTO"));

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar recomendações de produtos para cores", e);
        }

        return lista;
    }

    public RecomendacaoCorProduto buscar(long idRecomendacaoCor, long idProduto) {
        String sql = "SELECT * FROM T_EM_REC_COR_PRO " +
                "WHERE ID_RECOMENDACAO_COR = ? AND ID_PRODUTO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idRecomendacaoCor);
            stmt.setLong(2, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RecomendacaoCorProduto recomendacao = new RecomendacaoCorProduto();

                    RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
                    recomendacaoCor.setId(rs.getLong("ID_RECOMENDACAO_COR"));
                    recomendacao.setRecomendacaoCor(recomendacaoCor);

                    Produto produto = new Produto();
                    produto.setId(rs.getLong("ID_PRODUTO"));
                    recomendacao.setProduto(produto);

                    recomendacao.setJustificativa(rs.getString("JT_PRODUTO"));

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar recomendação de produto para cor", e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de produto para cor não encontrada");
    }

    public RecomendacaoCorProduto inserir(RecomendacaoCorProduto recomendacao) {
        String sql = "INSERT INTO T_EM_REC_COR_PRO " +
                "(ID_RECOMENDACAO_COR, ID_PRODUTO, JT_PRODUTO) " +
                "VALUES (?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, recomendacao.getRecomendacaoCor().getId());
            stmt.setLong(2, recomendacao.getProduto().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir recomendação de produto para cor", e);
        }

        return recomendacao;
    }

    public RecomendacaoCorProduto atualizar(RecomendacaoCorProduto recomendacao) {
        String sql = "UPDATE T_EM_REC_COR_PRO SET " +
                "JT_PRODUTO = ? " +
                "WHERE ID_RECOMENDACAO_COR = ? AND ID_PRODUTO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, recomendacao.getJustificativa());
            stmt.setLong(2, recomendacao.getRecomendacaoCor().getId());
            stmt.setLong(3, recomendacao.getProduto().getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de produto para cor não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar recomendação de produto para cor", e);
        }

        return recomendacao;
    }

    public void deletar(long idRecomendacaoCor, long idProduto) {
        String sql = "DELETE FROM T_EM_REC_COR_PRO " +
                "WHERE ID_RECOMENDACAO_COR = ? AND ID_PRODUTO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idRecomendacaoCor);
            stmt.setLong(2, idProduto);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de produto para cor não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar recomendação de produto para cor", e);
        }
    }
}