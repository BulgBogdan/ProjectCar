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

                <form:form method="POST" modelAttribute="doc">
                    <h3 class="text-center text-info">Создать документ</h3>
                    <div class="form-group">
                        <label for="name" class="text-info">Название документа:</label><br>
                        <input type="text" name="nameDocument" id="name" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="cost" class="text-info">Стоимость:</label><br>
                        <input type="number" name="documentCost" id="cost" class="form-control" step="0.01">
                    </div>
                    <div class="form-group">
                        <label for="date" class="text-info">Дата начала:</label><br>
                        <input type="date" name="beginDate" id="date" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="dateEnd" class="text-info">Дата окончания:</label><br>
                        <input type="date" name="endDate" id="dateEnd" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="month" class="text-info">Кол-во месяцев:</label><br>
                        <input type="number" name="endDate" id="month" class="form-control">
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Создать">
                        <div id="register-link" class="text-right">
                            <a href="/car/documents/${car.id}" class="text-info">Вернуться к списку документов</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/under.jsp" %>