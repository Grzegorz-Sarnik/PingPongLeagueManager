<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach items="${players}" var="player">
        <li> ${player}</li>
    </c:forEach>
</ul>


</body>
</html>
