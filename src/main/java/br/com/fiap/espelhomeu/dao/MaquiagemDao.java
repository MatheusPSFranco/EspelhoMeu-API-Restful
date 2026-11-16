package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Maquiagem;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaquiagemDao {

    private final DataSource dataSource;

    public MaquiagemDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Maquiagem> listar() {
        List<Maquiagem> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_MAQUIAGEM";

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Maquiagem maquiagem = new Maquiagem();

                maquiagem.setId(rs.getLong("ID_MAQUIAGEM"));
                maquiagem.setTipo(rs.getString("TP_MAQUIAGEM"));
                maquiagem.setCor(rs.getString("CR_MAQUIAGEM"));
                maquiagem.setPrecoReferencia(rs.getBigDecimal("PR_REFERENCIA"));
                maquiagem.setFaixaPreco(rs.getString("FX_PRECO"));
                maquiagem.setMarca(rs.getString("MC_MAQUIAGEM"));
                maquiagem.setLinhaProduto(rs.getString("LN_MAQUIAGEM"));
                maquiagem.setCodigoTonalidade(rs.getString("CD_TONALIDADE"));
                maquiagem.setTipoCobertura(rs.getString("TP_COBERTURA"));
                maquiagem.setAcabamento(rs.getString("AC_MAQUIAGEM"));

                double avaliacao = rs.getDouble("AV_MAQUIAGEM");
                if (!rs.wasNull()) {
                    maquiagem.setAvaliacao(avaliacao);
                }

                lista.add(maquiagem);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar maquiagens", e);
        }

        return lista;
    }

    public Maquiagem buscar(long id) {
        String sql = "SELECT * FROM T_EM_MAQUIAGEM WHERE ID_MAQUIAGEM = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Maquiagem maquiagem = new Maquiagem();

                    maquiagem.setId(rs.getLong("ID_MAQUIAGEM"));
                    maquiagem.setTipo(rs.getString("TP_MAQUIAGEM"));
                    maquiagem.setCor(rs.getString("CR_MAQUIAGEM"));
                    maquiagem.setPrecoReferencia(rs.getBigDecimal("PR_REFERENCIA"));
                    maquiagem.setFaixaPreco(rs.getString("FX_PRECO"));
                    maquiagem.setMarca(rs.getString("MC_MAQUIAGEM"));
                    maquiagem.setLinhaProduto(rs.getString("LN_MAQUIAGEM"));
                    maquiagem.setCodigoTonalidade(rs.getString("CD_TONALIDADE"));
                    maquiagem.setTipoCobertura(rs.getString("TP_COBERTURA"));
                    maquiagem.setAcabamento(rs.getString("AC_MAQUIAGEM"));

                    double avaliacao = rs.getDouble("AV_MAQUIAGEM");
                    if (!rs.wasNull()) {
                        maquiagem.setAvaliacao(avaliacao);
                    }

                    return maquiagem;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar maquiagem", e);
        }

        throw new EntidadeNaoEncontradaException("Maquiagem não encontrada");
    }

    public Maquiagem inserir(Maquiagem maquiagem) {
        String sql = "INSERT INTO T_EM_MAQUIAGEM " +
                "(ID_MAQUIAGEM, TP_MAQUIAGEM, CR_MAQUIAGEM, PR_REFERENCIA, FX_PRECO, MC_MAQUIAGEM, " +
                "LN_MAQUIAGEM, CD_TONALIDADE, TP_COBERTURA, AC_MAQUIAGEM, AV_MAQUIAGEM) " +
                "VALUES (SQ_MAQUIAGEM.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, new String[]{"ID_MAQUIAGEM"})) {

            stmt.setString(1, maquiagem.getTipo());
            stmt.setString(2, maquiagem.getCor());
            stmt.setBigDecimal(3, maquiagem.getPrecoReferencia());
            stmt.setString(4, maquiagem.getFaixaPreco());
            stmt.setString(5, maquiagem.getMarca());
            stmt.setString(6, maquiagem.getLinhaProduto());
            stmt.setString(7, maquiagem.getCodigoTonalidade());
            stmt.setString(8, maquiagem.getTipoCobertura());
            stmt.setString(9, maquiagem.getAcabamento());

            if (maquiagem.getAvaliacao() != null) {
                stmt.setDouble(10, maquiagem.getAvaliacao());
            } else {
                stmt.setNull(10, Types.NUMERIC);
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    maquiagem.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir maquiagem", e);
        }

        return maquiagem;
    }

    public Maquiagem atualizar(Maquiagem maquiagem) {
        String sql = "UPDATE T_EM_MAQUIAGEM SET " +
                "TP_MAQUIAGEM = ?, CR_MAQUIAGEM = ?, PR_REFERENCIA = ?, FX_PRECO = ?, MC_MAQUIAGEM = ?, " +
                "LN_MAQUIAGEM = ?, CD_TONALIDADE = ?, TP_COBERTURA = ?, AC_MAQUIAGEM = ?, AV_MAQUIAGEM = ? " +
                "WHERE ID_MAQUIAGEM = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, maquiagem.getTipo());
            stmt.setString(2, maquiagem.getCor());
            stmt.setBigDecimal(3, maquiagem.getPrecoReferencia());
            stmt.setString(4, maquiagem.getFaixaPreco());
            stmt.setString(5, maquiagem.getMarca());
            stmt.setString(6, maquiagem.getLinhaProduto());
            stmt.setString(7, maquiagem.getCodigoTonalidade());
            stmt.setString(8, maquiagem.getTipoCobertura());
            stmt.setString(9, maquiagem.getAcabamento());

            if (maquiagem.getAvaliacao() != null) {
                stmt.setDouble(10, maquiagem.getAvaliacao());
            } else {
                stmt.setNull(10, Types.NUMERIC);
            }

            stmt.setLong(11, maquiagem.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Maquiagem não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar maquiagem", e);
        }

        return maquiagem;
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_MAQUIAGEM WHERE ID_MAQUIAGEM = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Maquiagem não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar maquiagem", e);
        }
    }
}