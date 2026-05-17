package br.com.erionmaia.service;

import br.com.erionmaia.dao.ContaDAO;
import br.com.erionmaia.model.Conta;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ContaService {

    private final ContaDAO contaDAO;

    public ContaService() {
        this.contaDAO = new ContaDAO();
    }

    public void criarConta(Conta conta) throws SQLException {

        validarConta(conta);

        Conta contaExistente = contaDAO.buscarPorNumeroConta(
                conta.getNumerConta()
        );

        if (contaExistente != null) {
            throw new IllegalArgumentException("Já existe uma conta com esse número");
        }

        contaDAO.salvar(conta);
    }

    public void atualizarConta(Conta conta) throws SQLException {
        validarConta(conta);

        contaDAO.atualizarConta(conta);
    }

    public Conta buscarPorId(Integer id) throws SQLException {
        return contaDAO.buscarPorId(id);
    }

    public List<Conta> listar() throws SQLException {
        return contaDAO.listar();
    }

    private void validarConta(Conta conta) {
        if (conta.getNomeTitular() == null
            || conta.getNomeTitular().isBlank()) {
            throw new IllegalArgumentException("Nome do titular é obrigatório.");
        }

        if (conta.getNumerConta() == null
                || conta.getNumerConta().isBlank()) {
            throw new IllegalArgumentException("Número da conta é obrigatório.");
        }

        if (conta.getSaldo() == null)
            throw new IllegalArgumentException("Saldo é obrigatório.");

        if (conta.getSaldo().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Saldo não pode ser negativo.");

        if (conta.getStatus() == null
            || conta.getStatus().isBlank())
            throw new IllegalArgumentException("Status é obrigatório.");
    }
}
