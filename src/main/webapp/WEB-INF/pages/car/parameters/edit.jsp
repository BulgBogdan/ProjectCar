<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/head.jsp" %>

<div class="container">
    <div id="edit-row" class="row justify-content-center align-items-center">
        <div id="edit-column" class="col-md-6">
            <div id="edit-box" class="col-md-12">

                <form:form method="POST" modelAttribute="parameter">
                    <h3 class="text-center text-info">Редактировать параметры</h3>
                    <div class="form-group">
                        <input type="hidden" name="${parameter.id}" id="id" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="mark" class="text-info">Марка:</label><br>
                        <input type="text" name="mark" id="mark" class="form-control" value="${parameter.mark}">
                    </div>
                    <div class="form-group">
                        <label for="model" class="text-info">Модель:</label><br>
                        <input type="text" name="model" id="model" class="form-control" value="${parameter.model}">
                    </div>
                    <div class="form-group">
                        <label for="year" class="text-info">Год выпуска:</label><br>
                        <input type="number" name="year" id="year" class="form-control" value="${parameter.year}">
                    </div>
                    <div class="form-group">
                        <label for="firstMileage" class="text-info">Первоначальный пробег(км.):</label><br>
                        <input type="number" name="firstMileage" id="firstMileage" class="form-control"
                               value="${parameter.firstMileage}">
                    </div>
                    <div class="form-group">
                        <label for="mass" class="text-info">Масса(кг.):</label><br>
                        <input type="number" name="mass" id="mass" class="form-control" value="${parameter.mass}">
                    </div>
                    <div class="form-group">
                        <label for="color" class="text-info">Цвет:</label><br>
                        <input type="text" name="color" id="color" class="form-control" value="${parameter.color}">
                    </div>
                    <div class="form-group">
                        <label for="averageRate" class="text-info">Средний расход топлива(необходим для
                            расчетов):</label><br>
                        <input type="number" name="averageRate" id="averageRate" class="form-control" step="0.01"
                               value="${parameter.averageRate}">
                    </div>
                    <div class="form-group">
                        <label for="vin" class="text-info">VIN:</label><br>
                        <input type="text" name="vin" id="vin" class="form-control" value="${parameter.vin}">
                    </div>
                    <div class="form-group">
                        <label for="registrationNumber" class="text-info">Регистрационный знак:</label><br>
                        <input type="text" name="registrationNumber" id="registrationNumber" class="form-control"
                               value="${parameter.registrationNumber}">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <div id="register-link" class="text-right">
                            <a href="/car/view/${parameter.car.id}" class="text-info">На страницу авто</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/under.jsp" %>