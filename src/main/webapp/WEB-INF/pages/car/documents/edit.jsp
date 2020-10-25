<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить Документ</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
Ваш логин: <sec:authentication property="principal.username"/>
<s></s>
<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="docs">

    <h2>Изменить Документ</h2>
    <div>
        <form:input type="text" path="nameDocument" title="${docs.nameDocument}"></form:input>
    </div>
    <div>
        <form:input type="number" path="documentCost" title="${docs.documentCost}"></form:input>
    </div>
    <div>
        <form:input type="date" path="beginDate" title="${docs.beginDate}"></form:input>
    </div>
    <div>
        <form:input type="date" path="endDate" title="${docs.endDate}"></form:input>
    </div>

    <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/car/view/${docs.car.id}" var="carView"/>
    <a href="${carView}">Back to ${docs.car.nameCar}</a>

</body>
</html>
