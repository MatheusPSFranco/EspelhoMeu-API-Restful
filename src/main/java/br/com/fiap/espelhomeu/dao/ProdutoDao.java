package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Produto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoDao {

    private final DataSource dataSource;

    public ProdutoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_PRODUTO";

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto();

                produto.setId(rs.getLong("ID_PRODUTO"));
                produto.setCategoria(rs.getString("CT_PRODUTO"));
                produto.setNome(rs.getString("NM_PRODUTO"));
                produto.setMarca(rs.getString("MC_PRODUTO"));
                produto.setCategoriaPrincipal(rs.getString("CT_PRINCIPAL"));
                produto.setPrecoReferencia(rs.getBigDecimal("PR_REFERENCIA"));
                produto.setFaixaPreco(rs.getString("FX_PRECO"));

                double avaliacao = rs.getDouble("AV_PRODUTO");
                if (!rs.wasNull()) {
                    produto.setAvaliacao(avaliacao);
                }

                produto.setCor(rs.getString("CR_PRODUTO"));

                Timestamp timestamp = rs.getTimestamp("DT_PRODUTO");
                if (timestamp != null) {
                    produto.setData(timestamp.toLocalDateTime());
                }

                lista.add(produto);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar produtos", e);
        }

        return lista;
    }

    public Produto buscar(long id) {
        String sql = "SELECT * FROM T_EM_PRODUTO WHERE ID_PRODUTO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();

                    produto.setId(rs.getLong("ID_PRODUTO"));
                    produto.setCategoria(rs.getString("CT_PRODUTO"));
                    produto.setNome(rs.getString("NM_PRODUTO"));
                    produto.setMarca(rs.getString("MC_PRODUTO"));
                    produto.setCategoriaPrincipal(rs.getString("CT_PRINCIPAL"));
                    produto.setPrecoReferencia(rs.getBigDecimal("PR_REFERENCIA"));
                    produto.setFaixaPreco(rs.getString("FX_PRECO"));

                    double avaliacao = rs.getDouble("AV_PRODUTO");
                    if (!rs.wasNull()) {
                        produto.setAvaliacao(avaliacao);
                    }

                    produto.setCor(rs.getString("CR_PRODUTO"));

                    Timestamp timestamp = rs.getTimestamp("DT_PRODUTO");
                    if (timestamp != null) {
                        produto.setData(timestamp.toLocalDateTime());
                    }

                    return produto;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar produto", e);
        }

        throw new EntidadeNaoEncontradaException("Produto não encontrado");
    }

    public Produto inserir(Produto produto) {
        String sql = "INSERT INTO T_EM_PRODUTO " +
                "(ID_PRODUTO, CT_PRODUTO, NM_PRODUTO, MC_PRODUTO, CT_PRINCIPAL, PR_REFERENCIA, FX_PRECO, AV_PRODUTO, CR_PRODUTO, DT_PRODUTO) " +
                "VALUES (SQ_PRODUTO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, new String[]{"ID_PRODUTO"})) {

            stmt.setString(1, produto.getCategoria());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getMarca());
            stmt.setString(4, produto.getCategoriaPrincipal());

            if (produto.getPrecoReferencia() != null) {
                stmt.setBigDecimal(5, produto.getPrecoReferencia());
            } else {
                stmt.setNull(5, Types.NUMERIC);
            }

            stmt.setString(6, produto.getFaixaPreco());

            if (produto.getAvaliacao() != null) {
                stmt.setDouble(7, produto.getAvaliacao());
            } else {
                stmt.setNull(7, Types.NUMERIC);
            }

            stmt.setString(8, produto.getCor());

            if (produto.getData() != null) {
                stmt.setTimestamp(9, Timestamp.valueOf(produto.getData()));
            } else {
                stmt.setNull(9, Types.TIMESTAMP);
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir produto", e);
        }

        return produto;
    }

    public Produto atualizar(Produto produto) {
        String sql = "UPDATE T_EM_PRODUTO SET " +
                "CT_PRODUTO = ?, NM_PRODUTO = ?, MC_PRODUTO = ?, CT_PRINCIPAL = ?, " +
                "PR_REFERENCIA = ?, FX_PRECO = ?, AV_PRODUTO = ?, CR_PRODUTO = ?, DT_PRODUTO = ? " +
                "WHERE ID_PRODUTO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getCategoria());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getMarca());
            stmt.setString(4, produto.getCategoriaPrincipal());

            if (produto.getPrecoReferencia() != null) {
                stmt.setBigDecimal(5, produto.getPrecoReferencia());
            } else {
                stmt.setNull(5, Types.NUMERIC);
            }

            stmt.setString(6, produto.getFaixaPreco());

            if (produto.getAvaliacao() != null) {
                stmt.setDouble(7, produto.getAvaliacao());
            } else {
                stmt.setNull(7, Types.NUMERIC);
            }

            stmt.setString(8, produto.getCor());

            if (produto.getData() != null) {
                stmt.setTimestamp(9, Timestamp.valueOf(produto.getData()));
            } else {
                stmt.setNull(9, Types.TIMESTAMP);
            }

            stmt.setLong(10, produto.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Produto não encontrado");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar produto", e);
        }

        return produto;
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_PRODUTO WHERE ID_PRODUTO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Produto não encontrado");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar produto", e);
        }
    }
}