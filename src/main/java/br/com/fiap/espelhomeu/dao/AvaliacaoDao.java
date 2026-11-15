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
public class AvaliacaoDao {

    private final DataSource dataSource;

    public AvaliacaoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Avaliacao> listar() {
        List<Avaliacao> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_AVALIACAO";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Avaliacao avaliacao = new Avaliacao();

                avaliacao.setId(resultSet.getLong("ID_AVALIACAO"));

                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("ID_USUARIO"));
                avaliacao.setUsuario(usuario);

                long idRecomendacaoCor = resultSet.getLong("ID_RECOMENDACAO_COR");
                if (!resultSet.wasNull()) {
                    RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
                    recomendacaoCor.setId(idRecomendacaoCor);
                    avaliacao.setRecomendacaoCor(recomendacaoCor);
                }

                long idRecomendacaoCorte = resultSet.getLong("ID_RECOMENDACAO_CORTE");
                if (!resultSet.wasNull()) {
                    RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
                    recomendacaoCorte.setId(idRecomendacaoCorte);
                    avaliacao.setRecomendacaoCorte(recomendacaoCorte);
                }

                long idRecomendacaoPenteado = resultSet.getLong("ID_RECOMENDACAO_PENTEADO");
                if (!resultSet.wasNull()) {
                    RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
                    recomendacaoPenteado.setId(idRecomendacaoPenteado);
                    avaliacao.setRecomendacaoPenteado(recomendacaoPenteado);
                }

                long idRecomendacaoMaquiagem = resultSet.getLong("ID_RECOMENDACAO_MAQUIAGEM");
                if (!resultSet.wasNull()) {
                    RecomendacaoMaquiagem recomendacaoMaquiagem = new RecomendacaoMaquiagem();
                    recomendacaoMaquiagem.setId(idRecomendacaoMaquiagem);
                    avaliacao.setRecomendacaoMaquiagem(recomendacaoMaquiagem);
                }

                avaliacao.setNota(resultSet.getDouble("NT_AVALIACAO"));
                avaliacao.setMensagem(resultSet.getString("MS_AVALIACAO"));
                avaliacao.setData(resultSet.getTimestamp("DT_AVALIACAO").toLocalDateTime());

                lista.add(avaliacao);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar as avaliacoes",e);
        }

        return lista;
    }

    public Avaliacao buscar(long id) {
        String sql = "SELECT * FROM T_EM_AVALIACAO WHERE ID_AVALIACAO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Avaliacao avaliacao = new Avaliacao();

                    avaliacao.setId(resultSet.getLong("ID_AVALIACAO"));

                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getLong("ID_USUARIO"));
                    avaliacao.setUsuario(usuario);

                    long idRecomendacaoCor = resultSet.getLong("ID_RECOMENDACAO_COR");
                    if (!resultSet.wasNull()) {
                        RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
                        recomendacaoCor.setId(idRecomendacaoCor);
                        avaliacao.setRecomendacaoCor(recomendacaoCor);
                    }

                    long idRecomendacaoCorte = resultSet.getLong("ID_RECOMENDACAO_CORTE");
                    if (!resultSet.wasNull()) {
                        RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
                        recomendacaoCorte.setId(idRecomendacaoCorte);
                        avaliacao.setRecomendacaoCorte(recomendacaoCorte);
                    }

                    long idRecomendacaoPenteado = resultSet.getLong("ID_RECOMENDACAO_PENTEADO");
                    if (!resultSet.wasNull()) {
                        RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
                        recomendacaoPenteado.setId(idRecomendacaoPenteado);
                        avaliacao.setRecomendacaoPenteado(recomendacaoPenteado);
                    }

                    long idRecomendacaoMaquiagem = resultSet.getLong("ID_RECOMENDACAO_MAQUIAGEM");
                    if (!resultSet.wasNull()) {
                        RecomendacaoMaquiagem recomendacaoMaquiagem = new RecomendacaoMaquiagem();
                        recomendacaoMaquiagem.setId(idRecomendacaoMaquiagem);
                        avaliacao.setRecomendacaoMaquiagem(recomendacaoMaquiagem);
                    }

                    avaliacao.setNota(resultSet.getDouble("NT_AVALIACAO"));
                    avaliacao.setMensagem(resultSet.getString("MS_AVALIACAO"));
                    avaliacao.setData(resultSet.getTimestamp("DT_AVALIACAO").toLocalDateTime());

                    return avaliacao;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar a avaliacao",e);
        }

        throw new EntidadeNaoEncontradaException("Avaliação não encontrada.");
    }

    public void inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO T_EM_AVALIACAO " +
                "(ID_AVALIACAO, ID_USUARIO, ID_RECOMENDACAO_COR, ID_RECOMENDACAO_CORTE, " +
                "ID_RECOMENDACAO_PENTEADO, ID_RECOMENDACAO_MAQUIAGEM, NT_AVALIACAO, " +
                "MS_AVALIACAO, DT_AVALIACAO) " +
                "VALUES (SQ_AVALIACAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID_AVALIACAO"})) {

            statement.setLong(1, avaliacao.getUsuario().getId());

            if (avaliacao.getRecomendacaoCor() != null) {
                statement.setLong(2, avaliacao.getRecomendacaoCor().getId());
            } else {
                statement.setNull(2, Types.NUMERIC);
            }

            if (avaliacao.getRecomendacaoCorte() != null) {
                statement.setLong(3, avaliacao.getRecomendacaoCorte().getId());
            } else {
                statement.setNull(3, Types.NUMERIC);
            }

            if (avaliacao.getRecomendacaoPenteado() != null) {
                statement.setLong(4, avaliacao.getRecomendacaoPenteado().getId());
            } else {
                statement.setNull(4, Types.NUMERIC);
            }

            if (avaliacao.getRecomendacaoMaquiagem() != null) {
                statement.setLong(5, avaliacao.getRecomendacaoMaquiagem().getId());
            } else {
                statement.setNull(5, Types.NUMERIC);
            }

            statement.setDouble(6, avaliacao.getNota());
            statement.setString(7, avaliacao.getMensagem());
            statement.setTimestamp(8, Timestamp.valueOf(avaliacao.getData()));

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    avaliacao.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir a avaliacao",e);
        }
    }

    public void atualizar(Avaliacao avaliacao) {
        String sql = "UPDATE T_EM_AVALIACAO SET " +
                "ID_USUARIO = ?, ID_RECOMENDACAO_COR = ?, ID_RECOMENDACAO_CORTE = ?, " +
                "ID_RECOMENDACAO_PENTEADO = ?, ID_RECOMENDACAO_MAQUIAGEM = ?, " +
                "NT_AVALIACAO = ?, MS_AVALIACAO = ?, DT_AVALIACAO = ? " +
                "WHERE ID_AVALIACAO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, avaliacao.getUsuario().getId());

            if (avaliacao.getRecomendacaoCor() != null) {
                statement.setLong(2, avaliacao.getRecomendacaoCor().getId());
            } else {
                statement.setNull(2, Types.NUMERIC);
            }

            if (avaliacao.getRecomendacaoCorte() != null) {
                statement.setLong(3, avaliacao.getRecomendacaoCorte().getId());
            } else {
                statement.setNull(3, Types.NUMERIC);
            }

            if (avaliacao.getRecomendacaoPenteado() != null) {
                statement.setLong(4, avaliacao.getRecomendacaoPenteado().getId());
            } else {
                statement.setNull(4, Types.NUMERIC);
            }

            if (avaliacao.getRecomendacaoMaquiagem() != null) {
                statement.setLong(5, avaliacao.getRecomendacaoMaquiagem().getId());
            } else {
                statement.setNull(5, Types.NUMERIC);
            }

            statement.setDouble(6, avaliacao.getNota());
            statement.setString(7, avaliacao.getMensagem());
            statement.setTimestamp(8, Timestamp.valueOf(avaliacao.getData()));
            statement.setLong(9, avaliacao.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Avaliação não encontrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar a avaliacao",e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_AVALIACAO WHERE ID_AVALIACAO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Avaliação não encontrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar a avaliacao",e);
        }
    }
}