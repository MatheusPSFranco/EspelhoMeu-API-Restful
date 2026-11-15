package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.PerfilCabelo;
import br.com.fiap.espelhomeu.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PerfilCabeloDao {
    private final DataSource dataSource;
    public PerfilCabeloDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PerfilCabelo> listar() {
        List<PerfilCabelo> lista = new ArrayList<>();

        String sql = """
                SELECT ID_PERFIL_CABELO, ID_USUARIO, TX_CABELO, FR_ROSTO,
                       CP_ATUAL, CP_DESEJADO, CR_ATUAL, ST_PREFERENCIA,
                       TP_DISPONIVEL, IN_COLORACAO, PF_MUDANCA, FX_PRECO,
                       DT_PERFIL
                FROM T_EM_PERFIL_CABELO
                """;

        try (Connection conexao = dataSource.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PerfilCabelo perfilCabelo = new PerfilCabelo();

                perfilCabelo.setId(rs.getLong("ID_PERFIL_CABELO"));
                long idUsuario = rs.getLong("ID_USUARIO");
                if (!rs.wasNull()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(idUsuario);
                    perfilCabelo.setUsuario(usuario);
                }
                perfilCabelo.setTexturaCabelo(rs.getString("TX_CABELO"));
                perfilCabelo.setFormatoRosto(rs.getString("FR_ROSTO"));
                perfilCabelo.setComprimentoAtual(rs.getString("CP_ATUAL"));
                perfilCabelo.setComprimentoDesejado(rs.getString("CP_DESEJADO"));
                perfilCabelo.setCorAtual(rs.getString("CR_ATUAL"));
                perfilCabelo.setEstiloPreferencia(rs.getString("ST_PREFERENCIA"));
                perfilCabelo.setTempoDisponivel(rs.getString("TP_DISPONIVEL"));
                perfilCabelo.setInteresseColoracao(rs.getString("IN_COLORACAO"));
                perfilCabelo.setPreferenciaMudanca(rs.getString("PF_MUDANCA"));
                perfilCabelo.setFaixaPreco(rs.getString("FX_PRECO"));
                perfilCabelo.setData(rs.getTimestamp("DT_PERFIL").toLocalDateTime());
                lista.add(perfilCabelo);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar os Perfil de Cabelo.", e);
        }

        return lista;
    }

    public PerfilCabelo buscar(long id) {
        String sql = """
                SELECT ID_PERFIL_CABELO, ID_USUARIO, TX_CABELO, FR_ROSTO,
                       CP_ATUAL, CP_DESEJADO, CR_ATUAL, ST_PREFERENCIA,
                       TP_DISPONIVEL, IN_COLORACAO, PF_MUDANCA, FX_PRECO,
                       DT_PERFIL
                FROM T_EM_PERFIL_CABELO
                WHERE ID_PERFIL_CABELO = ?
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PerfilCabelo perfilCabelo = new PerfilCabelo();

                    perfilCabelo.setId(rs.getLong("ID_PERFIL_CABELO"));
                    long idUsuario = rs.getLong("ID_USUARIO");
                    if (!rs.wasNull()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(idUsuario);
                        perfilCabelo.setUsuario(usuario);
                    }
                    perfilCabelo.setTexturaCabelo(rs.getString("TX_CABELO"));
                    perfilCabelo.setFormatoRosto(rs.getString("FR_ROSTO"));
                    perfilCabelo.setComprimentoAtual(rs.getString("CP_ATUAL"));
                    perfilCabelo.setComprimentoDesejado(rs.getString("CP_DESEJADO"));
                    perfilCabelo.setCorAtual(rs.getString("CR_ATUAL"));
                    perfilCabelo.setEstiloPreferencia(rs.getString("ST_PREFERENCIA"));
                    perfilCabelo.setTempoDisponivel(rs.getString("TP_DISPONIVEL"));
                    perfilCabelo.setInteresseColoracao(rs.getString("IN_COLORACAO"));
                    perfilCabelo.setPreferenciaMudanca(rs.getString("PF_MUDANCA"));
                    perfilCabelo.setFaixaPreco(rs.getString("FX_PRECO"));
                    perfilCabelo.setData(rs.getTimestamp("DT_PERFIL").toLocalDateTime());

                    return perfilCabelo;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar o Perfil de Cabelo.", e);
        }

        throw new EntidadeNaoEncontradaException("Perfil de Cabelo não encontrado.");
    }

    public void inserir(PerfilCabelo perfilCabelo) {
        String sql = """
                INSERT INTO T_EM_PERFIL_CABELO (
                    ID_PERFIL_CABELO, ID_USUARIO, TX_CABELO, FR_ROSTO,
                    CP_ATUAL, CP_DESEJADO, CR_ATUAL, ST_PREFERENCIA,
                    TP_DISPONIVEL, IN_COLORACAO, PF_MUDANCA, FX_PRECO,
                    DT_PERFIL
                ) VALUES (
                    SQ_PERFIL_CABELO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                )
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, new String[]{"ID_PERFIL_CABELO"})) {

            stmt.setLong(1, perfilCabelo.getUsuario().getId());
            stmt.setString(2, perfilCabelo.getTexturaCabelo());
            stmt.setString(3, perfilCabelo.getFormatoRosto());
            stmt.setString(4, perfilCabelo.getComprimentoAtual());
            stmt.setString(5, perfilCabelo.getComprimentoDesejado());
            stmt.setString(6, perfilCabelo.getCorAtual());
            stmt.setString(7, perfilCabelo.getEstiloPreferencia());
            stmt.setString(8, perfilCabelo.getTempoDisponivel());
            stmt.setString(9, perfilCabelo.getInteresseColoracao());
            stmt.setString(10, perfilCabelo.getPreferenciaMudanca());
            stmt.setString(11, perfilCabelo.getFaixaPreco());
            stmt.setTimestamp(12, Timestamp.valueOf(perfilCabelo.getData()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    perfilCabelo.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir o Perfil de Cabelo.", e);
        }
    }

    public void atualizar(PerfilCabelo perfilCabelo) {
        String sql = """
                UPDATE T_EM_PERFIL_CABELO SET
                    ID_USUARIO = ?,
                    TX_CABELO = ?,
                    FR_ROSTO = ?,
                    CP_ATUAL = ?,
                    CP_DESEJADO = ?,
                    CR_ATUAL = ?,
                    ST_PREFERENCIA = ?,
                    TP_DISPONIVEL = ?,
                    IN_COLORACAO = ?,
                    PF_MUDANCA = ?,
                    FX_PRECO = ?,
                    DT_PERFIL = ?
                    
                WHERE ID_PERFIL_CABELO = ?
                """;

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, perfilCabelo.getUsuario().getId());
            stmt.setString(2, perfilCabelo.getTexturaCabelo());
            stmt.setString(3, perfilCabelo.getFormatoRosto());
            stmt.setString(4, perfilCabelo.getComprimentoAtual());
            stmt.setString(5, perfilCabelo.getComprimentoDesejado());
            stmt.setString(6, perfilCabelo.getCorAtual());
            stmt.setString(7, perfilCabelo.getEstiloPreferencia());
            stmt.setString(8, perfilCabelo.getTempoDisponivel());
            stmt.setString(9, perfilCabelo.getInteresseColoracao());
            stmt.setString(10, perfilCabelo.getPreferenciaMudanca());
            stmt.setString(11, perfilCabelo.getFaixaPreco());
            stmt.setTimestamp(12, Timestamp.valueOf(perfilCabelo.getData()));
            stmt.setLong(13, perfilCabelo.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Perfil de Cabelo não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar o Perfil de Cabelo.", e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_PERFIL_CABELO WHERE ID_PERFIL_CABELO = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Perfil de Cabelo não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar o Perfil de Cabelo.", e);
        }
    }
}
