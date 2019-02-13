<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <title>Add new student</title>
</head>
<body>
<form method="POST" action='/students' name="frmAddStudent">
    Student ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${student.id}" />" /> <br />
    First Name : <input
        type="text" name="name"
        value="<c:out value="${student.name}" />" /> <br />
    Surname : <input
        type="text" name="surName"
        value="<c:out value="${student.surName}" />" /> <br />
    Gender : <input
        type="text" name="gender"
        value="<c:out value="${student.gender}" />" /> <br />
    Age : <input type="text" name="age"
                   value="<c:out value="${student.age}" />" /> <br />
    Group : <input type="text" name="group_id"
                 value="<c:out value="${student.group.id}" />" /> <br />

    <input type="submit" value="Submit" />
</form>
</body>
</html>