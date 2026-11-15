package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.RecomendacaoCor;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.CorCabelo;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoCorDao {

    private final DataSource dataSource;

    public RecomendacaoCorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoCor> listar() {
        List<RecomendacaoCor> lista = new ArrayList<>();

        String sql = """
                SELECT ID_RECOMENDACAO_COR, ID_CONSULTORIA, ID_COR, JT_COR, DT_COR
                FROM T_EM_RECOMENDACAO_COR
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RecomendacaoCor recomendacao = new RecomendacaoCor();

                recomendacao.setId(rs.getLong("ID_RECOMENDACAO_COR"));

                long idConsultoria = rs.getLong("ID_CONSULTORIA");
                if (!rs.wasNull()) {
                    ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
                    consultoria.setId(idConsultoria);
                    recomendacao.setConsultoriaCapilar(consultoria);
                }

                long idCor = rs.getLong("ID_COR");
                if (!rs.wasNull()) {
                    CorCabelo cor = new CorCabelo();
                    cor.setId(idCor);
                    recomendacao.setCorCabelo(cor);
                }

                recomendacao.setJustificativa(rs.getString("JT_COR"));

                Timestamp timestamp = rs.getTimestamp("DT_COR");
                if (timestamp != null) {
                    recomendacao.setData(timestamp.toLocalDateTime());
                }

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar recomendações de cor", e);
        }

        return lista;
    }

    public RecomendacaoCor buscar(long id) {
        String sql = """
                SELECT ID_RECOMENDACAO_COR, ID_CONSULTORIA, ID_COR, JT_COR, DT_COR
                FROM T_EM_RECOMENDACAO_COR
                WHERE ID_RECOMENDACAO_COR = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RecomendacaoCor recomendacao = new RecomendacaoCor();

                    recomendacao.setId(rs.getLong("ID_RECOMENDACAO_COR"));

                    long idConsultoria = rs.getLong("ID_CONSULTORIA");
                    if (!rs.wasNull()) {
                        ConsultoriaCapilar consultoria = new ConsultoriaCapilar();
                        consultoria.setId(idConsultoria);
                        recomendacao.setConsultoriaCapilar(consultoria);
                    }

                    long idCor = rs.getLong("ID_COR");
                    if (!rs.wasNull()) {
                        CorCabelo cor = new CorCabelo();
                        cor.setId(idCor);
                        recomendacao.setCorCabelo(cor);
                    }

                    recomendacao.setJustificativa(rs.getString("JT_COR"));

                    Timestamp timestamp = rs.getTimestamp("DT_COR");
                    if (timestamp != null) {
                        recomendacao.setData(timestamp.toLocalDateTime());
                    }

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar recomendação de cor", e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de cor não encontrada");
    }

    public RecomendacaoCor inserir(RecomendacaoCor recomendacao) {
        String sql = """
                INSERT INTO T_EM_RECOMENDACAO_COR
                (ID_RECOMENDACAO_COR, ID_CONSULTORIA, ID_COR, JT_COR, DT_COR)
                VALUES (SQ_RECOMENDACAO_COR.NEXTVAL, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_RECOMENDACAO_COR"})) {

            stmt.setLong(1, recomendacao.getConsultoriaCapilar().getId());
            stmt.setLong(2, recomendacao.getCorCabelo().getId());
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
            throw new ErroBancoDadosException("Erro ao inserir recomendação de cor", e);
        }
    }

    public RecomendacaoCor atualizar(RecomendacaoCor recomendacao) {
        String sql = """
                UPDATE T_EM_RECOMENDACAO_COR
                SET ID_CONSULTORIA = ?, ID_COR = ?, JT_COR = ?, DT_COR = ?
                WHERE ID_RECOMENDACAO_COR = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, recomendacao.getConsultoriaCapilar().getId());
            stmt.setLong(2, recomendacao.getCorCabelo().getId());
            stmt.setString(3, recomendacao.getJustificativa());

            if (recomendacao.getData() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(recomendacao.getData()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }

            stmt.setLong(5, recomendacao.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de cor não encontrada");
            }

            return recomendacao;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar recomendação de cor", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_RECOMENDACAO_COR
                WHERE ID_RECOMENDACAO_COR = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de cor não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar recomendação de cor", e);
        }
    }
}