package br.com.fiap.espelhomeu.dao;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.model.ConsultoriaMaquiagem;
import br.com.fiap.espelhomeu.model.Maquiagem;
import br.com.fiap.espelhomeu.model.RecomendacaoMaquiagem;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecomendacaoMaquiagemDao {

    private final DataSource dataSource;

    public RecomendacaoMaquiagemDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RecomendacaoMaquiagem> listar() {
        List<RecomendacaoMaquiagem> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_RECOMENDACAO_MAQUIAGEM";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                RecomendacaoMaquiagem recomendacao = new RecomendacaoMaquiagem();

                recomendacao.setId(resultSet.getLong("ID_RECOMENDACAO_MAQUIAGEM"));

                ConsultoriaMaquiagem consultoriaMaquiagem = new ConsultoriaMaquiagem();
                consultoriaMaquiagem.setId(resultSet.getLong("ID_CONSULTORIA_MAQUIAGEM"));
                recomendacao.setConsultoriaMaquiagem(consultoriaMaquiagem);

                Maquiagem maquiagem = new Maquiagem();
                maquiagem.setId(resultSet.getLong("ID_MAQUIAGEM"));
                recomendacao.setMaquiagem(maquiagem);

                recomendacao.setJustificativa(resultSet.getString("JT_MAQUIAGEM"));

                Timestamp timestamp = resultSet.getTimestamp("DT_MAQUIAGEM");
                if (timestamp != null) {
                    recomendacao.setData(timestamp.toLocalDateTime());
                }

                lista.add(recomendacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar as Recomendacoes de Maquiagem",e);
        }

        return lista;
    }

    public RecomendacaoMaquiagem buscar(long id) {
        String sql = "SELECT * FROM T_EM_RECOMENDACAO_MAQUIAGEM WHERE ID_RECOMENDACAO_MAQUIAGEM = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    RecomendacaoMaquiagem recomendacao = new RecomendacaoMaquiagem();

                    recomendacao.setId(resultSet.getLong("ID_RECOMENDACAO_MAQUIAGEM"));

                    ConsultoriaMaquiagem consultoriaMaquiagem = new ConsultoriaMaquiagem();
                    consultoriaMaquiagem.setId(resultSet.getLong("ID_CONSULTORIA_MAQUIAGEM"));
                    recomendacao.setConsultoriaMaquiagem(consultoriaMaquiagem);

                    Maquiagem maquiagem = new Maquiagem();
                    maquiagem.setId(resultSet.getLong("ID_MAQUIAGEM"));
                    recomendacao.setMaquiagem(maquiagem);

                    recomendacao.setJustificativa(resultSet.getString("JT_MAQUIAGEM"));

                    Timestamp timestamp = resultSet.getTimestamp("DT_MAQUIAGEM");
                    if (timestamp != null) {
                        recomendacao.setData(timestamp.toLocalDateTime());
                    }

                    return recomendacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar a Recomendacao de Maquiagem",e);
        }

        throw new EntidadeNaoEncontradaException("Recomendação de maquiagem não encontrada.");
    }

    public void inserir(RecomendacaoMaquiagem recomendacao) {
        String sql = "INSERT INTO T_EM_RECOMENDACAO_MAQUIAGEM " +
                "(ID_RECOMENDACAO_MAQUIAGEM, ID_CONSULTORIA_MAQUIAGEM, ID_MAQUIAGEM, JT_MAQUIAGEM, DT_MAQUIAGEM) " +
                "VALUES (SQ_RECOMENDACAO_MAQUIAGEM.NEXTVAL, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID_RECOMENDACAO_MAQUIAGEM"})) {

            statement.setLong(1, recomendacao.getConsultoriaMaquiagem().getId());
            statement.setLong(2, recomendacao.getMaquiagem().getId());
            statement.setString(3, recomendacao.getJustificativa());

            if (recomendacao.getData() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(recomendacao.getData()));
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    recomendacao.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir a Recomendacao de Maquiagem",e);
        }
    }

    public void atualizar(RecomendacaoMaquiagem recomendacao) {
        String sql = "UPDATE T_EM_RECOMENDACAO_MAQUIAGEM SET " +
                "ID_CONSULTORIA_MAQUIAGEM = ?, ID_MAQUIAGEM = ?, JT_MAQUIAGEM = ?, DT_MAQUIAGEM = ? " +
                "WHERE ID_RECOMENDACAO_MAQUIAGEM = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, recomendacao.getConsultoriaMaquiagem().getId());
            statement.setLong(2, recomendacao.getMaquiagem().getId());
            statement.setString(3, recomendacao.getJustificativa());

            if (recomendacao.getData() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(recomendacao.getData()));
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }

            statement.setLong(5, recomendacao.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de maquiagem não encontrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar a Recomendacao de Maquiagem",e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_RECOMENDACAO_MAQUIAGEM WHERE ID_RECOMENDACAO_MAQUIAGEM = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Recomendação de maquiagem não encontrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar a Recomendacao de Maquiagem",e);
        }
    }
}