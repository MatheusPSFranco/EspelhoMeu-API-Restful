package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.Penteado;
import br.com.fiap.espelhomeu.model.RecomendacaoPenteado;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoPenteadoDao {

    private final DataSource dataSource;

    public RecomendacaoPenteadoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoPenteado> listar() {
        List<RecomendacaoPenteado> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_RECOMENDACAO_PENTEADO";

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RecomendacaoPenteado recomendacao = new RecomendacaoPenteado();

                recomendacao.setId(rs.getLong("ID_RECOMENDACAO_PENTEADO"));

                ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
                consultoria.setId(rs.getLong("ID_CONSULTORIA"));
                recomendacao.setConsultoriaCapilar(consultoria);

                Penteado penteado = new Penteado();
                penteado.setId(rs.getLong("ID_PENTEADO"));
                recomendacao.setPenteado(penteado);

                recomendacao.setJustificativa(rs.getString("JT_PENTEADO"));

                Timestamp timestamp = rs.getTimestamp("DT_PENTEADO");
                if (timestamp != null) {
                    recomendacao.setData(timestamp.toLocalDateTime());
                }

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar recomendações de penteado", e);
        }

        return lista;
    }

    public RecomendacaoPenteado buscar(long id) {
        String sql = "SELECT * FROM T_EM_RECOMENDACAO_PENTEADO WHERE ID_RECOMENDACAO_PENTEADO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RecomendacaoPenteado recomendacao = new RecomendacaoPenteado();

                    recomendacao.setId(rs.getLong("ID_RECOMENDACAO_PENTEADO"));

                    ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
                    consultoria.setId(rs.getLong("ID_CONSULTORIA"));
                    recomendacao.setConsultoriaCapilar(consultoria);

                    Penteado penteado = new Penteado();
                    penteado.setId(rs.getLong("ID_PENTEADO"));
                    recomendacao.setPenteado(penteado);

                    recomendacao.setJustificativa(rs.getString("JT_PENTEADO"));

                    Timestamp timestamp = rs.getTimestamp("DT_PENTEADO");
                    if (timestamp != null) {
                        recomendacao.setData(timestamp.toLocalDateTime());
                    }

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar recomendação de penteado", e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de penteado não encontrada");
    }

    public RecomendacaoPenteado inserir(RecomendacaoPenteado recomendacao) {
        String sql = "INSERT INTO T_EM_RECOMENDACAO_PENTEADO " +
                "(ID_RECOMENDACAO_PENTEADO, ID_CONSULTORIA, ID_PENTEADO, JT_PENTEADO, DT_PENTEADO) " +
                "VALUES (SQ_RECOMENDACAO_PENTEADO.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, new String[]{"ID_RECOMENDACAO_PENTEADO"})) {

            stmt.setLong(1, recomendacao.getConsultoriaCapilar().getId());
            stmt.setLong(2, recomendacao.getPenteado().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            if (recomendacao.getData() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(recomendacao.getData()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    recomendacao.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir recomendação de penteado", e);
        }

        return recomendacao;
    }

    public RecomendacaoPenteado atualizar(RecomendacaoPenteado recomendacao) {
        String sql = "UPDATE T_EM_RECOMENDACAO_PENTEADO SET " +
                "ID_CONSULTORIA = ?, ID_PENTEADO = ?, JT_PENTEADO = ?, DT_PENTEADO = ? " +
                "WHERE ID_RECOMENDACAO_PENTEADO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, recomendacao.getConsultoriaCapilar().getId());
            stmt.setLong(2, recomendacao.getPenteado().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            if (recomendacao.getData() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(recomendacao.getData()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }

            stmt.setLong(5, recomendacao.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de penteado não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar recomendação de penteado", e);
        }

        return recomendacao;
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_RECOMENDACAO_PENTEADO WHERE ID_RECOMENDACAO_PENTEADO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de penteado não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar recomendação de penteado", e);
        }
    }
}