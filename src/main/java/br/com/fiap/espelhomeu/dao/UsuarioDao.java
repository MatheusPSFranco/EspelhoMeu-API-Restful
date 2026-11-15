package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDao {
    private final DataSource dataSource;
    public UsuarioDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();

        String sql = """
                SELECT ID_USUARIO, NM_USUARIO, EM_USUARIO, DT_NASCIMENTO,
                       TL_USUARIO, ST_USUARIO
                FROM T_EM_USUARIO
                """;

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(rs.getLong("ID_USUARIO"));
                usuario.setNome(rs.getString("NM_USUARIO"));
                usuario.setEmail(rs.getString("EM_USUARIO"));
                usuario.setData(rs.getDate("DT_NASCIMENTO").toLocalDate());
                usuario.setTelefone(rs.getString("TL_USUARIO"));
                usuario.setStatus(rs.getString("ST_USUARIO"));
                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar os Usuarios.", e);
        }

        return lista;
    }

    public Usuario buscar(long id) {
        String sql = """
                SELECT ID_USUARIO, NM_USUARIO, EM_USUARIO, DT_NASCIMENTO,
                       TL_USUARIO, ST_USUARIO
                FROM T_EM_USUARIO
                WHERE ID_USUARIO = ?
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setId(rs.getLong("ID_USUARIO"));
                    usuario.setNome(rs.getString("NM_USUARIO"));
                    usuario.setEmail(rs.getString("EM_USUARIO"));
                    usuario.setData(rs.getDate("DT_NASCIMENTO").toLocalDate());
                    usuario.setTelefone(rs.getString("TL_USUARIO"));
                    usuario.setStatus(rs.getString("ST_USUARIO"));

                    return usuario;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar o Usuario.", e);
        }

        throw new EntidadeNaoEncontradaException("Usuario não encontrado.");
    }

    public void inserir(Usuario usuario) {
        String sql = """
                INSERT INTO T_EM_USUARIO (
                    ID_USUARIO, NM_USUARIO, EM_USUARIO, DT_NASCIMENTO,
                    TL_USUARIO, ST_USUARIO
                ) VALUES (
                    SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?
                )
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, new String[]{"ID_USUARIO"})) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setDate(3, java.sql.Date.valueOf(usuario.getData()));
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getStatus());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir o Usuario.", e);
        }
    }

    public void atualizar(Usuario usuario) {
        String sql = """
                UPDATE T_EM_USUARIO SET
                    NM_USUARIO = ?,
                    EM_USUARIO = ?,
                    DT_NASCIMENTO = ?,
                    TL_USUARIO = ?,
                    ST_USUARIO = ?
                    
                WHERE ID_USUARIO = ?
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setDate(3, java.sql.Date.valueOf(usuario.getData()));
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getStatus());
            stmt.setLong(6, usuario.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Usuario não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar o Usuario.", e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_USUARIO WHERE ID_USUARIO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Usuario não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar o Usuario.", e);
        }
    }
}
