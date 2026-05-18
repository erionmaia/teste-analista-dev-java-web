package br.com.erionmaia.mapper;

import br.com.erionmaia.dto.MovimentacaoResponseDTO;
import br.com.erionmaia.model.Movimentacao;

public class MovimentacaoMapper {

    private MovimentacaoMapper() {
    }

    public static MovimentacaoResponseDTO toResponseDTO(Movimentacao movimentacao) {

        return new MovimentacaoResponseDTO(
                movimentacao.getId(),
                movimentacao.getContaOrigemId(),
                movimentacao.getContaDestinoId(),
                movimentacao.getValor(),
                movimentacao.getTipo(),
                movimentacao.getDataHora(),
                movimentacao.getObservacao()
        );
    }
}
