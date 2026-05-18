package br.com.erionmaia.controller;

import br.com.erionmaia.service.ContaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/extrato")
public class ExtratoServlet extends HttpServlet {

    private final ContaService contaService = new ContaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            int pagina = 1;

            String paginaParam = req.getParameter("pagina");

            if (paginaParam != null
                    && !paginaParam.isBlank()) {

                pagina = Integer.parseInt(paginaParam);
            }

            int tamanho = 10;

            String contaIdParam = req.getParameter("contaId");

            req.setAttribute("contas", contaService.listarDTO(pagina, tamanho));

            if (contaIdParam != null && !contaIdParam.isBlank()) {
                Integer contaId = Integer.valueOf(contaIdParam);
                req.setAttribute("extrato", contaService.gerarExtrato(contaId));
                req.setAttribute("contaIdSelecionada", contaId);
            }

            req.getRequestDispatcher("/WEB-INF/views/extrato/consulta.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("erro", e.getMessage());

            try {
                int pagina = 1;

                String paginaParam = req.getParameter("pagina");

                if (paginaParam != null
                        && !paginaParam.isBlank()) {

                    pagina = Integer.parseInt(paginaParam);
                }

                int tamanho = 10;
                req.setAttribute("contas", contaService.listarDTO(pagina, tamanho));
            } catch (Exception ignored) {
            }

            req.getRequestDispatcher("/WEB-INF/views/extrato/consulta.jsp")
                    .forward(req, resp);
        }
    }
}