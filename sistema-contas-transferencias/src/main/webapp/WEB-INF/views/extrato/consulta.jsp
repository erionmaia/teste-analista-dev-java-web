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
    <title>Extrato da Conta</title>
    <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>Extrato da Conta</h1>

<c:if test="${not empty erro}">
    <p style="color: red;">${erro}</p>
</c:if>

<form method="get" action="${pageContext.request.contextPath}/extrato">
    <label>Conta:</label>

    <select name="contaId" required>
        <option value="">Selecione</option>

        <c:forEach var="conta" items="${contas}">
            <option value="${conta.id}" ${conta.id == contaIdSelecionada ? 'selected' : ''}>
                ${conta.numeroConta} - ${conta.nomeTitular}
            </option>
        </c:forEach>
    </select>

    <button type="submit">Consultar</button>
</form>

<c:if test="${not empty extrato}">
    <hr>

    <h2>Dados da Conta</h2>

    <p><strong>Titular:</strong> ${extrato.conta.nomeTitular}</p>
    <p><strong>Número:</strong> ${extrato.conta.numeroConta}</p>
    <p><strong>Status:</strong> ${extrato.conta.status}</p>
    <p><strong>Saldo atual:</strong> ${extrato.saldoAtual}</p>

    <h2>Histórico de Movimentações</h2>

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
        <c:forEach var="mov" items="${extrato.movimentacoes}">
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
</c:if>

<br>

<a href="${pageContext.request.contextPath}/">Voltar</a>

</body>
</html>