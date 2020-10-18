<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>myPage</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    <p>Ваш логин: <sec:authentication property="principal.username"/></p>
    <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
</sec:authorize>

<h2>PersonalCabinet</h2>
<form:form modelAttribute="user" >
    <div>
        <td>${user.id}</td>
    </div>
    <div>
        <td>${user.login}</td>
    </div>
    <div>
        <td>${user.birthday}</td>
    </div>
    <div>
        <td>${user.email}</td>
    </div>
    <div>
        <td>${user.firstName}</td>
    </div>
    <div>
        <td>${user.secondName}</td>
    </div>
    <br>
    <h2>${user.firstName} select your car</h2>
    <c:url value="/carView" var="cars"/>
    <a href="${carView}">Open car</a>
    <br>
    <h2>add new car</h2>
    <c:url value="/createCar" var="cars"/>
    <a href="${createCar}">Create car</a>
</form:form>
</body>
</html>
