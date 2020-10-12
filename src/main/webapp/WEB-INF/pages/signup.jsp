<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<div align="center">
    <form:form action="signupProcess" method="post" modelAttribute="user">

        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Registration</h2></td>
            </tr>
            <tr>
                <td>Login:</td>
                <td><form:input path="login" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>FirstName:</td>
                <td><form:input path="firstName" /></td>
            </tr><tr>
                <td>SecondName:</td>
                <td><form:input path="secondName" /></td>
            </tr>

                <%--<div class="form-group">--%>
                    <%--<label for="birthday">Введите дату:</label>--%>
                    <%--<input type="date" class="form-control" name="birthday" id="birthday">--%>

                <%--</div>--%>

            <tr>
                <td>Birthday (mm/dd/yyyy):</td>
                <td><form:input type="date" path="birthday" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
