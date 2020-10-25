<%--
  Created by IntelliJ IDEA.
  User: superpony
  Date: 25.10.2020
  Time: 7:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>TimeCalculator</title>
</head>
<body>
<form method="post">
    Часы обозначаются ЛЮБОЙ буквой (русской или английской) <br/>
    <input type="text" name="primer" style="text-align: right" value="${primer}">
    <button type="submit"> = </button>
    <button type="reset"> c </button>
    <br/>
    ${stroka}
    <br/>
    <h1>${otvet}</h1>

</form>
</body>
</html>
