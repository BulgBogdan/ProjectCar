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

<br>

<c:url value="/user" var="userList"/>
<a href="${userList}">list users</a>

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
    <c:forEach items="${carList}" var="carsList">
        <c:if test="${carsList!=null}">
            <c:url value="car/view/${carsList.id}" var="cars"/>
            <a href="${cars}">Open ${carsList.nameCar}</a>
        </c:if>
        <c:if test="${carsList==null}">
            <p>Car didn't created</p>
        </c:if>
        <br>
    </c:forEach>
    <br>
    <h2>add new car</h2>
    <c:url value="/car/createName" var="createCarName"/>
    <a href="${createCarName}">Create car</a>
</form:form>
</body>
</html>