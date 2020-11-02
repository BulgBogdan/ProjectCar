<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>

<div class="container">
    <div id="title-row" class="row justify-content-center align-items-center">
        <div id="title-column" class="col-md-6">
            <div id="title-box" class="col-md-12">

                <form:form method="POST" modelAttribute="newCar">
                    <h3 class="text-center text-info">Создать машину</h3>
                    <div class="form-group">
                        <label for="name" class="text-info">Название машины:</label><br>
                        <input type="text" name="nameCar" id="name" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="mileage" class="text-info">Пробег:</label><br>
                        <input type="text" name="mileage" id="mileage" class="form-control">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Создать">
                        <b class="text-danger">${Errors}</b>
                        <div id="register-link" class="text-right">
                            <a href="/" class="text-info">Вернуться назад</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/under.jsp" %>