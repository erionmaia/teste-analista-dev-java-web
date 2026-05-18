<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.sucesso}">
    <p style="color: green;">
        ${sessionScope.sucesso}
    </p>

    <c:remove var="sucesso" scope="session"/>
</c:if>

<c:if test="${not empty erro}">
    <p style="color: red;">
        ${erro}
    </p>
</c:if>

<html>
    <head>
        <title>Movimentações</title>
        <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <h1>Movimentações</h1>

        <form method="get" action="${pageContext.request.contextPath}/movimentacoes">
            <label>Filtrar por conta:</label>

            <select name="contaId">
                <option value="">Todas</option>

                <c:forEach var="conta" items="${contas}">
                    <option value="${conta.id}" ${conta.id == contaIdSelecionada ? 'selected' : ''}>
                        ${conta.numeroConta} - ${conta.nomeTitular}
                    </option>
                </c:forEach>
            </select>

            <button type="submit">Filtrar</button>
        </form>

        <br>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>ID</th>
                <th>Origem</th>
                <th>Destino</th>
                <th>Valor</th>
                <th>Tipo</th>
                <th>Data/Hora</th>
                <th>Observação</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="mov" items="${movimentacoes}">
                <tr>
                    <td>${mov.id}</td>
                    <td>${mov.contaOrigemId}</td>
                    <td>${mov.contaDestinoId}</td>
                    <td>${mov.valor}</td>
                    <td>${mov.tipo}</td>
                    <td>${mov.dataHora}</td>
                    <td>${mov.observacao}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br>

       <br><br>

       <c:if test="${pagina > 1}">
           <a href="${pageContext.request.contextPath}/movimentacoes?pagina=${pagina - 1}&contaId=${contaIdSelecionada}">
               Anterior
           </a>
       </c:if>

       <span> Página ${pagina} de ${totalPaginas} </span>

       <c:if test="${pagina < totalPaginas}">
           <a href="${pageContext.request.contextPath}/movimentacoes?pagina=${pagina + 1}&contaId=${contaIdSelecionada}">
               Próxima
           </a>
       </c:if>

        <br><br>

        <a href="${pageContext.request.contextPath}/">Voltar</a>

    </body>
</html>