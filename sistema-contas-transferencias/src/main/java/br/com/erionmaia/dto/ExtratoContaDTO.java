package br.com.erionmaia.dto;

import java.math.BigDecimal;
import java.util.List;

public class ExtratoContaDTO {

    private ContaResponseDTO conta;
    private BigDecimal saldoAtual;
    private List<MovimentacaoResponseDTO> movimentacoes;

    public ExtratoContaDTO(ContaResponseDTO conta,
                           BigDecimal saldoAtual,
                           List<MovimentacaoResponseDTO> movimentacoes) {
        this.conta = conta;
        this.saldoAtual = saldoAtual;
        this.movimentacoes = movimentacoes;
    }

    public ContaResponseDTO getConta() {
        return conta;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public List<MovimentacaoResponseDTO> getMovimentacoes() {
        return movimentacoes;
    }
}
