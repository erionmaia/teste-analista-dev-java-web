package br.com.erionmaia.service;

import br.com.erionmaia.dao.MovimentacaoDAO;
import br.com.erionmaia.model.Movimentacao;

import java.sql.SQLException;
import java.util.List;

public class MovimentacaoService {

    private final MovimentacaoDAO movimentacaoDAO;

    public MovimentacaoService() {
        this.movimentacaoDAO = new MovimentacaoDAO();
    }

    public List<Movimentacao> listarPorConta(Integer contaId, int pagina, int tamanho) throws SQLException {

        if (pagina < 1) {
            pagina = 1;
        }

        if (tamanho < 1) {
            tamanho = 10;
        }

        return movimentacaoDAO.listarPorConta(contaId, pagina, tamanho);
    }
}
