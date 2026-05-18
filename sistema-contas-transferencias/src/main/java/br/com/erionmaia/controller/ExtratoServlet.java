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
            String contaIdParam = req.getParameter("contaId");

            req.setAttribute("contas", contaService.listarDTO());

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
                req.setAttribute("contas", contaService.listarDTO());
            } catch (Exception ignored) {
            }

            req.getRequestDispatcher("/WEB-INF/views/extrato/consulta.jsp")
                    .forward(req, resp);
        }
    }
}