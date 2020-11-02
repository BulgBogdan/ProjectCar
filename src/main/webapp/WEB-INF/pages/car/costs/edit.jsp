<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/head.jsp" %>

<div class="container">
    <div id="edit-row" class="row justify-content-center align-items-center">
        <div id="edit-column" class="col-md-6">
            <div id="edit-box" class="col-md-12">

                <form:form method="POST" modelAttribute="registration">
                    <h3 class="text-center text-info">Редактировать</h3>
                    <div class="form-group">
                        <input type="hidden" name="${registration.id}" id="id" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="price" class="text-info">Цена авто:</label><br>
                        <input type="number" name="priceCar" id="price" class="form-control"
                               value="${registration.priceCar}" step="0.01">
                    </div>
                    <div class="form-group">
                        <label for="registration" class="text-info">Сумма за оформление авто:</label><br>
                        <input type="number" name="priceRegistration" id="registration" class="form-control"
                               value="${registration.priceRegistration}" step="0.01">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <b class="text-danger">${Errors}</b>
                        <div id="register-link" class="text-right">
                            <a href="/car/view/${registration.car.id}" class="text-info">На страницу авто</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/under.jsp" %>