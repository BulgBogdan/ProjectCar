<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/head.jsp" %>

<div class="container">
    <div id="edit-row" class="row justify-content-center align-items-center">
        <div id="edit-column" class="col-md-6">
            <div id="edit-box" class="col-md-12">

                <form:form method="POST" modelAttribute="fuel">
                    <h3 class="text-center text-info">Создать отчет о заправке</h3>
                    <div class="form-group">
                        <label for="literCost" class="text-info">Цена за литр топлива:</label><br>
                        <input type="number" name="literCost" id="literCost" class="form-control" step="0.01">
                    </div>
                    <div>
                        <p class="text-center text-info"><b>Укажите объем заправленного топлива или сумму:</b></p>

                        <div class="form-group">
                            <label for="literValue" class="text-info">Заправленный объем:</label><br>
                            <input type="number" name="literValue" id="literValue" class="form-control"
                                   step="0.01" value="0 hidden">
                        </div>

                        <div class="form-group">
                            <label for="summ" class="text-info">Сумма:</label><br>
                            <input type="number" name="summ" id="summ" class="form-control" step="0.01"
                                   value="0 hidden">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Создать">
                        <b class="text-danger">${Errors}</b>
                        <div id="register-link" class="text-right">
                            <a href="/car/fuel/${car.id}" class="text-info">Вернуться к списку заправок</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/under.jsp" %>