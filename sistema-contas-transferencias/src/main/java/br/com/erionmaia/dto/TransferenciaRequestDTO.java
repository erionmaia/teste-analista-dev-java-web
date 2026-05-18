package br.com.erionmaia.dto;

import java.math.BigDecimal;

public class TransferenciaRequestDTO {

    private Integer contaOrigemId;
    private Integer contaDestinoId;
    private BigDecimal valor;

    public TransferenciaRequestDTO(Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor) {
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.valor = valor;
    }

    public Integer getContaOrigemId() {
        return contaOrigemId;
    }

    public Integer getContaDestinoId() {
        return contaDestinoId;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
