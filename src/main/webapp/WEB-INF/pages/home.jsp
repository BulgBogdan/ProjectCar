<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include/head.jsp" %>

<form:form modelAttribute="user">
    <section class="content content_content" style="width: 70%; margin: auto;">
        <section class="invoice">
            <div class="row">
                <div class="col-xs-12">
                    <h3 class="page-header">
                        <i class="fa fa-globe"></i> Ваш профиль:
                    </h3>
                </div>
            </div>
            <div class="row invoice-info">
                <div class="col-sm-8 invoice-col">
                    <address>
                        Логин:<b> ${user.login}</b><br>
                        День рождения:<b> ${user.birthday}</b><br>
                        Почта:<b> ${user.email}</b><br>
                        Имя:<b> ${user.firstName}</b><br>
                        Фамилия:<b> ${user.secondName}</b><br>
                        <span class="badge badge-pill badge-light">Редактировать профиль</span>
                    </address>
                </div>
            </div>
        </section>
    </section>
</form:form>
    <br>

    <c:url value="/user" var="userList"/>
    <a href="${userList}">list users</a>





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
    <h2>add new car</h2>
    <a href="${createCarName}">Create car</a>
</c:if>

<%@include file="include/under.jsp" %>