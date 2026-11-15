package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.ConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.model.PerfilMaquiagem;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultoriaMaquiagemDao {

    private final DataSource dataSource;

    public ConsultoriaMaquiagemDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ConsultoriaMaquiagem> listar() {
        List<ConsultoriaMaquiagem> lista = new ArrayList<>();

        String sql = """
                SELECT ID_CONSULTORIA_MAQUIAGEM, ID_PERFIL_MAQUIAGEM, DT_CONSULTORIA
                FROM T_EM_CONSULTORIA_MAQUIAGEM
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ConsultoriaMaquiagem consultoria = new ConsultoriaMaquiagem();

                consultoria.setId(rs.getLong("ID_CONSULTORIA_MAQUIAGEM"));

                long idPerfilMaquiagem = rs.getLong("ID_PERFIL_MAQUIAGEM");
                if (!rs.wasNull()) {
                    PerfilMaquiagem perfilMaquiagem = new PerfilMaquiagem();
                    perfilMaquiagem.setId(idPerfilMaquiagem);
                    consultoria.setPerfilMaquiagem(perfilMaquiagem);
                }

                Timestamp timestamp = rs.getTimestamp("DT_CONSULTORIA");
                if (timestamp != null) {
                    consultoria.setData(timestamp.toLocalDateTime());
                }

                lista.add(consultoria);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar consultorias de maquiagem", e);
        }

        return lista;
    }

    public ConsultoriaMaquiagem buscar(long id) {
        String sql = """
                SELECT ID_CONSULTORIA_MAQUIAGEM, ID_PERFIL_MAQUIAGEM, DT_CONSULTORIA
                FROM T_EM_CONSULTORIA_MAQUIAGEM
                WHERE ID_CONSULTORIA_MAQUIAGEM = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ConsultoriaMaquiagem consultoria = new ConsultoriaMaquiagem();

                    consultoria.setId(rs.getLong("ID_CONSULTORIA_MAQUIAGEM"));

                    long idPerfilMaquiagem = rs.getLong("ID_PERFIL_MAQUIAGEM");
                    if (!rs.wasNull()) {
                        PerfilMaquiagem perfilMaquiagem = new PerfilMaquiagem();
                        perfilMaquiagem.setId(idPerfilMaquiagem);
                        consultoria.setPerfilMaquiagem(perfilMaquiagem);
                    }

                    Timestamp timestamp = rs.getTimestamp("DT_CONSULTORIA");
                    if (timestamp != null) {
                        consultoria.setData(timestamp.toLocalDateTime());
                    }

                    return consultoria;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar consultoria de maquiagem", e);
        }

        throw new EntidadeNaoEncontradaException("Consultoria de maquiagem não encontrada");
    }

    public ConsultoriaMaquiagem inserir(ConsultoriaMaquiagem consultoria) {
        String sql = """
                INSERT INTO T_EM_CONSULTORIA_MAQUIAGEM
                (ID_CONSULTORIA_MAQUIAGEM, ID_PERFIL_MAQUIAGEM, DT_CONSULTORIA)
                VALUES (SQ_CONSULTORIA_MAQUIAGEM.NEXTVAL, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_CONSULTORIA_MAQUIAGEM"})) {

            stmt.setLong(1, consultoria.getPerfilMaquiagem().getId());

            if (consultoria.getData() != null) {
                stmt.setTimestamp(2, Timestamp.valueOf(consultoria.getData()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    consultoria.setId(rs.getLong(1));
                }
            }

            return consultoria;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir consultoria de maquiagem", e);
        }
    }

    public ConsultoriaMaquiagem atualizar(ConsultoriaMaquiagem consultoria) {
        String sql = """
                UPDATE T_EM_CONSULTORIA_MAQUIAGEM
                SET ID_PERFIL_MAQUIAGEM = ?, DT_CONSULTORIA = ?
                WHERE ID_CONSULTORIA_MAQUIAGEM = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, consultoria.getPerfilMaquiagem().getId());

            if (consultoria.getData() != null) {
                stmt.setTimestamp(2, Timestamp.valueOf(consultoria.getData()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }

            stmt.setLong(3, consultoria.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Consultoria de maquiagem não encontrada");
            }

            return consultoria;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar consultoria de maquiagem", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_CONSULTORIA_MAQUIAGEM
                WHERE ID_CONSULTORIA_MAQUIAGEM = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Consultoria de maquiagem não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar consultoria de maquiagem", e);
        }
    }
}