<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../include/head.jsp" %>

<div class="container">
    <div id="edit-row" class="row justify-content-center align-items-center">
        <div id="edit-column" class="col-md-6">
            <div id="edit-box" class="col-md-12">

                <form:form method="POST" modelAttribute="costs">
                    <h3 class="text-center text-info">Редактировать отчет о затратах</h3>
                    <div class="form-group">
                        <label for="nameOtherCost" class="text-info">Название:</label><br>
                        <input type="text" name="nameOtherCost" id="nameOtherCost" class="form-control"
                               value="${costs.nameOtherCost}">
                    </div>
                    <div class="form-group">
                        <label for="date" class="text-info">Дата:</label><br>
                        <input type="date" name="dateCost" id="date" class="form-control" value="${costs.dateCost}">
                    </div>
                    <div class="form-group">
                        <label for="cost" class="text-info">Стоимость(${costs.car.user.currency.title}):</label><br>
                        <input type="number" name="cost" id="cost" class="form-control"
                               value="${Math.round(costs.cost * 100) / 100}" step="0.01">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <p style="color: red">${Errors}</p>
                        <div id="register-link" class="text-right">
                            <a href="/car/other/costs/${car.id}" class="text-info">Вернуться к списку затрат</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../../include/under.jsp" %>