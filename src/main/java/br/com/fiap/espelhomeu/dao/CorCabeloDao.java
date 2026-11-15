package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.CorCabelo;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CorCabeloDao {

    private final DataSource dataSource;

    public CorCabeloDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CorCabelo> listar() {
        List<CorCabelo> lista = new ArrayList<>();

        String sql = """
                SELECT ID_COR, NM_TONALIDADE, RF_VISUAL, NF_MANUTENCAO
                FROM T_EM_COR
                """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CorCabelo cor = new CorCabelo();

                cor.setId(rs.getLong("ID_COR"));
                cor.setNomeTonalidade(rs.getString("NM_TONALIDADE"));
                cor.setImagem(rs.getBytes("RF_VISUAL"));
                cor.setInfotmacaoManutencao(rs.getString("NF_MANUTENCAO"));

                lista.add(cor);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar cores de cabelo", e);
        }

        return lista;
    }

    public CorCabelo buscar(long id) {
        String sql = """
                SELECT ID_COR, NM_TONALIDADE, RF_VISUAL, NF_MANUTENCAO
                FROM T_EM_COR
                WHERE ID_COR = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CorCabelo cor = new CorCabelo();

                    cor.setId(rs.getLong("ID_COR"));
                    cor.setNomeTonalidade(rs.getString("NM_TONALIDADE"));
                    cor.setImagem(rs.getBytes("RF_VISUAL"));
                    cor.setInfotmacaoManutencao(rs.getString("NF_MANUTENCAO"));

                    return cor;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar cor de cabelo", e);
        }

        throw new EntidadeNaoEncontradaException("Cor de cabelo não encontrada");
    }

    public CorCabelo inserir(CorCabelo cor) {
        String sql = """
                INSERT INTO T_EM_COR
                (ID_COR, NM_TONALIDADE, RF_VISUAL, NF_MANUTENCAO)
                VALUES (SQ_COR.NEXTVAL, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_COR"})) {

            stmt.setString(1, cor.getNomeTonalidade());
            stmt.setBytes(2, cor.getImagem());
            stmt.setString(3, cor.getInfotmacaoManutencao());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cor.setId(rs.getLong(1));
                }
            }

            return cor;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir cor de cabelo", e);
        }
    }

    public CorCabelo atualizar(CorCabelo cor) {
        String sql = """
                UPDATE T_EM_COR
                SET NM_TONALIDADE = ?, RF_VISUAL = ?, NF_MANUTENCAO = ?
                WHERE ID_COR = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cor.getNomeTonalidade());
            stmt.setBytes(2, cor.getImagem());
            stmt.setString(3, cor.getInfotmacaoManutencao());
            stmt.setLong(4, cor.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Cor de cabelo não encontrada");
            }

            return cor;

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar cor de cabelo", e);
        }
    }

    public void deletar(long id) {
        String sql = """
                DELETE FROM T_EM_COR
                WHERE ID_COR = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Cor de cabelo não encontrada");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar cor de cabelo", e);
        }
    }
}