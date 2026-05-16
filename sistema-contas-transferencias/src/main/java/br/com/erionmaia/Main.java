package br.com.erionmaia;

import br.com.erionmaia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        String sql = "SELECT * FROM conta";

        try (
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        )
        {
            if (connection != null) {
                System.out.println("Conexão com PostgreSQL realizada com sucesso!");

                while(resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Titular: " + resultSet.getString("nome_titular"));
                    System.out.println("Saldo: " + resultSet.getBigDecimal("saldo"));
                    System.out.println("--------------------------");
                }
            } else {
                System.out.println("Falha ao conectar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}