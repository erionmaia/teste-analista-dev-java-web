<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Nova Conta</title>
    </head>
    <body>
        <h1>Nova Conta</h1>

        <% if (request.getAttribute("erro") != null) { %>
            <p style="color: red;"><%= request.getAttribute("erro") %></p>
        <% } %>

        <form action="${pageContext.request.contextPath}/contas" method="post">

            <div>
                <label>Nome do Titular:</label><br>
                <input type="text" name="nomeTitular" required>
            </div>

            <br>

            <div>
                <label>Numero da Conta:</label><br>
                <input type="number" name="numeroConta" required>
            </div>

            <br>

            <div>
                <label>Saldo inicial:</label><br>
                <input type="text" name="saldo" step="0.01" min="0" required>
            </div>

            <br>

            <div>
                <label>Status:</label><br>
                <select name="status" required>
                    <option value="ATIVA">Ativa</option>
                    <option value="INATIVA">Inativa</option>
                </select>
            </div>

            <br>

            <button type="submit">Salvar</button>
        </form>

        <br>

        <a href="${pageContext.request.contextPath}/">Voltar</a>
   </body>
<html>