package br.com.erionmaia.dao;

import br.com.erionmaia.model.Movimentacao;
import br.com.erionmaia.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {

    public void salvar(Movimentacao movimentacao, Connection connection) throws SQLException {

        String sql =
                "INSERT INTO movimentacao (" +
                "conta_origem_id, conta_destino_id, valor, tipo, observacao) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movimentacao.getContaOrigemId());
            stmt.setInt(2, movimentacao.getContaDestinoId());
            stmt.setBigDecimal(3, movimentacao.getValor());
            stmt.setString(4, movimentacao.getTipo());
            stmt.setString(5, movimentacao.getObservacao());

            stmt.executeUpdate();
        }
    }

    public List<Movimentacao> listarPorConta(Integer contaId, int pagina, int tamanho) throws SQLException {

        String sql =
                "SELECT * FROM movimentacao " +
                "WHERE (? IS NULL OR conta_origem_id = ? OR conta_destino_id = ?) " +
                "ORDER BY data_hora DESC " +
                "LIMIT ? OFFSET ?";

        List<Movimentacao> movimentacoes = new ArrayList<>();

        int offset = (pagina - 1) * tamanho;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ){
            if (contaId != null) {
                stmt.setNull(1, Types.INTEGER);
                stmt.setNull(2, Types.INTEGER);
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(1, contaId);
                stmt.setInt(2, contaId);
                stmt.setInt(3, contaId);
            }

            stmt.setInt(4, tamanho);
            stmt.setInt(5, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    movimentacoes.add(mapearMovimentacao(rs));
                }
            }
        }

        return movimentacoes;
    }

    private Movimentacao mapearMovimentacao(ResultSet rs) throws SQLException {

        return new Movimentacao(
                rs.getInt("id"),
                rs.getInt("conta_origem_id"),
                rs.getInt("conta_destino_id"),
                rs.getBigDecimal("valor"),
                rs.getString("tipo"),
                rs.getTimestamp("data_hora").toLocalDateTime(),
                rs.getString("observacao")
        );
    }
}
