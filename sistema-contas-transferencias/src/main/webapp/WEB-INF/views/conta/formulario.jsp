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
                <input type="hidden" name="id" value="${conta.id}">
            </div>

            <br>

            <div>
                <label>Nome do Titular:</label><br>
                <input type="text" name="nomeTitular" value="${conta.nomeTitular}" required>
            </div>

            <br>

            <div>
                <label>Numero da Conta:</label><br>
                <input type="number" name="numeroConta" value="${conta.numeroConta}" required>
            </div>

            <br>

            <div>
                <label>Saldo inicial:</label><br>
                <input type="text" name="saldo" step="0.01" min="0" value="${conta.saldo}" required>
            </div>

            <br>

            <div>
                <label>Status:</label><br>
                <select name="status" required>
                    <option value="ATIVA" ${conta.status == 'ATIVA' ? 'selected' : ''}>Ativa</option>
                    <option value="INATIVA" ${conta.status == 'INATIVA' ? 'selected' : ''}>Inativa</option>
                </select>
            </div>

            <br>

            <button type="submit">Salvar</button>
        </form>

        <br>

        <a href="${pageContext.request.contextPath}/">Voltar</a>
   </body>
<html>