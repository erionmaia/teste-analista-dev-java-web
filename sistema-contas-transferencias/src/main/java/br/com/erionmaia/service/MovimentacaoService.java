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

    public int calcularTotalPagina(int tamanho) throws SQLException {

        int totalRegistros = movimentacaoDAO.contarTotal();

        int totalPaginas = (int) Math.ceil((double) totalRegistros / tamanho);

        return Math.max(totalPaginas, 1);
    }

    public int calcularTotalPaginaPorConta(Integer contaId, int tamanho) throws SQLException {

        int totalRegistrosPorConta = movimentacaoDAO.contarTotalPorConta(contaId);

        int totalPaginas = (int) Math.ceil((double) totalRegistrosPorConta / tamanho);

        return Math.max(totalPaginas, 1);
    }
}
