package br.com.erionmaia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movimentacao {

    private Integer id;
    private Integer contaOrigemId;
    private Integer contaDestinoId;
    private BigDecimal valor;
    private String tipo;
    private LocalDateTime dataHora;
    private String observacao;

    public Movimentacao(Integer id, Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor, String tipo, LocalDateTime dataHora, String observacao) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContaOrigemId() {
        return contaOrigemId;
    }

    public void setContaOrigemId(Integer contaOrigemId) {
        this.contaOrigemId = contaOrigemId;
    }

    public Integer getContaDestinoId() {
        return contaDestinoId;
    }

    public void setContaDestinoId(Integer contaDestinoId) {
        this.contaDestinoId = contaDestinoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
