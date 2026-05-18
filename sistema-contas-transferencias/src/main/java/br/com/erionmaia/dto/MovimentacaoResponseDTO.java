package br.com.erionmaia.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentacaoResponseDTO {

    private Integer id;
    private Integer contaOrigemId;
    private Integer contaDestinoId;
    private BigDecimal valor;
    private String tipo;
    private LocalDateTime dataHora;
    private String observacao;

    public MovimentacaoResponseDTO(Integer id, Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor, String tipo, LocalDateTime dataHora, String observacao) {
        this.id = id;
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.valor = valor;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
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

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getObservacao() {
        return observacao;
    }
}
