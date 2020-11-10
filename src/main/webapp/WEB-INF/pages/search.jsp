<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="include/head.jsp" %>

<c:if test="${carsList.size() != 0}">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col" class="text-center">Название машины</th>
        <th scope="col" class="text-center">Пробег</th>
    </tr>
    </thead>
    <c:forEach var="car" items="${carsList}">
    <tbody>
    <tr>
        <th scope="row" class="text-center text-info">${car.nameCar}</th>
        <th scope="row" class="text-center text-info">${car.mileage}</th>
    </tr>
    </tbody>
    </c:forEach>
</table>
</c:if>

<c:if test="${docs.size() != 0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" class="text-center">Название документа</th>
            <th scope="col" class="text-center">Дата начала</th>
            <th scope="col" class="text-center">Дата окончания</th>
        </tr>
        </thead>
        <c:forEach var="doc" items="${docs}">
            <tbody>
            <tr>
                <th scope="row" class="text-center text-info">${doc.nameDocument}</th>
                <th scope="row" class="text-center text-info">${doc.beginDate}</th>
                <th scope="row" class="text-center text-info">${doc.endDate}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</c:if>


<c:if test="${costs.size() != 0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" class="text-center">Название затрат</th>
            <th scope="col" class="text-center">Дата</th>
            <th scope="col" class="text-center">Стоимость</th>
        </tr>
        </thead>
        <c:forEach var="cost" items="${costs}">
            <tbody>
            <tr>
                <th scope="row" class="text-center text-info">${cost.nameOtherCost}</th>
                <th scope="row" class="text-center text-info">${cost.dateCost}</th>
                <th scope="row" class="text-center text-info">${cost.cost}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</c:if>
<c:if test="${repairs.size() != 0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" class="text-center">Название ремонта</th>
            <th scope="col" class="text-center">Пробег при ремонте</th>
            <th scope="col" class="text-center">Пробег по гарантии</th>
        </tr>
        </thead>
        <c:forEach var="repair" items="${repairs}">
            <tbody>
            <tr>
                <th scope="row" class="text-center text-info">${repair.nameRepair}</th>
                <th scope="row" class="text-center text-info">${repair.beginMileage}</th>
                <th scope="row" class="text-center text-info">${repair.serviceLife}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</c:if>

<c:if test="${(carsList.size() == 0) && (docs.size() == 0) && (costs.size() == 0) && (repairs.size() == 0)}">
    <h5 class="text-center">К сожалению ничего не найдено, попробуйте изменить параметры поиска</h5>
</c:if>

    <div id="register-link" class="text-left">
    <br>
    <a href="/" class="text-info">В личный кабинет</a>
    </div>

<%@ include file="include/under.jsp" %>