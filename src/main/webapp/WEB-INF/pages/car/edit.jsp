<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/head.jsp" %>

    <div class="container">
    <div id="edit-row" class="row justify-content-center align-items-center">
        <div id="edit-column" class="col-md-6">
            <div id="edit-box" class="col-md-12">

                <form:form method="POST" modelAttribute="car">
                    <h3 class="text-center text-info">Редактировать</h3>
                    <div class="form-group">
                        <label for="name" class="text-info">Название машины:</label><br>
                        <input type="text" name="nameCar" id="name" class="form-control" value="${car.nameCar}">
                    </div>
                    <div class="form-group">
                        <label for="mileage" class="text-info">Пробег:</label><br>
                        <input type="text" name="mileage" id="mileage" class="form-control" value="${car.mileage}">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <p style="color: red">${Errors}</p>
                        <div id="register-link" class="text-right">
                            <a href="/car/view/${car.id}" class="text-info">На страницу авто</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

<%@ include file="../include/under.jsp"%>