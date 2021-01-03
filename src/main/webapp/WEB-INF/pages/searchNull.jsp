<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp" %>
<div>
    &nbsp;
</div>
<h2 align="center">К сожалению ничего не найдено, попробуйте изменить параметры поиска</h2>
<div>
    &nbsp;
</div>

<div class="container col-6">
    <form class="form-horizontal" action="/search">
        <div class="input-group">
            <!--Text input-->
            <label class="control-label" for="search"></label>
            <input id="search" name="searchText" type="text" class="form-control input-md" placeholder="Поиск"/>
            <span class="input-group-btn">
        <button type="submit" class="btn btn-outline-success">Search</button>
        </span>
        </div>
    </form>
</div>

<br>
<div id="register-link" class="text-center">
    <a href="#" onclick="history.back();">Вернуться назад</a>
</div>
<%@ include file="include/under.jsp" %>