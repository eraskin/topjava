<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals list</title>

    <style>
        .red {
            color: red;
        }

        .green {
            color: green;
        }

        table, th, td {
            border: 1px solid black;
        }

    </style>

</head>
<body>
<h2>Подсчет калорий</h2>

<br>

    <table>

        <tr>
            <th>Прием пищи</th>
            <th>День и время</th>
            <th>Калории</th>
            <th>Результат</th>
        </tr>

        <c:forEach items="${meals}" var="meal">
            <tr class="${meal.exceed ? 'red' : 'green'}">
                <td>${meal.description}</td>
                <td><fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m"
                                   var="parsedDate" type="date" />

                    <fmt:formatDate value="${parsedDate}" var="stdDatum"
                                    type="date" pattern="yyyy.MM.dd HH:mm" />${stdDatum}</td>

                <td>${meal.calories}</td>
                <td><c:if test="${meal.exceed}">Превышено</c:if>
                    <c:if test="${not meal.exceed}">Молодец</c:if>
                </td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
