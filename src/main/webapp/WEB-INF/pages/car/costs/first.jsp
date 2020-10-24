<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
Ваш логин: <sec:authentication property="principal.username"/>
<s></s>
<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="registration">

        <h2>Цена автомобиля и сумма за оформление:</h2>

        <div>
            <form:input type="text" path="priceCar" placeholder="Цена авто"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="priceRegistration" placeholder="Сумма за оформление"
                        autofocus="true"></form:input>
        </div>

        <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/" var="cabinet"/>
    <a href="${cabinet}">Back</a>
</div>

</body>
</html>
