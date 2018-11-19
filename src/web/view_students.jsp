<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Students list</title>
</head>
<body>
<h3 align="center">Таблица</h3>
<br>
<table border="3" align="center">
    <td align="center" colspan="5" style="font-size: large; font-weight: bold">Students list</td>
    <c:forEach items="${students}" var="students">
        <br>
        <tr>
            <td> ${students.id} </td>
            <td> ${students.name} </td>
            <td> ${students.surName} </td>
            <td> ${students.gender} </td>
            <td> ${students.age} </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
