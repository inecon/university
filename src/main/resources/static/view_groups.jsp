<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Groups list</title>
</head>
<body>
<table border="3" align="center">
<thead>
    <td align="center" colspan="5" style="font-size: large; font-weight: bold">Groups list</td>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Description</th>
        <th colspan=2>Action</th>
    </tr>
</thead>
    <c:forEach items="${groups}" var="groups">
        <tr>
            <td> ${groups.id} </td>
            <td> ${groups.title} </td>
            <td> ${groups.description} </td>
            <td><form id="edit" action="/groups/edit/" method="get" >
                <button type="submit"  name="id" value="${groups.id}">Edit</button>
            </form></td>
            <td><form  method="post" id="delete" action="/groups/delete/" >
                <button type="submit"  name="id" value="${groups.id}">Delete</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
<form id="insert" action="/groups/insert/" method="get" style="margin-left: 10%">
    <button type="submit">Add group</button>
</form>
<div id="button" align="right" style="font-weight: bolder; font-size: larger; background: yellow">
    <button name="back" onclick="window.location = '/index.html'">Back</button>
</div>

</body>
</html>
