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
public class FavoritosDao {

    private final DataSource dataSource;

    public FavoritosDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Favoritos> listar() {
        List<Favoritos> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_EM_FAVORITOS";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Favoritos favoritos = new Favoritos();

                favoritos.setId(resultSet.getLong("ID_FAVORITO"));

                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("ID_USUARIO"));
                favoritos.setUsuario(usuario);

                long idCorte = resultSet.getLong("ID_CORTE");
                if (!resultSet.wasNull()) {
                    Corte corte = new Corte();
                    corte.setId(idCorte);
                    favoritos.setCorte(corte);
                }

                long idMaquiagem = resultSet.getLong("ID_MAQUIAGEM");
                if (!resultSet.wasNull()) {
                    Maquiagem maquiagem = new Maquiagem();
                    maquiagem.setId(idMaquiagem);
                    favoritos.setMaquiagem(maquiagem);
                }

                long idCor = resultSet.getLong("ID_COR");
                if (!resultSet.wasNull()) {
                    CorCabelo corCabelo = new CorCabelo();
                    corCabelo.setId(idCor);
                    favoritos.setCorCabelo(corCabelo);
                }

                long idRecomendacaoCorte = resultSet.getLong("ID_RECOMENDACAO_CORTE");
                if (!resultSet.wasNull()) {
                    RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
                    recomendacaoCorte.setId(idRecomendacaoCorte);
                    favoritos.setRecomendacaoCorte(recomendacaoCorte);
                }

                long idRecomendacaoCor = resultSet.getLong("ID_RECOMENDACAO_COR");
                if (!resultSet.wasNull()) {
                    RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
                    recomendacaoCor.setId(idRecomendacaoCor);
                    favoritos.setRecomendacaoCor(recomendacaoCor);
                }

                long idRecomendacaoPenteado = resultSet.getLong("ID_RECOMENDACAO_PENTEADO");
                if (!resultSet.wasNull()) {
                    RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
                    recomendacaoPenteado.setId(idRecomendacaoPenteado);
                    favoritos.setRecomendacaoPenteado(recomendacaoPenteado);
                }

                long idPenteado = resultSet.getLong("ID_PENTEADO");
                if (!resultSet.wasNull()) {
                    Penteado penteado = new Penteado();
                    penteado.setId(idPenteado);
                    favoritos.setPenteado(penteado);
                }

                long idProduto = resultSet.getLong("ID_PRODUTO");
                if (!resultSet.wasNull()) {
                    Produto produto = new Produto();
                    produto.setId(idProduto);
                    favoritos.setProduto(produto);
                }

                long idRecomendacaoMaquiagem = resultSet.getLong("ID_RECOMENDACAO_MAQUIAGEM");
                if (!resultSet.wasNull()) {
                    RecomendacaoMaquiagem recomendacaoMaquiagem = new RecomendacaoMaquiagem();
                    recomendacaoMaquiagem.setId(idRecomendacaoMaquiagem);
                    favoritos.setRecomendacaoMaquiagem(recomendacaoMaquiagem);
                }

                long idSimulacao = resultSet.getLong("ID_SIMULACAO");
                if (!resultSet.wasNull()) {
                    Simulacao simulacao = new Simulacao();
                    simulacao.setId(idSimulacao);
                    favoritos.setSimulacao(simulacao);
                }

                Timestamp timestamp = resultSet.getTimestamp("DT_FAVORITO");
                if (timestamp != null) {
                    favoritos.setData(timestamp.toLocalDateTime());
                }

                lista.add(favoritos);
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao listar os favoritos",e);
        }

        return lista;
    }

    public Favoritos buscar(long id) {
        String sql = "SELECT * FROM T_EM_FAVORITOS WHERE ID_FAVORITO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Favoritos favoritos = new Favoritos();

                    favoritos.setId(resultSet.getLong("ID_FAVORITO"));

                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getLong("ID_USUARIO"));
                    favoritos.setUsuario(usuario);

                    long idCorte = resultSet.getLong("ID_CORTE");
                    if (!resultSet.wasNull()) {
                        Corte corte = new Corte();
                        corte.setId(idCorte);
                        favoritos.setCorte(corte);
                    }

                    long idMaquiagem = resultSet.getLong("ID_MAQUIAGEM");
                    if (!resultSet.wasNull()) {
                        Maquiagem maquiagem = new Maquiagem();
                        maquiagem.setId(idMaquiagem);
                        favoritos.setMaquiagem(maquiagem);
                    }

                    long idCor = resultSet.getLong("ID_COR");
                    if (!resultSet.wasNull()) {
                        CorCabelo corCabelo = new CorCabelo();
                        corCabelo.setId(idCor);
                        favoritos.setCorCabelo(corCabelo);
                    }

                    long idRecomendacaoCorte = resultSet.getLong("ID_RECOMENDACAO_CORTE");
                    if (!resultSet.wasNull()) {
                        RecomendacaoCorte recomendacaoCorte = new RecomendacaoCorte();
                        recomendacaoCorte.setId(idRecomendacaoCorte);
                        favoritos.setRecomendacaoCorte(recomendacaoCorte);
                    }

                    long idRecomendacaoCor = resultSet.getLong("ID_RECOMENDACAO_COR");
                    if (!resultSet.wasNull()) {
                        RecomendacaoCor recomendacaoCor = new RecomendacaoCor();
                        recomendacaoCor.setId(idRecomendacaoCor);
                        favoritos.setRecomendacaoCor(recomendacaoCor);
                    }

                    long idRecomendacaoPenteado = resultSet.getLong("ID_RECOMENDACAO_PENTEADO");
                    if (!resultSet.wasNull()) {
                        RecomendacaoPenteado recomendacaoPenteado = new RecomendacaoPenteado();
                        recomendacaoPenteado.setId(idRecomendacaoPenteado);
                        favoritos.setRecomendacaoPenteado(recomendacaoPenteado);
                    }

                    long idPenteado = resultSet.getLong("ID_PENTEADO");
                    if (!resultSet.wasNull()) {
                        Penteado penteado = new Penteado();
                        penteado.setId(idPenteado);
                        favoritos.setPenteado(penteado);
                    }

                    long idProduto = resultSet.getLong("ID_PRODUTO");
                    if (!resultSet.wasNull()) {
                        Produto produto = new Produto();
                        produto.setId(idProduto);
                        favoritos.setProduto(produto);
                    }

                    long idRecomendacaoMaquiagem = resultSet.getLong("ID_RECOMENDACAO_MAQUIAGEM");
                    if (!resultSet.wasNull()) {
                        RecomendacaoMaquiagem recomendacaoMaquiagem = new RecomendacaoMaquiagem();
                        recomendacaoMaquiagem.setId(idRecomendacaoMaquiagem);
                        favoritos.setRecomendacaoMaquiagem(recomendacaoMaquiagem);
                    }

                    long idSimulacao = resultSet.getLong("ID_SIMULACAO");
                    if (!resultSet.wasNull()) {
                        Simulacao simulacao = new Simulacao();
                        simulacao.setId(idSimulacao);
                        favoritos.setSimulacao(simulacao);
                    }

                    Timestamp timestamp = resultSet.getTimestamp("DT_FAVORITO");
                    if (timestamp != null) {
                        favoritos.setData(timestamp.toLocalDateTime());
                    }

                    return favoritos;
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao buscar o favorito",e);
        }

        throw new EntidadeNaoEncontradaException("Favorito não encontrado.");
    }

    public void inserir(Favoritos favoritos) {
        String sql = "INSERT INTO T_EM_FAVORITOS " +
                "(ID_FAVORITO, ID_USUARIO, ID_CORTE, ID_MAQUIAGEM, ID_COR, " +
                "ID_RECOMENDACAO_CORTE, ID_RECOMENDACAO_COR, ID_RECOMENDACAO_PENTEADO, " +
                "ID_PENTEADO, ID_PRODUTO, ID_RECOMENDACAO_MAQUIAGEM, ID_SIMULACAO, DT_FAVORITO) " +
                "VALUES (SQ_FAVORITOS.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID_FAVORITO"})) {

            statement.setLong(1, favoritos.getUsuario().getId());

            if (favoritos.getCorte() != null) {
                statement.setLong(2, favoritos.getCorte().getId());
            } else {
                statement.setNull(2, Types.NUMERIC);
            }

            if (favoritos.getMaquiagem() != null) {
                statement.setLong(3, favoritos.getMaquiagem().getId());
            } else {
                statement.setNull(3, Types.NUMERIC);
            }

            if (favoritos.getCorCabelo() != null) {
                statement.setLong(4, favoritos.getCorCabelo().getId());
            } else {
                statement.setNull(4, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoCorte() != null) {
                statement.setLong(5, favoritos.getRecomendacaoCorte().getId());
            } else {
                statement.setNull(5, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoCor() != null) {
                statement.setLong(6, favoritos.getRecomendacaoCor().getId());
            } else {
                statement.setNull(6, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoPenteado() != null) {
                statement.setLong(7, favoritos.getRecomendacaoPenteado().getId());
            } else {
                statement.setNull(7, Types.NUMERIC);
            }

            if (favoritos.getPenteado() != null) {
                statement.setLong(8, favoritos.getPenteado().getId());
            } else {
                statement.setNull(8, Types.NUMERIC);
            }

            if (favoritos.getProduto() != null) {
                statement.setLong(9, favoritos.getProduto().getId());
            } else {
                statement.setNull(9, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoMaquiagem() != null) {
                statement.setLong(10, favoritos.getRecomendacaoMaquiagem().getId());
            } else {
                statement.setNull(10, Types.NUMERIC);
            }

            if (favoritos.getSimulacao() != null) {
                statement.setLong(11, favoritos.getSimulacao().getId());
            } else {
                statement.setNull(11, Types.NUMERIC);
            }

            if (favoritos.getData() != null) {
                statement.setTimestamp(12, Timestamp.valueOf(favoritos.getData()));
            } else {
                statement.setNull(12, Types.TIMESTAMP);
            }

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    favoritos.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao inserir o favorito",e);
        }
    }

    public void atualizar(Favoritos favoritos) {
        String sql = "UPDATE T_EM_FAVORITOS SET " +
                "ID_USUARIO = ?, ID_CORTE = ?, ID_MAQUIAGEM = ?, ID_COR = ?, " +
                "ID_RECOMENDACAO_CORTE = ?, ID_RECOMENDACAO_COR = ?, " +
                "ID_RECOMENDACAO_PENTEADO = ?, ID_PENTEADO = ?, ID_PRODUTO = ?, " +
                "ID_RECOMENDACAO_MAQUIAGEM = ?, ID_SIMULACAO = ?, DT_FAVORITO = ? " +
                "WHERE ID_FAVORITO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, favoritos.getUsuario().getId());

            if (favoritos.getCorte() != null) {
                statement.setLong(2, favoritos.getCorte().getId());
            } else {
                statement.setNull(2, Types.NUMERIC);
            }

            if (favoritos.getMaquiagem() != null) {
                statement.setLong(3, favoritos.getMaquiagem().getId());
            } else {
                statement.setNull(3, Types.NUMERIC);
            }

            if (favoritos.getCorCabelo() != null) {
                statement.setLong(4, favoritos.getCorCabelo().getId());
            } else {
                statement.setNull(4, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoCorte() != null) {
                statement.setLong(5, favoritos.getRecomendacaoCorte().getId());
            } else {
                statement.setNull(5, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoCor() != null) {
                statement.setLong(6, favoritos.getRecomendacaoCor().getId());
            } else {
                statement.setNull(6, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoPenteado() != null) {
                statement.setLong(7, favoritos.getRecomendacaoPenteado().getId());
            } else {
                statement.setNull(7, Types.NUMERIC);
            }

            if (favoritos.getPenteado() != null) {
                statement.setLong(8, favoritos.getPenteado().getId());
            } else {
                statement.setNull(8, Types.NUMERIC);
            }

            if (favoritos.getProduto() != null) {
                statement.setLong(9, favoritos.getProduto().getId());
            } else {
                statement.setNull(9, Types.NUMERIC);
            }

            if (favoritos.getRecomendacaoMaquiagem() != null) {
                statement.setLong(10, favoritos.getRecomendacaoMaquiagem().getId());
            } else {
                statement.setNull(10, Types.NUMERIC);
            }

            if (favoritos.getSimulacao() != null) {
                statement.setLong(11, favoritos.getSimulacao().getId());
            } else {
                statement.setNull(11, Types.NUMERIC);
            }

            if (favoritos.getData() != null) {
                statement.setTimestamp(12, Timestamp.valueOf(favoritos.getData()));
            } else {
                statement.setNull(12, Types.TIMESTAMP);
            }

            statement.setLong(13, favoritos.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Favorito não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao atualizar oo favorito",e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM T_EM_FAVORITOS WHERE ID_FAVORITO = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontradaException("Favorito não encontrado.");
            }

        } catch (SQLException e) {
            throw new ErroBancoDadosException("Erro ao deletar o favorito",e);
        }
    }
}