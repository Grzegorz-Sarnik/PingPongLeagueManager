<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<h2>User Registration</h2>
<form:form method="post" modelAttribute="user" action="/user/add">
    <table>
        <tr>
            <td><label for="login">Login:</label></td>
            <td><form:input path="login" id="login"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="password">Hasło:</label></td>
            <td><form:password path="password" id="password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="firstName">Imię:</label></td>
            <td><form:input path="personData.firstName" id="firstName"/></td>
            <td><form:errors path="personData.firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="lastName">Nazwisko:</label></td>
            <td><form:input path="personData.lastName" id="lastName"/></td>
            <td><form:errors path="personData.lastName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>Gender:</label></td>
            <td>
                <input type="radio" id="male" name="personData.gender" value="Mężczyzna"/>
                <label for="male">Mężczyzna</label>
                <input type="radio" id="female" name="personData.gender" value="Kobieta"/>
                <label for="female">Kobieta</label>
            </td>
            <td><form:errors path="personData.gender" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="dateOfBirth">Data urodzenia:</label></td>
            <td><form:input path="personData.dateOfBirth" id="dateOfBirth" type="date"/></td>
            <td><form:errors path="personData.dateOfBirth" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Register"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>