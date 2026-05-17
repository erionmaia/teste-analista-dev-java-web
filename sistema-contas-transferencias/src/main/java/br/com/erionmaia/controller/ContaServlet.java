package br.com.erionmaia.controller;

import br.com.erionmaia.model.Conta;
import br.com.erionmaia.service.ContaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet({"/contas", "/contas/nova"})
public class ContaServlet extends HttpServlet {

    private final ContaService contaService = new ContaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String path = req.getServletPath();

            if ("/contas/nova".equals(path)) {
                req.getRequestDispatcher("/WEB-INF/views/conta/formulario.jsp")
                        .forward(req, resp);
                return;
            }

            req.setAttribute("contas", contaService.listar());

            req.getRequestDispatcher(
                    "/WEB-INF/views/conta/listar.jsp")
                    .forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar contas.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String nomeTitular = req.getParameter("nomeTitular");
            String numeroConta = req.getParameter("numeroConta");
            BigDecimal saldo = new BigDecimal(req.getParameter("saldo"));
            String status =  req.getParameter("status");

            Conta conta = new Conta(
                    null,
                    nomeTitular,
                    numeroConta,
                    saldo,
                    status,
                    null
            );

            contaService.criarConta(conta);

            resp.sendRedirect(req.getContextPath() + "/contas");
        } catch (Exception e) {
            req.setAttribute("erro", e.getMessage());

            req.getRequestDispatcher("/WEB-INF/views/conta/formulario.jsp")
                    .forward(req, resp);
        }
    }
}
