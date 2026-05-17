package br.com.erionmaia.dao;

import br.com.erionmaia.model.Movimentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDAO {

    public void salvar(Movimentacao movimentacao, Connection connection) throws SQLException {

        String sql =
                "INSERT INTO movimentacao " +
                "(conta_origem_id, conta_destino_id, valor, tipo, observacao) " +
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
}
