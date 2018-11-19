<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Teachers list</title>
</head>
<body>
<h3 align="center">Таблица</h3>
<br>
<table border="3" align="center">
    <td align="center" colspan="5" style="font-size: large; font-weight: bold">Teachers list</td>
    <c:forEach items="${teachers}" var="teachers">
        <br>
        <tr>
            <td> ${teachers.id} </td>
            <td> ${teachers.name} </td>
            <td> ${teachers.surName} </td>
            <td> ${teachers.gender} </td>
            <td> ${teachers.age} </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
