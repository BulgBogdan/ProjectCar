<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Car</title>
</head>
<h1>Создать машинку</h1>

<sec:authorize access="isAuthenticated()">
    Ваш логин: <sec:authentication property="principal.username"/>
    <s></s>
    <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="newCar">

        <h2>Create car</h2>

        <div>
            <form:input type="text" path="nameCar" placeholder="Car Name"
                        autofocus="true"></form:input>
        </div>

        <div>
            <form:input type="double" path="priceCar" placeholder="Price car"
                        autofocus="true"></form:input>
        </div>

        <div>
            <form:input type="double" path="costRegistration" placeholder="Registration cost"
                        autofocus="true"></form:input>
        </div>

        <form action="/car/parameters/${newCar.id}">
        <button type="submit">Create</button>
        </form>

    </form:form>
    <br>
    <c:url value="/" var="cabinet"/>
    <a href="${cabinet}">В личный кабинет</a>
</div>

</body>
</html>