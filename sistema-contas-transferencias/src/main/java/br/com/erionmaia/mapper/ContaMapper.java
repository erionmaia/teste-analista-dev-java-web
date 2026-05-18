package br.com.erionmaia.mapper;

import br.com.erionmaia.dto.ContaRequestDTO;
import br.com.erionmaia.dto.ContaResponseDTO;
import br.com.erionmaia.model.Conta;

public class ContaMapper {

    private ContaMapper() {
    }

    public static Conta toEntity(Integer id, ContaRequestDTO dto) {
        return new Conta(
                id,
                dto.getNomeTitular(),
                dto.getNumeroConta(),
                dto.getSaldo(),
                dto.getStatus(),
                null
        );
    }

    public static ContaResponseDTO toResponseDTO(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getNomeTitular(),
                conta.getNumeroConta(),
                conta.getSaldo(),
                conta.getStatus(),
                conta.getDataCriacao()
        );
    }
}