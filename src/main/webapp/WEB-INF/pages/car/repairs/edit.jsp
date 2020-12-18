<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/head.jsp" %>

<div class="container">
    <div id="edit-row" class="row justify-content-center align-items-center">
        <div id="edit-column" class="col-md-6">
            <div id="edit-box" class="col-md-12">

                <form:form method="POST" modelAttribute="repair">
                    <h3 class="text-center text-info">Редактировать отчет о ремонте</h3>
                    <div class="form-group">
                        <label for="nameRepair" class="text-info">Название ремонта и(или) запчасти:</label><br>
                        <input type="text" name="nameRepair" id="nameRepair" class="form-control"
                               value="${repair.nameRepair}">
                    </div>
                    <div class="form-group">
                        <label for="beginMileage" class="text-info">Пробег при ремонте(км.):</label><br>
                        <input type="number" name="beginMileage" id="beginMileage" class="form-control"
                               value="${repair.beginMileage}">
                    </div>
                    <div class="form-group">
                        <label for="costsRepair" class="text-info">
                            Цена ремонта и(или) запчастей(${docs.car.user.currency.title}):
                        </label><br>
                        <input type="number" name="costsRepair" id="costsRepair" class="form-control" step="0.01"
                               value="${repair.costsRepair}">
                    </div>
                    <div class="form-group">
                        <label for="serviceLife" class="text-info">Срок износа запчасти по регламенту(км.):</label><br>
                        <input type="number" name="serviceLife" id="serviceLife" class="form-control"
                               value="${repair.serviceLife}">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <p style="color: red">${Errors}</p>
                        <div id="register-link" class="text-right">
                            <a href="/car/repairs/${car.id}" class="text-info">Вернуться к списку ремонтов</a>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/under.jsp"%>