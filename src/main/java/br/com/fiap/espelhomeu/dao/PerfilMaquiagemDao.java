package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.PerfilMaquiagem;
import br.com.fiap.espelhomeu.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PerfilMaquiagemDao {
    private final DataSource dataSource;
    public PerfilMaquiagemDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PerfilMaquiagem> listar() {
        List<PerfilMaquiagem> lista = new ArrayList<>();

        String sql = """
                SELECT ID_PERFIL_MAQUIAGEM, ID_USUARIO, TP_PELE, TM_PELE,
                       SB_PELE, NS_PELE, PD_UTILIZADO, TN_UTILIZADA,
                       DF_BASE, DF_CORRETIVO, CB_DESEJADA, AC_PREFERIDO,
                       FX_PRECO, DT_PERFIL
                FROM T_EM_PERFIL_MAQUIAGEM
                """;

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PerfilMaquiagem perfilMaquiagem = new PerfilMaquiagem();

                perfilMaquiagem.setId(rs.getLong("ID_PERFIL_MAQUIAGEM"));
                long idUsuario = rs.getLong("ID_USUARIO");
                if (!rs.wasNull()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(idUsuario);
                    perfilMaquiagem.setUsuario(usuario);
                }
                perfilMaquiagem.setTipopele(rs.getString("TP_PELE"));
                perfilMaquiagem.setTomPele(rs.getString("TM_PELE"));
                perfilMaquiagem.setSubtomPele(rs.getString("SB_PELE"));
                perfilMaquiagem.setNecessidadePele(rs.getString("NS_PELE"));
                perfilMaquiagem.setProdutoUtilizado(rs.getString("PD_UTILIZADO"));
                perfilMaquiagem.setTonalidadeUtilizada(rs.getString("TN_UTILIZADA"));
                perfilMaquiagem.setDificuldadeBase(rs.getString("DF_BASE"));
                perfilMaquiagem.setDificuldadeCorretivo(rs.getString("DF_CORRETIVO"));
                perfilMaquiagem.setCoberturaDesejada(rs.getString("CB_DESEJADA"));
                perfilMaquiagem.setAcabamentoPreferido(rs.getString("AC_PREFERIDO"));
                perfilMaquiagem.setFaixaPreco(rs.getString("FX_PRECO"));
                perfilMaquiagem.setData(rs.getTimestamp("DT_PERFIL").toLocalDateTime());
                lista.add(perfilMaquiagem);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar os Perfis de Maquiagem.", e);
        }

        return lista;
    }

    public PerfilMaquiagem buscar(long id) {
        String sql = """
                SELECT ID_PERFIL_MAQUIAGEM, ID_USUARIO, TP_PELE, TM_PELE,
                       SB_PELE, NS_PELE, PD_UTILIZADO, TN_UTILIZADA,
                       DF_BASE, DF_CORRETIVO, CB_DESEJADA, AC_PREFERIDO,
                       FX_PRECO, DT_PERFIL
                FROM T_EM_PERFIL_MAQUIAGEM
                WHERE ID_PERFIL_MAQUIAGEM = ?
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PerfilMaquiagem perfilMaquiagem = new PerfilMaquiagem();

                    perfilMaquiagem.setId(rs.getLong("ID_PERFIL_MAQUIAGEM"));
                    long idUsuario = rs.getLong("ID_USUARIO");
                    if (!rs.wasNull()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(idUsuario);
                        perfilMaquiagem.setUsuario(usuario);
                    }
                    perfilMaquiagem.setTipopele(rs.getString("TP_PELE"));
                    perfilMaquiagem.setTomPele(rs.getString("TM_PELE"));
                    perfilMaquiagem.setSubtomPele(rs.getString("SB_PELE"));
                    perfilMaquiagem.setNecessidadePele(rs.getString("NS_PELE"));
                    perfilMaquiagem.setProdutoUtilizado(rs.getString("PD_UTILIZADO"));
                    perfilMaquiagem.setTonalidadeUtilizada(rs.getString("TN_UTILIZADA"));
                    perfilMaquiagem.setDificuldadeBase(rs.getString("DF_BASE"));
                    perfilMaquiagem.setDificuldadeCorretivo(rs.getString("DF_CORRETIVO"));
                    perfilMaquiagem.setCoberturaDesejada(rs.getString("CB_DESEJADA"));
                    perfilMaquiagem.setAcabamentoPreferido(rs.getString("AC_PREFERIDO"));
                    perfilMaquiagem.setFaixaPreco(rs.getString("FX_PRECO"));
                    perfilMaquiagem.setData(rs.getTimestamp("DT_PERFIL").toLocalDateTime());

                    return perfilMaquiagem;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar o Perfil de Maquiagem.", e);
        }

        throw new EntidadeNaoEncontradaException("Perfil de Maquiagem não encontrado.");
    }

    public void inserir(PerfilMaquiagem perfilMaquiagem) {
        String sql = """
                INSERT INTO T_EM_PERFIL_MAQUIAGEM (
                    ID_PERFIL_MAQUIAGEM, ID_USUARIO, TP_PELE, TM_PELE,
                    SB_PELE, NS_PELE, PD_UTILIZADO, TN_UTILIZADA,
                    DF_BASE, DF_CORRETIVO, CB_DESEJADA, AC_PREFERIDO,
                    FX_PRECO, DT_PERFIL
                ) VALUES (
                    SQ_PERFIL_MAQUIAGEM.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                )
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, new String[]{"ID_PERFIL_MAQUIAGEM"})) {

            stmt.setLong(1, perfilMaquiagem.getUsuario().getId());
            stmt.setString(2, perfilMaquiagem.getTipopele());
            stmt.setString(3, perfilMaquiagem.getTomPele());
            stmt.setString(4, perfilMaquiagem.getSubtomPele());
            stmt.setString(5, perfilMaquiagem.getNecessidadePele());
            stmt.setString(6, perfilMaquiagem.getProdutoUtilizado());
            stmt.setString(7, perfilMaquiagem.getTonalidadeUtilizada());
            stmt.setString(8, perfilMaquiagem.getDificuldadeBase());
            stmt.setString(9, perfilMaquiagem.getDificuldadeCorretivo());
            stmt.setString(10, perfilMaquiagem.getCoberturaDesejada());
            stmt.setString(11, perfilMaquiagem.getAcabamentoPreferido());
            stmt.setString(12, perfilMaquiagem.getFaixaPreco());
            stmt.setTimestamp(13, Timestamp.valueOf(perfilMaquiagem.getData()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    perfilMaquiagem.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir o Perfil de Maquiagem.", e);
        }
    }

    public void atualizar(PerfilMaquiagem perfilMaquiagem) {
        String sql = """
                UPDATE T_EM_PERFIL_MAQUIAGEM SET
                    ID_USUARIO = ?,
                    TP_PELE = ?,
                    TM_PELE = ?,
                    SB_PELE = ?,
                    NS_PELE = ?,
                    PD_UTILIZADO = ?,
                    TN_UTILIZADA = ?,
                    DF_BASE = ?,
                    DF_CORRETIVO = ?,
                    CB_DESEJADA = ?,
                    AC_PREFERIDO = ?,
                    FX_PRECO = ?,
                    DT_PERFIL = ?
                WHERE ID_PERFIL_MAQUIAGEM = ?
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, perfilMaquiagem.getUsuario().getId());
            stmt.setString(2, perfilMaquiagem.getTipopele());
            stmt.setString(3, perfilMaquiagem.getTomPele());
            stmt.setString(4, perfilMaquiagem.getSubtomPele());
            stmt.setString(5, perfilMaquiagem.getNecessidadePele());
            stmt.setString(6, perfilMaquiagem.getProdutoUtilizado());
            stmt.setString(7, perfilMaquiagem.getTonalidadeUtilizada());
            stmt.setString(8, perfilMaquiagem.getDificuldadeBase());
            stmt.setString(9, perfilMaquiagem.getDificuldadeCorretivo());
            stmt.setString(10, perfilMaquiagem.getCoberturaDesejada());
            stmt.setString(11, perfilMaquiagem.getAcabamentoPreferido());
            stmt.setString(12, perfilMaquiagem.getFaixaPreco());
            stmt.setTimestamp(13, Timestamp.valueOf(perfilMaquiagem.getData()));
            stmt.setLong(14, perfilMaquiagem.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Perfil de Maquiagem não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar o Perfil de Maquiagem.", e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_PERFIL_MAQUIAGEM WHERE ID_PERFIL_MAQUIAGEM = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Perfil de Maquiagem não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar o Perfil de Maquiagem.", e);
        }
    }
}