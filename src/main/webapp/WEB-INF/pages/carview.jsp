<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyCar</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <p>Ваш логин: <sec:authentication property="principal.username"/></p>
    <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
</sec:authorize>

<h2>PersonalCabinet</h2>
<form:form modelAttribute="car" >
    <div>
        <td>${car.id}</td>
    </div>
    <div>
        <td>${car.nameCar}</td>
    </div>
</form:form>
<c:url value="/home" var="home"/>
<a href="${home}">HOME</a>
</body>
</html>
