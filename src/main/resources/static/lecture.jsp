<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <title>Add new lecture</title>
</head>
<body>
<form method="POST" action='/lectures' name="frmAddLecture">
    Lecture ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${lecture.id}" />" /> <br />
    Date : <input
        type="text" name="date"
        value="<c:out value="${lecture.date}" />" /> <br />
    Subject : <input
        type="text" name="subject"
        value="<c:out value="${lecture.subject}" />" /> <br />
    Teacher_id : <input
        type="text" name="teacher_id"
        value="<c:out value="${lecture.teacher.getId()}" />" /> <br />
    Group_id : <input
        type="text" name="group_id"
        value="<c:out value="${lecture.group.getId()}" />" /> <br />
    Class room : <input
        type="text" name="classroom"
        value="<c:out value="${lecture.classroom}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>