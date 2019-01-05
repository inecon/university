<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Teachers list</title>
</head>
<body>
<table border="3" align="center">
<thead>
    <td align="center" colspan="7" style="font-size: large; font-weight: bold">Teachers list</td>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Gender</th>
        <th>Age</th>
        <th colspan=2>Action</th>
    </tr>
</thead>
    <c:forEach items="${teachers}" var="teachers">
        <tr>
            <td> ${teachers.id} </td>
            <td> ${teachers.name} </td>
            <td> ${teachers.surName} </td>
            <td> ${teachers.gender} </td>
            <td> ${teachers.age} </td>
            <td><form id="edit" action="/teachers/edit/" method="get" >
                <button type="submit"  name="id" value="${teachers.id}">Edit</button>
            </form></td>
            <td><form  method="post" id="delete" action="/teachers/delete/" >
                <button type="submit"  name="id" value="${teachers.id}">Delete</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
<form id="insert" action="/teachers/insert/" method="get" style="margin-left: 10%">
    <button type="submit">Add teacher</button>
</form>
<div id="button" align="right" style="font-weight: bolder; font-size: larger; background: yellow">
    <button name="back" onclick="window.location = '/index.html'">Back</button>
</div>

</body>
</html>
