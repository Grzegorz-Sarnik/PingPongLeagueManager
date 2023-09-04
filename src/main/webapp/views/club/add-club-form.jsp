<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj klub</title>
</head>
<body>
<h2>Club Form</h2>
<form:form method="post" modelAttribute="club" action="/club/add">
    <table>
        <tr>
            <td><label for="name">Nazwa klubu:</label></td>
            <td><form:input path="name" id="name" required="true" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label for="contact">Kontakt:</label></td>
            <td><form:input path="contact" id="contact" /></td>
            <td><form:errors path="contact" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label for="address">Adres klubu:</label></td>
            <td><form:input path="address" id="address" /></td>
            <td><form:errors path="address" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Zapisz" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
