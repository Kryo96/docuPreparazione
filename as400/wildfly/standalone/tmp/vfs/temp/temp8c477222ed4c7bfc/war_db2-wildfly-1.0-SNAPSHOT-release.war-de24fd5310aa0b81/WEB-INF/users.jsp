<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="https://jakarta.ee/tags/core" prefix="c" %>

<html>
<head>
    <title>Elenco utenti</title>
</head>
<body>
<h1>Lista utenti dal database</h1>

<table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
        <th>EmpNo</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty users}">
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.empNo}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr><td colspan="3">Nessun utente trovato</td></tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>
