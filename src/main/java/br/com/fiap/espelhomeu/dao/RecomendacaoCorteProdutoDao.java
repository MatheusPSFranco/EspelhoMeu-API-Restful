package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Produto;
import br.com.fiap.espelhomeu.model.RecomendacaoCorte;
import br.com.fiap.espelhomeu.model.RecomendacaoCorteProduto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoCorteProdutoDao {

    private final DataSource dataSource;

    public RecomendacaoCorteProdutoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoCorteProduto> listar() {
        List<RecomendacaoCorteProduto> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_REC_COT_PRO";

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RecomendacaoCorteProduto recomendacao = new RecomendacaoCorteProduto();

                Produto produto = new Produto();
                produto.setId(rs.getLong("ID_PRODUTO"));
                recomendacao.setProduto(produto);

                RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
                recomendacaoCorte.setId(rs.getLong("ID_RECOMENDACAO_CORTE"));
                recomendacao.setRecomendacaoCorte(recomendacaoCorte);

                recomendacao.setJustificativa(rs.getString("JT_PRODUTO"));

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar recomendações de produtos para cortes", e);
        }

        return lista;
    }

    public RecomendacaoCorteProduto buscar(long idProduto, long idRecomendacaoCorte) {
        String sql = "SELECT * FROM T_EM_REC_COT_PRO " +
                "WHERE ID_PRODUTO = ? AND ID_RECOMENDACAO_CORTE = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProduto);
            stmt.setLong(2, idRecomendacaoCorte);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RecomendacaoCorteProduto recomendacao = new RecomendacaoCorteProduto();

                    Produto produto = new Produto();
                    produto.setId(rs.getLong("ID_PRODUTO"));
                    recomendacao.setProduto(produto);

                    RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
                    recomendacaoCorte.setId(rs.getLong("ID_RECOMENDACAO_CORTE"));
                    recomendacao.setRecomendacaoCorte(recomendacaoCorte);

                    recomendacao.setJustificativa(rs.getString("JT_PRODUTO"));

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar recomendação de produto para corte", e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de produto para corte não encontrada");
    }

    public RecomendacaoCorteProduto inserir(RecomendacaoCorteProduto recomendacao) {
        String sql = "INSERT INTO T_EM_REC_COT_PRO " +
                "(ID_PRODUTO, ID_RECOMENDACAO_CORTE, JT_PRODUTO) " +
                "VALUES (?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, recomendacao.getProduto().getId());
            stmt.setLong(2, recomendacao.getRecomendacaoCorte().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir recomendação de produto para corte", e);
        }

        return recomendacao;
    }

    public RecomendacaoCorteProduto atualizar(RecomendacaoCorteProduto recomendacao) {
        String sql = "UPDATE T_EM_REC_COT_PRO SET " +
                "JT_PRODUTO = ? " +
                "WHERE ID_PRODUTO = ? AND ID_RECOMENDACAO_CORTE = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, recomendacao.getJustificativa());
            stmt.setLong(2, recomendacao.getProduto().getId());
            stmt.setLong(3, recomendacao.getRecomendacaoCorte().getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de produto para corte não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar recomendação de produto para corte", e);
        }

        return recomendacao;
    }

    public void deletar(long idProduto, long idRecomendacaoCorte) {
        String sql = "DELETE FROM T_EM_REC_COT_PRO " +
                "WHERE ID_PRODUTO = ? AND ID_RECOMENDACAO_CORTE = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProduto);
            stmt.setLong(2, idRecomendacaoCorte);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de produto para corte não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar recomendação de produto para corte", e);
        }
    }
}