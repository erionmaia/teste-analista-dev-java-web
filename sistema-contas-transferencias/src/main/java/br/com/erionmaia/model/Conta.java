package br.com.erionmaia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Conta {

    private Integer id;
    private String nomeTitular;
    private String numerConta;
    private BigDecimal saldo;
    private String status;
    private LocalDateTime dataCriacao;

    public Conta(Integer id, String nomeTitular, String numerConta, BigDecimal saldo, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.nomeTitular = nomeTitular;
        this.numerConta = numerConta;
        this.saldo = saldo;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtiva() {
        return "ATIVA".equalsIgnoreCase(this.status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumerConta() {
        return numerConta;
    }

    public void setNumerConta(String numerConta) {
        this.numerConta = numerConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void debitar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }

        if (this.saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        this.saldo = this.saldo.add(valor);
    }

    public void creditar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }

        this.saldo = this.saldo.subtract(valor);
    }
}
