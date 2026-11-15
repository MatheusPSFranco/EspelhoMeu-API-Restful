package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.ConsultoriaCapilar;
import br.com.fiap.espelhomeu.model.PerfilCabelo;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultoriaCapilarDao {

    private final DataSource dataSource;

    public ConsultoriaCapilarDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ConsultoriaCapilar> listar() {
        List<ConsultoriaCapilar> lista = new ArrayList<>();

        String sql = """
                SELECT ID_CONSULTORIA, ID_PERFIL_CABELO, DT_CONSULTORIA
                FROM T_EM_CONSULTORIA_CAPILAR
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ConsultoriaCapilar consultoria = new ConsultoriaCapilar();

                consultoria.setId(rs.getLong("ID_CONSULTORIA"));

                long idPerfilCabelo = rs.getLong("ID_PERFIL_CABELO");
                if (!rs.wasNull()) {
                    PerfilCabelo perfilCabelo = new PerfilCabelo();
                    perfilCabelo.setId(idPerfilCabelo);
                    consultoria.setPerfilCabelo(perfilCabelo);
                }

                consultoria.setData(rs.getTimestamp("DT_CONSULTORIA").toLocalDateTime());

                lista.add(consultoria);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar consultorias capilares", e);
        }

        return lista;
    }

    public ConsultoriaCapilar buscar(long id) {
        String sql = """
                SELECT ID_CONSULTORIA, ID_PERFIL_CABELO, DT_CONSULTORIA
                FROM T_EM_CONSULTORIA_CAPILAR
                WHERE ID_CONSULTORIA = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ConsultoriaCapilar consultoria = new ConsultoriaCapilar();

                    consultoria.setId(rs.getLong("ID_CONSULTORIA"));

                    long idPerfilCabelo = rs.getLong("ID_PERFIL_CABELO");
                    if (!rs.wasNull()) {
                        PerfilCabelo perfilCabelo = new PerfilCabelo();
                        perfilCabelo.setId(idPerfilCabelo);
                        consultoria.setPerfilCabelo(perfilCabelo);
                    }

                    consultoria.setData(rs.getTimestamp("DT_CONSULTORIA").toLocalDateTime());

                    return consultoria;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar consultoria capilar", e);
        }

        throw new EntidadeNaoEncontradaException("Consultoria capilar não encontrada");
    }

    public ConsultoriaCapilar inserir(ConsultoriaCapilar consultoria) {
        String sql = """
                INSERT INTO T_EM_CONSULTORIA_CAPILAR
                (ID_CONSULTORIA, ID_PERFIL_CABELO, DT_CONSULTORIA)
                VALUES (SQ_CONSULTORIA_CAPILAR.NEXTVAL, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_CONSULTORIA"})) {

            stmt.setLong(1, consultoria.getPerfilCabelo().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(consultoria.getData()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    consultoria.setId(rs.getLong(1));
                }
            }

            return consultoria;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir consultoria capilar", e);
        }
    }

    public ConsultoriaCapilar atualizar(ConsultoriaCapilar consultoria) {
        String sql = """
                UPDATE T_EM_CONSULTORIA_CAPILAR
                SET ID_PERFIL_CABELO = ?, DT_CONSULTORIA = ?
                WHERE ID_CONSULTORIA = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, consultoria.getPerfilCabelo().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(consultoria.getData()));
            stmt.setLong(3, consultoria.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Consultoria capilar não encontrada");
            }

            return consultoria;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar consultoria capilar", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_CONSULTORIA_CAPILAR
                WHERE ID_CONSULTORIA = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Consultoria capilar não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar consultoria capilar", e);
        }
    }
}