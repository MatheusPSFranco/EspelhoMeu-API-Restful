package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Penteado;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PenteadoDao {

    private final DataSource dataSource;

    public PenteadoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Penteado> listar() {
        List<Penteado> lista = new ArrayList<>();

        String sql = """
                SELECT ID_PENTEADO, NM_PENTEADO, DC_PENTEADO, IM_PENTEADO, NF_MANUTENCAO
                FROM T_EM_PENTEADO
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Penteado penteado = new Penteado();

                penteado.setId(rs.getLong("ID_PENTEADO"));
                penteado.setNome(rs.getString("NM_PENTEADO"));
                penteado.setDescricao(rs.getString("DC_PENTEADO"));
                penteado.setImagem(rs.getBytes("IM_PENTEADO"));
                penteado.setInformacaoManutencao(rs.getString("NF_MANUTENCAO"));

                lista.add(penteado);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar penteados", e);
        }

        return lista;
    }

    public Penteado buscar(long id) {
        String sql = """
                SELECT ID_PENTEADO, NM_PENTEADO, DC_PENTEADO, IM_PENTEADO, NF_MANUTENCAO
                FROM T_EM_PENTEADO
                WHERE ID_PENTEADO = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Penteado penteado = new Penteado();

                    penteado.setId(rs.getLong("ID_PENTEADO"));
                    penteado.setNome(rs.getString("NM_PENTEADO"));
                    penteado.setDescricao(rs.getString("DC_PENTEADO"));
                    penteado.setImagem(rs.getBytes("IM_PENTEADO"));
                    penteado.setInformacaoManutencao(rs.getString("NF_MANUTENCAO"));

                    return penteado;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar penteado", e);
        }

        throw new EntidadeNaoEncontradaException("Penteado não encontrado");
    }

    public Penteado inserir(Penteado penteado) {
        String sql = """
                INSERT INTO T_EM_PENTEADO
                (ID_PENTEADO, NM_PENTEADO, DC_PENTEADO, IM_PENTEADO, NF_MANUTENCAO)
                VALUES (SQ_PENTEADO.NEXTVAL, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_PENTEADO"})) {

            stmt.setString(1, penteado.getNome());
            stmt.setString(2, penteado.getDescricao());
            stmt.setBytes(3, penteado.getImagem());
            stmt.setString(4, penteado.getInformacaoManutencao());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    penteado.setId(rs.getLong(1));
                }
            }

            return penteado;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir penteado", e);
        }
    }

    public Penteado atualizar(Penteado penteado) {
        String sql = """
                UPDATE T_EM_PENTEADO
                SET NM_PENTEADO = ?, DC_PENTEADO = ?, IM_PENTEADO = ?, NF_MANUTENCAO = ?
                WHERE ID_PENTEADO = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, penteado.getNome());
            stmt.setString(2, penteado.getDescricao());
            stmt.setBytes(3, penteado.getImagem());
            stmt.setString(4, penteado.getInformacaoManutencao());
            stmt.setLong(5, penteado.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Penteado não encontrado");
            }

            return penteado;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar penteado", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_PENTEADO
                WHERE ID_PENTEADO = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Penteado não encontrado");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar penteado", e);
        }
    }
}