package br.com.erionmaia.service;

import br.com.erionmaia.dao.ContaDAO;
import br.com.erionmaia.dao.MovimentacaoDAO;
import br.com.erionmaia.dto.ContaRequestDTO;
import br.com.erionmaia.dto.ContaResponseDTO;
import br.com.erionmaia.dto.ExtratoContaDTO;
import br.com.erionmaia.dto.MovimentacaoResponseDTO;
import br.com.erionmaia.mapper.ContaMapper;
import br.com.erionmaia.mapper.MovimentacaoMapper;
import br.com.erionmaia.model.Conta;
import br.com.erionmaia.model.Movimentacao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaService {

    private final ContaDAO contaDAO;
    private final MovimentacaoDAO movimentacaoDAO;

    public ContaService() {
        this.contaDAO = new ContaDAO();
    }

    public void criarConta(ContaRequestDTO dto) throws SQLException {

        Conta conta = ContaMapper.toEntity(null, dto);

        validarConta(conta);

        Conta contaExistente = contaDAO.buscarPorNumeroConta(
                conta.getNumeroConta()
        );

        if (contaExistente != null) {
            throw new IllegalArgumentException("Já existe uma conta com esse número");
        }

        contaDAO.salvar(conta);
    }

    public void atualizarConta(Integer id, ContaRequestDTO dto) throws SQLException {

        Conta conta = ContaMapper.toEntity(id, dto);

        validarConta(conta);

        contaDAO.atualizarConta(conta);
    }

    public ContaResponseDTO buscarDTOPorId(Integer id) throws SQLException {
        Conta conta = contaDAO.buscarPorId(id);

        if (conta == null) {
            return null;
        }

        return ContaMapper.toResponseDTO(conta);
    }

    public List<ContaResponseDTO> listarDTO() throws SQLException {
        List<Conta> contas = contaDAO.listar();

        List<ContaResponseDTO> dtos = new ArrayList<>();

        for (Conta conta : contas) {
            dtos.add(ContaMapper.toResponseDTO(conta));
        }

        return dtos;
    }

    public ExtratoContaDTO gerarExtrato(Integer contaId) throws SQLException {

        Conta conta = contaDAO.buscarPorId(contaId);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        List<Movimentacao> movimentacoes = movimentacaoDAO.listarPorConta(contaId, 1, 50);

        List<MovimentacaoResponseDTO> movimentacoesDto = new ArrayList<>();

        for (Movimentacao movimentacao : movimentacoes) {
            movimentacoesDto.add(
                    MovimentacaoMapper.toResponseDTO(movimentacao)
            );
        }

        return new ExtratoContaDTO(
                ContaMapper.toResponseDTO(conta),
                conta.getSaldo(),
                movimentacoesDto
        );
    }

    private void validarConta(Conta conta) {
        if (conta.getNomeTitular() == null
            || conta.getNomeTitular().isBlank()) {
            throw new IllegalArgumentException("Nome do titular é obrigatório.");
        }

        if (conta.getNumeroConta() == null
                || conta.getNumeroConta().isBlank()) {
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
