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
                    <h3 class="text-center text-info">Редактирование пользователя</h3>
                    <input type="hidden" name="id" class="form-control" value="${user.id}">
                    <input type="hidden" name="login" class="form-control" value="${user.login}">
                    <input type="hidden" name="password" class="form-control" value="${user.password}">

                    <div class="form-group">
                        <label for="email" class="text-info">Email:</label><br>
                        <input type="email" name="email" id="email" class="form-control" value="${user.email}">
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="text-info">Имя:</label><br>
                        <input type="text" name="firstName" id="firstName" class="form-control"
                               value="${user.firstName}">
                    </div>
                    <div class="form-group">
                        <label for="secondName" class="text-info">Фамилия:</label><br>
                        <input type="text" name="secondName" id="secondName" class="form-control"
                               value="${user.secondName}">
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="text-info">Дата рождения:</label><br>
                        <input type="date" name="birthday" id="birthday" class="form-control" value="${user.birthday}">
                    </div>

                    <div id="register-link" class="text-right">
                        <a href="/editPassword" class="text-info">Хотите поменять пароль?</a>
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Редактировать">
                            ${error}
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
<br>
<div id="register-link" class="text-right">
    <a href="/" class="text-info">My cabinet</a>
</div>

<%@ include file="include/under.jsp" %>