package br.com.erionmaia.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaResponseDTO {

    private Integer id;
    private String nomeTitular;
    private String numeroConta;
    private BigDecimal saldo;
    private String status;
    private LocalDateTime dataCriacao;

    public ContaResponseDTO(Integer id, String nomeTitular, String numeroConta, BigDecimal saldo, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.nomeTitular = nomeTitular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
