package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Corte;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CorteDao {

    private final DataSource dataSource;

    public CorteDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Corte> listar() {
        List<Corte> lista = new ArrayList<>();

        String sql = """
                SELECT ID_CORTE, NM_CORTE, DC_CORTE, IM_CORTE, NF_MANUTENCAO
                FROM T_EM_CORTE
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Corte corte = new Corte();

                corte.setId(rs.getLong("ID_CORTE"));
                corte.setNome(rs.getString("NM_CORTE"));
                corte.setDescricao(rs.getString("DC_CORTE"));
                corte.setImagem(rs.getBytes("IM_CORTE"));
                corte.setInformacaoManutencao(rs.getString("NF_MANUTENCAO"));

                lista.add(corte);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar cortes", e);
        }

        return lista;
    }

    public Corte buscar(long id) {
        String sql = """
                SELECT ID_CORTE, NM_CORTE, DC_CORTE, IM_CORTE, NF_MANUTENCAO
                FROM T_EM_CORTE
                WHERE ID_CORTE = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Corte corte = new Corte();

                    corte.setId(rs.getLong("ID_CORTE"));
                    corte.setNome(rs.getString("NM_CORTE"));
                    corte.setDescricao(rs.getString("DC_CORTE"));
                    corte.setImagem(rs.getBytes("IM_CORTE"));
                    corte.setInformacaoManutencao(rs.getString("NF_MANUTENCAO"));

                    return corte;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar corte", e);
        }

        throw new EntidadeNaoEncontradaException("Corte não encontrado");
    }

    public Corte inserir(Corte corte) {
        String sql = """
                INSERT INTO T_EM_CORTE
                (ID_CORTE, NM_CORTE, DC_CORTE, IM_CORTE, NF_MANUTENCAO)
                VALUES (SQ_CORTE.NEXTVAL, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_CORTE"})) {

            stmt.setString(1, corte.getNome());
            stmt.setString(2, corte.getDescricao());
            stmt.setBytes(3, corte.getImagem());
            stmt.setString(4, corte.getInformacaoManutencao());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    corte.setId(rs.getLong(1));
                }
            }

            return corte;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir corte", e);
        }
    }

    public Corte atualizar(Corte corte) {
        String sql = """
                UPDATE T_EM_CORTE
                SET NM_CORTE = ?, DC_CORTE = ?, IM_CORTE = ?, NF_MANUTENCAO = ?
                WHERE ID_CORTE = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, corte.getNome());
            stmt.setString(2, corte.getDescricao());
            stmt.setBytes(3, corte.getImagem());
            stmt.setString(4, corte.getInformacaoManutencao());
            stmt.setLong(5, corte.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Corte não encontrado");
            }

            return corte;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar corte", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_CORTE
                WHERE ID_CORTE = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Corte não encontrado");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar corte", e);
        }
    }
}