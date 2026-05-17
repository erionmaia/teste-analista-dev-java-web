<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Transferência</title>
    </head>
    <body>

        <h1>Transferência entre Contas</h1>

            <c:if test="${not empty erro}">
                <p style="color: red;">${erro}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/transferencias" method="post">

                <div>
                    <label>Conta de origem:</label><br>
                    <select name="contaOrigemId" required>
                        <option value="">Selecione</option>
                        <c:forEach var="conta" items="${contas}">
                            <option value="${conta.id}">
                                ${conta.numeroConta} - ${conta.nomeTitular} - Saldo: ${conta.saldo} - ${conta.status}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <br>

                <div>
                    <label>Conta de destino:</label><br>
                    <select name="contaDestinoId" required>
                        <option value="">Selecione</option>
                        <c:forEach var="conta" items="${contas}">
                            <option value="${conta.id}">
                                ${conta.numeroConta} - ${conta.nomeTitular} - Saldo: ${conta.saldo} - ${conta.status}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <br>

                <div>
                    <label>Valor:</label><br>
                    <input type="number" name="valor" step="0.01" min="0.01" required>
                </div>

                <br>

                <button type="submit">Transferir</button>

            </form>

        <br>

        <a href="${pageContext.request.contextPath}/contas">Voltar para contas</a>

    </body>
</html>