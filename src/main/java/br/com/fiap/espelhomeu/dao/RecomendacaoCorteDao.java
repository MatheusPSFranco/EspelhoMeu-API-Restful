package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.RecomendacaoCorte;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.Corte;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoCorteDao {

    private final DataSource dataSource;

    public RecomendacaoCorteDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoCorte> listar() {
        List<RecomendacaoCorte> lista = new ArrayList<>();

        String sql = """
                SELECT ID_RECOMENDACAO_CORTE, ID_CONSULTORIA, ID_CORTE, JT_CORTE, DT_CORTE
                FROM T_EM_RECOMENDACAO_CORTE
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RecomendacaoCorte recomendacao = new RecomendacaoCorte();

                recomendacao.setId(rs.getLong("ID_RECOMENDACAO_CORTE"));

                long idConsultoria = rs.getLong("ID_CONSULTORIA");
                if (!rs.wasNull()) {
                    ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
                    consultoria.setId(idConsultoria);
                    recomendacao.setConsultoriaCapilar(consultoria);
                }

                long idCorte = rs.getLong("ID_CORTE");
                if (!rs.wasNull()) {
                    Corte corte = new Corte();
                    corte.setId(idCorte);
                    recomendacao.setCorte(corte);
                }

                recomendacao.setJustificativa(rs.getString("JT_CORTE"));

                Timestamp timestamp = rs.getTimestamp("DT_CORTE");
                if (timestamp != null) {
                    recomendacao.setData(timestamp.toLocalDateTime());
                }

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar recomendações de corte", e);
        }

        return lista;
    }

    public RecomendacaoCorte buscar(long id) {
        String sql = """
                SELECT ID_RECOMENDACAO_CORTE, ID_CONSULTORIA, ID_CORTE, JT_CORTE, DT_CORTE
                FROM T_EM_RECOMENDACAO_CORTE
                WHERE ID_RECOMENDACAO_CORTE = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RecomendacaoCorte recomendacao = new RecomendacaoCorte();

                    recomendacao.setId(rs.getLong("ID_RECOMENDACAO_CORTE"));

                    long idConsultoria = rs.getLong("ID_CONSULTORIA");
                    if (!rs.wasNull()) {
                        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
                        consultoria.setId(idConsultoria);
                        recomendacao.setConsultoriaCapilar(consultoria);
                    }

                    long idCorte = rs.getLong("ID_CORTE");
                    if (!rs.wasNull()) {
                        Corte corte = new Corte();
                        corte.setId(idCorte);
                        recomendacao.setCorte(corte);
                    }

                    recomendacao.setJustificativa(rs.getString("JT_CORTE"));

                    Timestamp timestamp = rs.getTimestamp("DT_CORTE");
                    if (timestamp != null) {
                        recomendacao.setData(timestamp.toLocalDateTime());
                    }

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar recomendação de corte", e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de corte não encontrada");
    }

    public RecomendacaoCorte inserir(RecomendacaoCorte recomendacao) {
        String sql = """
                INSERT INTO T_EM_RECOMENDACAO_CORTE
                (ID_RECOMENDACAO_CORTE, ID_CONSULTORIA, ID_CORTE, JT_CORTE, DT_CORTE)
                VALUES (SQ_RECOMENDACAO_CORTE.NEXTVAL, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_RECOMENDACAO_CORTE"})) {

            stmt.setLong(1, recomendacao.getConsultoriaCapilar().getId());
            stmt.setLong(2, recomendacao.getCorte().getId());
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

            return recomendacao;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir recomendação de corte", e);
        }
    }

    public RecomendacaoCorte atualizar(RecomendacaoCorte recomendacao) {
        String sql = """
                UPDATE T_EM_RECOMENDACAO_CORTE
                SET ID_CONSULTORIA = ?, ID_CORTE = ?, JT_CORTE = ?, DT_CORTE = ?
                WHERE ID_RECOMENDACAO_CORTE = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, recomendacao.getConsultoriaCapilar().getId());
            stmt.setLong(2, recomendacao.getCorte().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            if (recomendacao.getData() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(recomendacao.getData()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }

            stmt.setLong(5, recomendacao.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de corte não encontrada");
            }

            return recomendacao;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar recomendação de corte", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_RECOMENDACAO_CORTE
                WHERE ID_RECOMENDACAO_CORTE = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de corte não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar recomendação de corte", e);
        }
    }
}