<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Document</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
Ваш логин: <sec:authentication property="principal.username"/>
<s></s>
<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
</sec:authorize>

<body>
<div>
    <form:form method="POST" modelAttribute="doc">

        <h2>Документы:</h2>
        <div>
            <form:input type="text" path="nameDocument" placeholder="Document"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="number" path="documentCost" placeholder="Cost"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="date" path="beginDate" placeholder="start date"
                        autofocus="true"></form:input>
        </div>
            <div>
                <form:input type="date" path="endDate" placeholder="end date"
                            autofocus="true"></form:input>
            </div>

        <button type="submit">Create</button>

    </form:form>
    <br>
    <c:url value="/car/documents/${car.id}" var="view"/>
    <a href="${view}">Back</a>
</div>

</body>
</html>


