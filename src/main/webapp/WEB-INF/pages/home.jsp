<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>My Page</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    Ваш логин: <sec:authentication property="principal.username"/>
    <s></s>
    <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<br>

<c:url value="/user" var="userList"/>
<a href="${userList}">list users</a>

<h2>Личный Кабинет</h2>
<form:form modelAttribute="user">
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
</form:form>


<h2>${user.firstName} выберите машину:</h2>
<c:url value="/car/title" var="createCarName"/>
<c:if test="${user.cars.size()==0}">

    <p>В вашем списке нет машин, <a href="${createCarName}">хотите добавить?</a></p>
</c:if>

<c:if test="${user.cars.size()!=0}">
    <c:forEach items="${carList}" var="carsList">
        <c:url value="car/view/${carsList.id}" var="cars"/>
        <a href="${cars}">Open ${carsList.nameCar}</a>
        <b>|</b>
        <c:url value="car/delete/${carsList.id}" var="carDelete"/>
        <a href="${carDelete}">Delete</a>
        <br>
    </c:forEach>
</c:if>

<h2>add new car</h2>
<a href="${createCarName}">Create car</a>
</body>
</html>