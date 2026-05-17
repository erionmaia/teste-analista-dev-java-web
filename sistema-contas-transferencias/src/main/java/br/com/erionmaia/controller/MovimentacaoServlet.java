package br.com.erionmaia.controller;

import br.com.erionmaia.service.ContaService;
import br.com.erionmaia.service.MovimentacaoService;
import br.com.erionmaia.service.TransferenciaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/movimentacoes")
public class MovimentacaoServlet extends HttpServlet {

    private final ContaService contaService = new ContaService();
    private final MovimentacaoService movimentacaoService = new MovimentacaoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer contaId = null;

            String contaIdParam = req.getParameter("contaId");
            if (contaIdParam != null && !contaIdParam.isBlank()) {
                contaId = Integer.valueOf(contaIdParam);
            }

            int pagina = 1;
            String paginaParam = req.getParameter("pagina");
            if (paginaParam != null && !paginaParam.isBlank()) {
                pagina = Integer.valueOf(paginaParam);
            }

            int tamanho = 10;

            req.setAttribute("contas", contaService.listar());
            req.setAttribute("movimentacoes", movimentacaoService.listarPorConta(contaId, pagina, tamanho));
            req.setAttribute("contaIdSelecionada", contaId);
            req.setAttribute("pagina", pagina);

            req.getRequestDispatcher("WEB-INF/views/movimentacao/listar.jsp")
                    .forward(req, resp);
        }catch (Exception e){
            throw new ServletException("Erro ao carregar tela de movimentacao", e);
        }
    }
}
