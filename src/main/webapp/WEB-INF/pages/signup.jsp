<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd"/>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <%--<title>Registration</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div align="center">--%>
    <%--<form:form action="signupProcess" method="post" modelAttribute="user">--%>

        <%--<table border="0">--%>
            <%--<tr>--%>
                <%--<td colspan="2" align="center"><h2>Registration</h2></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Login:</td>--%>
                <%--<td><form:input path="login" /></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Password:</td>--%>
                <%--<td><form:password path="password" /></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>E-mail:</td>--%>
                <%--<td><form:input path="email" /></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>FirstName:</td>--%>
                <%--<td><form:input path="firstName" /></td>--%>
            <%--</tr><tr>--%>
                <%--<td>SecondName:</td>--%>
                <%--<td><form:input path="secondName" /></td>--%>
            <%--</tr>--%>

                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<label for="birthday">Введите дату:</label>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<input type="date" class="form-control" name="birthday" id="birthday">&ndash;%&gt;--%>

                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

            <%--<tr>--%>
                <%--<td>Birthday (mm/dd/yyyy):</td>--%>
                <%--<td><form:input type="date" path="birthday" /></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="2" align="center"><input type="submit" value="Register" /></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form:form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="user">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="login" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="login"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="confirmPassword"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <div>
            <form:input type="text" path="email" placeholder="email"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="firstName" placeholder="firstName"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="secondName" placeholder="secondName"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="birthday" placeholder="bithday"
                        autofocus="true"></form:input>
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>
