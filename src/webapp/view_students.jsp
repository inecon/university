<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Students list</title>
</head>
<body>
<table border="3" align="center">
    <thead>
    <td align="center" colspan="7" style="font-size: large; font-weight: bold">Students list</td>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>gender</th>
            <th>age</th>
            <th colspan=2>Action</th>
        </tr>
    </thead>
    <c:forEach items="${students}" var="students">
        <br>
        <tr>
            <td> ${students.id} </td>
            <td> ${students.name} </td>
            <td> ${students.surName} </td>
            <td> ${students.gender} </td>
            <td> ${students.age} </td>
            <td><a href="students?action=edit&student_id=<c:out value="${students.id}"/>">Update</a></td>
            <td><a href="students?action=delete&userId=<c:out value="${students.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="ViewStudentsServlet?action=insert">Add User</a></p>
<div id="button" align="right" style="font-weight: bolder; font-size: larger; background: yellow">
    <button name="back" onclick='history.back()'>Back</button>
</div>
</body>
</html>
