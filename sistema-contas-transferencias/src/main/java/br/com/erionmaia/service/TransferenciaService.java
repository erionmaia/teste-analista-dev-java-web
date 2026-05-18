package br.com.erionmaia.service;

import br.com.erionmaia.dao.ContaDAO;
import br.com.erionmaia.dao.MovimentacaoDAO;
import br.com.erionmaia.model.Conta;
import br.com.erionmaia.model.Movimentacao;
import br.com.erionmaia.util.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;

public class TransferenciaService {

    private final ContaDAO contaDAO;
    private final MovimentacaoDAO movimentacaoDAO;

    public TransferenciaService() {
        contaDAO = new ContaDAO();
        movimentacaoDAO = new MovimentacaoDAO();
    }

    public void transferir(Integer contaOrigemId,
                           Integer contaDestinoId,
                           BigDecimal valor) throws Exception {

        validarDadosBasicos(contaOrigemId, contaDestinoId, valor);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            Conta contaOrigem = contaDAO.buscarPorIdParaAtualizacao(contaOrigemId, connection);
            Conta contaDestino = contaDAO.buscarPorIdParaAtualizacao(contaDestinoId, connection);

            validarContas(contaOrigem, contaDestino, valor);

            contaOrigem.debitar(valor);
            contaDestino.creditar(valor);

            contaDAO.atualizarSaldo(contaOrigem.getId(), contaOrigem.getSaldo(), connection);
            contaDAO.atualizarSaldo(contaDestino.getId(), contaDestino.getSaldo(), connection);

            Movimentacao movimentacao = new Movimentacao(
                    null,
                    contaOrigem.getId(),
                    contaDestino.getId(),
                    valor,
                    "TRANSFERENCIA",
                    null,
                    "Transferencia entre contas"
            );

            movimentacaoDAO.salvar(movimentacao, connection);

            connection.commit();
        } catch (Exception e) {
            if (connection != null){
                connection.rollback();
            }

            throw e;
        } finally {
            if (connection != null){
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private void validarDadosBasicos(
            Integer contaOrigemId,
            Integer contaDestinoId,
            BigDecimal valor) throws Exception {

        if (contaOrigemId == null)
            throw new IllegalArgumentException("Conta de origem é obrigatório.");

        if (contaDestinoId == null)
            throw new IllegalArgumentException("Conta de destino é obrigatório.");

        if (contaOrigemId.equals(contaDestinoId))
            throw new IllegalArgumentException("Não é permitido transferir para a mesma conta.");

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor da transferência deve ser maior que zero.");
    }

    private void validarContas(Conta contaOrigem,
                               Conta contaDestino,
                               BigDecimal valor) {
        if (contaOrigem == null)
            throw new IllegalArgumentException("Conta de origem não encontrada.");

        if (contaDestino == null)
            throw new IllegalArgumentException("Conta de destino não encontrada.");

        if (!contaOrigem.isAtiva())
            throw new IllegalArgumentException("Conta de origem está inativa.");

        if (!contaDestino.isAtiva())
            throw new IllegalArgumentException("Conta de destino está inativa.");

        if (contaOrigem.getSaldo().compareTo(valor) < 0)
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
    }
}
