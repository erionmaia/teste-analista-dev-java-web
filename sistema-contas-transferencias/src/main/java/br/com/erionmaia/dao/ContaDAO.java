package br.com.erionmaia.dao;

import br.com.erionmaia.model.Conta;
import br.com.erionmaia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public void salvar(Conta conta) throws SQLException {

        String sql =
                "INSERT INTO conta (" +
                    "nome_titular, numero_conta, saldo, status" +
                ") VALUES (?, ?, ?, ?)";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, conta.getNomeTitular());
            stmt.setString(2, conta.getNumeroConta());
            stmt.setBigDecimal(3, conta.getSaldo());
            stmt.setString(4, conta.getStatus());

            stmt.executeUpdate();
        }
    }

    public Conta buscarPorId(Integer id) throws SQLException {

        String sql = "SELECT * FROM conta WHERE id = ? ";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearConta(rs);
                }

                return null;
            }
        }
    }

    public Conta buscarPorIdParaAtualizacao(Integer id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM conta WHERE id = ? ";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearConta(rs);
                }

                return null;
            }
        }
    }

    public Conta buscarPorNumeroConta(String numeroConta) throws SQLException {

        String sql =
                "SELECT * "+
                "FROM conta "+
                "WHERE numero_conta = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, numeroConta);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearConta(rs);
                }

                return null;
            }
        }
    }

    public List<Conta> listar() throws SQLException {

        String sql =
                "SELECT * "+
                "FROM conta "+
                "ORDER BY id;";
        List<Conta> contas = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                contas.add(mapearConta(rs));
            }
        }

        return contas;
    }

    public List<Conta> listarPaginado(int pagina, int tamanho) throws SQLException {

        String sql =
                "SELECT * FROM conta " +
                "ORDER BY id " +
                "LIMIT ? OFFSET ?";

        List<Conta> contas = new ArrayList<>();

        int offset = (pagina - 1) * tamanho;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, tamanho);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contas.add(mapearConta(rs));
                }
            }
        }

        return contas;
    }

    public void atualizarConta(Conta conta) throws SQLException {

        String sql =
                "UPDATE conta " +
                "SET nome_titular = ?, " +
                "numero_conta = ?, " +
                "status = ? " +
                "WHERE id = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, conta.getNomeTitular());
            stmt.setString(2, conta.getNumeroConta());
            stmt.setString(3, conta.getStatus());
            stmt.setInt(4, conta.getId());

            stmt.executeUpdate();
        }
    }

    public void atualizarSaldo(
            Integer contaId,
            java.math.BigDecimal saldo,
            Connection connection) throws SQLException {

        String sql =
                "UPDATE conta " +
                "SET saldo = ? " +
                "WHERE id = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setBigDecimal(1, saldo);
            stmt.setInt(2, contaId);

            stmt.executeUpdate();
        }
    }

    public void deletarConta(Integer contaId) throws SQLException {

        String sql = "DELETE FROM conta WHERE id = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, contaId);

            stmt.executeUpdate();
        }
    }

    public void excluirLogico(Integer id) throws SQLException {

        String sql =
                "UPDATE conta SET status = 'INATIVA' WHERE id = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }

    public int contarTotal() throws SQLException {

        String sql = "SELECT COUNT(*) FROM conta";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            if (rs.next()) {
                return rs.getInt(1);
            }

            return 0;
        }
    }

    private Conta mapearConta(ResultSet rs) throws SQLException {
        return new Conta(
                rs.getInt("id"),
                rs.getString("nome_titular"),
                rs.getString("numero_conta"),
                rs.getBigDecimal("saldo"),
                rs.getString("status"),
                rs.getTimestamp("data_criacao").toLocalDateTime()
        );
    }
}
