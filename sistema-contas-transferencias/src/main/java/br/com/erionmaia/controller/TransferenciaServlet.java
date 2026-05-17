package br.com.erionmaia.controller;

import br.com.erionmaia.service.ContaService;
import br.com.erionmaia.service.TransferenciaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/transferencias")
public class TransferenciaServlet extends HttpServlet {

    private final ContaService contaService = new ContaService();
    private final TransferenciaService transferenciaService = new TransferenciaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setAttribute("contas", contaService.listar());

            req.getRequestDispatcher("WEB-INF/views/transferencia/formulario.jsp")
                    .forward(req, resp);
        }catch (Exception e){
            throw new ServletException("Erro ao carregar tela de transferência", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Integer contaOrigemId = Integer.valueOf(req.getParameter("contaOrigemId"));
            Integer contaDestinoId = Integer.valueOf(req.getParameter("contaDestinoId"));
            BigDecimal valor = new BigDecimal(req.getParameter("valor"));

            transferenciaService.transferir(contaOrigemId, contaDestinoId, valor);

            req.getSession().setAttribute(
                    "sucesso",
                    "Transferência realizada com sucesso."
            );

            resp.sendRedirect(req.getContextPath() + "/contas");

        }catch (Exception e){
            try {
                req.setAttribute("erro", e.getMessage());
                req.setAttribute("contas", contaService.listar());

                req.getRequestDispatcher("WEB-INF/views/transferencia/formulario.jsp")
                        .forward(req, resp);
            } catch (Exception ex) {
                throw new ServletException("Erro ao processar a transferência", ex);
            }
        }
    }
}
