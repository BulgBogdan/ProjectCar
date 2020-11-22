<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include/head.jsp" %>

<form:form modelAttribute="user">
    <div class="container-fluid h-100">
        <div class="row h-100">
            <aside class="col-12 col-md-2 p-0 bg-light">
                <nav class="navbar navbar-expand navbar-light bg-light flex-md-column flex-row align-items-start py-0">
                    <div class="collapse navbar-collapse align-items-start">
                        <ul class="flex-md-column flex-row navbar-nav w-100 justify-content-between">
                            <b>Ваш профиль:</b>

                            <li class="nav-item">
                                <small>Логин:<b> ${user.login}</b></small>
                                <br>
                            </li>
                            <li class="nav-item">
                                <small>День рождения:<b> ${user.birthday}</b></small>
                                <br>
                            </li>
                            <li class="nav-item">
                                <small>Почта:<b> ${user.email}</b></small>
                                <br>
                            </li>
                            <li class="nav-item">
                                <small>Имя:<b> ${user.firstName}</b></small>
                                <br>
                            </li>
                            <li class="nav-item">
                                <small>Фамилия:<b> ${user.secondName}</b></small>
                                <br>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link pl-0" href="/editUser"><i class="fa fa-list fa-fw"></i> <span
                                        class="d-none d-md-inline">Редактировать профиль</span></a>
                            </li>
                            <br>
                            <b>Описание приложения:</b>
                            <small><p>Приложение MyCar, позволяет вести учет всех затрат на ваш автомобиль. Если
                                редактировать пробег
                                и указывать все данные, вы увидите сколько денег требует автомобиль.</p></small>
                            <small><p>Также приложение показывает(при помощи цветовой гаммы), какой срок износа у
                                замененной детали, что
                                позволяет контролировать заявленные сроки износа.</p></small>
                            <small><p>В дальнейшем можно будет смотреть динамику роста топлива, а при помощи
                                автоматического подсчета
                                расхода вашего автомобиля, будет показано среднее расстояние, которое может преодолеть
                                автомобиль.</p></small>
                            <small><p>Все это делается для подсчета вашего бюджета и контроля затрат и конечно позволяет
                                предположить вам,
                                сколько необходимо вам потратить на обслуживание в следующем месяце или даже году.</p>
                            </small>
                        </ul>
                    </div>
                </nav>
            </aside>
            <main class="col offset-md-0 bg-faded py-0">

                <c:url value="/car/title" var="createCarName"/>

                <c:if test="${user.cars.size()==0}">
                    <br>
                    <p>В вашем списке нет машин, <a href="${createCarName}">добавить?</a></p>
                </c:if>

                <c:if test="${user.cars.size()!=0}">
                    <div class="container">
                        <div class="row col-md-8 col-md-offset-2">
                            <h3>${user.firstName} выберите машину:</h3>
                            <table class="table table-striped">
                                <c:forEach items="${carList}" var="carsList">
                                    <tr>
                                        <c:url value="car/view/${carsList.id}" var="cars"/>
                                        <c:url value="car/delete/${carsList.id}" var="carDelete"/>
                                        <c:url value="/car/edit/${carsList.id}" var="editCar"/>
                                        <td><a href="${cars}"><h4>${carsList.nameCar}</h4></a></td>
                                        <td></td>
                                        <td class="text-right">
                                            <a class='btn btn-info btn-xs' href="${editCar}">Изменить</a>
                                            <a href="${carDelete}" class="btn btn-danger btn-xs">Удалить</a></td>
                                    </tr>
                                </c:forEach>

                            </table>
                            <a href="${createCarName}" class="btn btn-primary btn-xs pull-right"><b>+</b> Добавить
                                автомобиль</a>
                            <span class="text-info text-right"
                                  style="margin-left: 70px; font-size: 120%">Количество машин: ${carsCount}</span>
                            <div class="container text-center">
                                <c:if test="${pagesCount > 1}">
                                    <c:set value="disabled" var="disabled"/>
                                    <c:set value="" var="active"/>
                                    <c:url value="/" var="url">
                                        <c:param name="page" value="1"/>
                                    </c:url>
                                    <a class="${page == 1 ? disabled : active}" href="${url}">
                                        &nbsp<span class="text-info">первая</span> &nbsp
                                    </a>
                                    <%--<c:url value="/" var="url">--%>
                                    <%--<c:param name="page" value="${page - 1}"/>--%>
                                    <%--</c:url>--%>
                                    <%--<a class="${page == 1 ? disabled : active}" href="${url}">--%>
                                    <%--&nbsp<span class="text-info">&larr;</span>&nbsp--%>
                                    <%--</a>--%>

                                    <c:if test="${pagesCount <= 5}">
                                        <c:set var="begin" value="1"/>
                                        <c:set var="end" value="${pagesCount}"/>
                                    </c:if>
                                    <c:if test="${pagesCount > 5}">
                                        <c:choose>
                                            <c:when test="${page < 3}">
                                                <c:set var="begin" value="1"/>
                                                <c:set var="end" value="5"/>
                                            </c:when>
                                            <c:when test="${page > pagesCount - 2}">
                                                <c:set var="begin" value="${pagesCount - 4}"/>
                                                <c:set var="end" value="${pagesCount}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="begin" value="${page - 2}"/>
                                                <c:set var="end" value="${page + 2}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                    <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                                        <c:url value="/" var="url">
                                            <c:param name="page" value="${i.index}"/>
                                        </c:url>
                                        <c:set value="current-page" var="current"/>
                                        <c:set value="" var="perspective"/>
                                        <a class="${page == i.index ? current : perspective}"
                                           href="${url}"> ${i.index} </a>
                                    </c:forEach>

                                    <%--<c:url value="/" var="url">--%>
                                    <%--<c:param name="page" value="${page + 1}"/>--%>
                                    <%--</c:url>--%>
                                    <%--<a class="${page == pagesCount ? disabled : active}" href="${url}">--%>
                                    <%--&nbsp<span class="text-info">&rarr;</span>&nbsp--%>
                                    <%--</a>--%>
                                    <c:url value="/" var="url">
                                        <c:param name="page" value="${pagesCount}"/>
                                    </c:url>
                                    <a class="${page == pagesCount ? disabled : active}" href="${url}">
                                        &nbsp<span class="text-info">последняя</span>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>

            </main>
        </div>
    </div>
</form:form>

<%@include file="include/under.jsp" %>