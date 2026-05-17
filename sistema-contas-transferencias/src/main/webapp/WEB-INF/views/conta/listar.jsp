<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Contas</title>
    </head>
    <body>
        <h1>Contas</h1>

        <a href="${pageContext.request.contextPath}/contas/nova">Nova Conta</a>

        <c:if test="${not empty erro}">
            <p style="color: red;">${erro}</p>
        </c:if>

        <table bordero="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titular</th>
                    <th>Número</th>
                    <th>Saldo</th>
                    <th>Status</th>
                    <th>Data de criação</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="conta" items="${contas}">
                    <tr>
                        <td>${conta.id}</td>
                        <td>${conta.nomeTitular}</td>
                        <td>${conta.numeroConta}</td>
                        <td>${conta.saldo}</td>
                        <td>${conta.status}</td>
                        <td>${conta.dataCriacao}</td>
                    </tr>
               </c:forEach>
            </tbody>
        </table>

        <br>

        <a href="${pageContext.request.contextPath}/">Voltar</a>
   </body>
<html>