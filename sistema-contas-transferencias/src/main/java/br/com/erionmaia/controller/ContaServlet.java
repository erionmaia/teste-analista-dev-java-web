package br.com.erionmaia.controller;

import br.com.erionmaia.dto.ContaRequestDTO;
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

@WebServlet({"/contas", "/contas/nova", "/contas/editar"})
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

            if ("/contas/editar".equals(path)) {

                Integer id = Integer.parseInt(req.getParameter("id"));

                req.setAttribute("conta", contaService.buscarDTOPorId(id));

                req.getRequestDispatcher("/WEB-INF/views/conta/formulario.jsp")
                        .forward(req, resp);
                return;
            }

            int pagina = 1;

            String paginaParam = req.getParameter("pagina");

            if (paginaParam != null
                    && !paginaParam.isBlank()) {

                pagina = Integer.parseInt(paginaParam);
            }

            int tamanho = 10;

            req.setAttribute("contas", contaService.listarDTO(pagina, tamanho));

            req.setAttribute("pagina", pagina);

            req.setAttribute(
                    "totalPaginas",
                    contaService.calcularTotalPagina(tamanho));

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

            String idParam = req.getParameter("id");

            Integer id = null;
            if (idParam != null && !idParam.isBlank()) {
                id = Integer.valueOf(idParam);
            }
            String nomeTitular = req.getParameter("nomeTitular");
            String numeroConta = req.getParameter("numeroConta");
            BigDecimal saldo = new BigDecimal(req.getParameter("saldo"));
            String status =  req.getParameter("status");

            ContaRequestDTO dto = new ContaRequestDTO(
                    nomeTitular,
                    numeroConta,
                    saldo,
                    status
            );

            if (id == null) {
                contaService.criarConta(dto);
                req.getSession().setAttribute(
                        "sucesso",
                        "Conta criada com sucesso."
                );
            }

            else {
                contaService.atualizarConta(id, dto);
                req.getSession().setAttribute(
                        "sucesso",
                        "Conta Atualizada com sucesso."
                );
            }

            resp.sendRedirect(req.getContextPath() + "/contas");
        } catch (Exception e) {
            req.setAttribute("erro", e.getMessage());

            req.getRequestDispatcher("/WEB-INF/views/conta/formulario.jsp")
                    .forward(req, resp);
        }
    }
}
