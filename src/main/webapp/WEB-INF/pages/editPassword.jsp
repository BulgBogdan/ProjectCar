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
                    <input type="hidden" name="email" class="form-control" value="${user.email}">
                    <input type="hidden" name="firstName" class="form-control" value="${user.firstName}">
                    <input type="hidden" name="secondName" class="form-control" value="${user.secondName}">
                    <input type="hidden" name="birthday" class="form-control" value="${user.birthday}">

                    <div class="form-group">
                        <label for="password" class="text-info">Ваш старый пароль:</label><br>
                        <input type="password" name="password" id="password" class="form-control">
                        <p style="color: red">${passwordError}</p>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="text-info">Новый пароль:</label><br>
                        <input type="password" name="newPassword" id="newPassword" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword" class="text-info">Подтверждение нового пароля:</label><br>
                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control">
                        <p style="color: red">${passwordConfirmError}</p>
                    </div>

                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Изменить">
                        <p style="color: red">${error}</p>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
<br>
<div id="register-link" class="text-right">
    <a href="/editUser" class="text-info">&larr;Вернуться назад</a>
</div>

<%@ include file="include/under.jsp" %>
