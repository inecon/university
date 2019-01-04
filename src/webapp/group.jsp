<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <title>Add new group</title>
</head>
<body>
<form method="POST" action='/groups' name="frmAddGroup">
    Group ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${group.id}" />" /> <br />
    Title : <input
        type="text" name="title"
        value="<c:out value="${group.title}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${group.description}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>