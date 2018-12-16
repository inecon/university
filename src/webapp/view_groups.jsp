<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Groups list</title>
</head>
<body>
<h3 align="center">Таблица</h3>
<br>
<table border="3" align="center">
    <td align="center" colspan="5" style="font-size: large; font-weight: bold">Groups list</td>
    <c:forEach items="${groups}" var="groups">
        <br>
        <tr>
            <td> ${groups.id} </td>
            <td> ${groups.title} </td>
            <td> ${groups.description} </td>
        </tr>
    </c:forEach>
</table>

<div id="button" align="right" style="font-weight: bolder; font-size: larger; background: yellow">
    <button name="back" onclick='history.back()'>Back</button>
</div>

</body>
</html>
