package br.com.erionmaia.dto;

import java.math.BigDecimal;

public class ContaRequestDTO {

    private String nomeTitular;
    private String numeroConta;
    private BigDecimal saldo;
    private String status;

    public ContaRequestDTO() {
    }

    public ContaRequestDTO(String nomeTitular, String numeroConta, BigDecimal saldo, String status) {
        this.nomeTitular = nomeTitular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.status = status;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getStatus() {
        return status;
    }
}
