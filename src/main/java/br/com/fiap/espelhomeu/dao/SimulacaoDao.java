package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SimulacaoDao {

    private final DataSource dataSource;

    public SimulacaoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Simulacao> listar() {
        List<Simulacao> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_SIMULACAO";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Simulacao simulacao = new Simulacao();

                simulacao.setId(resultSet.getLong("ID_SIMULACAO"));

                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("ID_USUARIO"));
                simulacao.setUsuario(usuario);

                long idCorte = resultSet.getLong("ID_CORTE");
                if (!resultSet.wasNull()) {
                    Corte corte = new Corte();
                    corte.setId(idCorte);
                    simulacao.setCorte(corte);
                }

                long idCor = resultSet.getLong("ID_COR");
                if (!resultSet.wasNull()) {
                    CorCabelo corCabelo = new CorCabelo();
                    corCabelo.setId(idCor);
                    simulacao.setCorCabelo(corCabelo);
                }

                long idPenteado = resultSet.getLong("ID_PENTEADO");
                if (!resultSet.wasNull()) {
                    Penteado penteado = new Penteado();
                    penteado.setId(idPenteado);
                    simulacao.setPenteado(penteado);
                }

                simulacao.setImagem(resultSet.getBytes("FT_SIMULACAO"));
                simulacao.setData(resultSet.getTimestamp("DT_SIMULACAO").toLocalDateTime());

                lista.add(simulacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar as simulacoes",e);
        }

        return lista;
    }

    public Simulacao buscar(long id) {
        String sql = "SELECT * FROM T_EM_SIMULACAO WHERE ID_SIMULACAO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Simulacao simulacao = new Simulacao();

                    simulacao.setId(resultSet.getLong("ID_SIMULACAO"));

                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getLong("ID_USUARIO"));
                    simulacao.setUsuario(usuario);

                    long idCorte = resultSet.getLong("ID_CORTE");
                    if (!resultSet.wasNull()) {
                        Corte corte = new Corte();
                        corte.setId(idCorte);
                        simulacao.setCorte(corte);
                    }

                    long idCor = resultSet.getLong("ID_COR");
                    if (!resultSet.wasNull()) {
                        CorCabelo corCabelo = new CorCabelo();
                        corCabelo.setId(idCor);
                        simulacao.setCorCabelo(corCabelo);
                    }

                    long idPenteado = resultSet.getLong("ID_PENTEADO");
                    if (!resultSet.wasNull()) {
                        Penteado penteado = new Penteado();
                        penteado.setId(idPenteado);
                        simulacao.setPenteado(penteado);
                    }

                    simulacao.setImagem(resultSet.getBytes("FT_SIMULACAO"));
                    simulacao.setData(resultSet.getTimestamp("DT_SIMULACAO").toLocalDateTime());

                    return simulacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar a simulacao",e);
        }

        throw new EntidadeNaoEncontradaException("Simulação não encontrada.");
    }

    public void inserir(Simulacao simulacao) {
        String sql = "INSERT INTO T_EM_SIMULACAO " +
                "(ID_SIMULACAO, ID_USUARIO, ID_CORTE, ID_COR, ID_PENTEADO, FT_SIMULACAO, DT_SIMULACAO) " +
                "VALUES (SQ_SIMULACAO.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID_SIMULACAO"})) {

            statement.setLong(1, simulacao.getUsuario().getId());

            if (simulacao.getCorte() != null) {
                statement.setLong(2, simulacao.getCorte().getId());
            } else {
                statement.setNull(2, Types.NUMERIC);
            }

            if (simulacao.getCorCabelo() != null) {
                statement.setLong(3, simulacao.getCorCabelo().getId());
            } else {
                statement.setNull(3, Types.NUMERIC);
            }

            if (simulacao.getPenteado() != null) {
                statement.setLong(4, simulacao.getPenteado().getId());
            } else {
                statement.setNull(4, Types.NUMERIC);
            }

            statement.setBytes(5, simulacao.getImagem());
            statement.setTimestamp(6, Timestamp.valueOf(simulacao.getData()));

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    simulacao.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir a simulacao",e);
        }
    }

    public void atualizar(Simulacao simulacao) {
        String sql = "UPDATE T_EM_SIMULACAO SET " +
                "ID_USUARIO = ?, ID_CORTE = ?, ID_COR = ?, ID_PENTEADO = ?, " +
                "FT_SIMULACAO = ?, DT_SIMULACAO = ? " +
                "WHERE ID_SIMULACAO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, simulacao.getUsuario().getId());

            if (simulacao.getCorte() != null) {
                statement.setLong(2, simulacao.getCorte().getId());
            } else {
                statement.setNull(2, Types.NUMERIC);
            }

            if (simulacao.getCorCabelo() != null) {
                statement.setLong(3, simulacao.getCorCabelo().getId());
            } else {
                statement.setNull(3, Types.NUMERIC);
            }

            if (simulacao.getPenteado() != null) {
                statement.setLong(4, simulacao.getPenteado().getId());
            } else {
                statement.setNull(4, Types.NUMERIC);
            }

            statement.setBytes(5, simulacao.getImagem());
            statement.setTimestamp(6, Timestamp.valueOf(simulacao.getData()));
            statement.setLong(7, simulacao.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Simulação não encontrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar a simulacao",e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_SIMULACAO WHERE ID_SIMULACAO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Simulação não encontrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar a simulacao",e);
        }
    }
}