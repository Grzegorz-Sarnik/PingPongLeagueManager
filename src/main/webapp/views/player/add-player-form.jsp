<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodaj Zawodnika</title>
</head>
<body>
<h2>Player Form</h2>
<form:form method="post" modelAttribute="player" action="/player/add">
    <table>
        <tr>
            <td><label for="firstName">Imię:</label></td>
            <td><form:input path="personData.firstName" id="firstName" required="true"/></td>
            <td><form:errors path="personData.firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="lastName">Nazwisko:</label></td>
            <td><form:input path="personData.lastName" id="lastName" required="true"/></td>
            <td><form:errors path="personData.lastName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>Gender:</label></td>
            <td>
                <input type="radio" id="male" name="personData.gender" value="Mężczyzna" required="true"/>
                <label for="male">Mężczyzna</label>
                <input type="radio" id="female" name="personData.gender" value="Kobieta" required="true"/>
                <label for="female">Kobieta</label>
            </td>
            <td><form:errors path="personData.gender" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="licenseNumber">License Number:</label></td>
            <td><form:input path="licenseNumber" id="licenseNumber"/></td>
            <td><form:errors path="licenseNumber" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="dateOfBirth">Data urodzenia:</label></td>
            <td><form:input path="personData.dateOfBirth" id="dateOfBirth" type="date"/></td>
            <td><form:errors path="personData.dateOfBirth" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Save Player"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
