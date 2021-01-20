<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include/head.jsp" %>

<div class="container">
    <div id="singUp-row" class="row justify-content-center align-items-center">
        <div id="singUp-column" class="col-md-6">
            <div id="singUp-box" class="col-md-12">

                <form:form method="POST" modelAttribute="user">
                    <h3 class="text-center text-info">Используемая валюта(${user.currency.title})</h3>
                    <input type="hidden" name="id" class="form-control" value="${user.id}">
                    <input type="hidden" name="login" class="form-control" value="${user.login}">
                    <input type="hidden" name="password" class="form-control" value="${user.password}">
                    <input type="hidden" name="email" id="email" class="form-control" value="${user.email}">
                    <input type="hidden" name="firstName" id="firstName" class="form-control" value="${user.firstName}">
                    <input type="hidden" name="secondName" id="secondName" class="form-control"
                           value="${user.secondName}">
                    <input type="hidden" name="birthday" id="birthday" class="form-control" value="${user.birthday}">
                    <div class="form-group">
                        <label class="text-center text-info">Выбрать:</label><br>
                        <select name="currencyID" class="form-control text-center text-info">
                            <c:forEach items="${currencies}" var="currency">
                                <option value="${currency.id}">${currency.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="text-center text-info">Курс USD по курсу НБРБ:</label><br>
                        <label class="text-center text-justify">${currencyValueUSD}</label>
                    </div>

                    <div class="text-center form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <p style="color: red">${error}</p>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>

<br>

<div id="register-link" class="text-center">
    <a href="#" onclick="history.back();">Вернуться назад</a>
</div>

<%@ include file="include/under.jsp" %>
