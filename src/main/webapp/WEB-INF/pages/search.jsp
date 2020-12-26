<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="include/head.jsp" %>

<c:if test="${(carsList.size() != 0) && (docs.size() == 0) && (costs.size() == 0) && (repairs.size() == 0)}">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col" class="text-center">Название машины</th>
        <th scope="col" class="text-center">Пробег</th>
    </tr>
    </thead>
    <c:forEach var="car" items="${carsList}">
    <tbody>
    <tr>
        <th scope="row" class="text-center text-info">${car.nameCar}</th>
        <th scope="row" class="text-center text-info">${car.mileage}</th>
    </tr>
    </tbody>
    </c:forEach>
</table>
    <div class="container text-center">
        <c:if test="${countPageCars > 1}">
            <c:set value="disabled" var="disabled"/>
            <c:set value="" var="active"/>
            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="1"/>
            </c:url>

            <a class="${page == 1 ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">первая</span> &nbsp
            </a>

            <c:if test="${countPageCars <= 5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${countPageCars}"/>
            </c:if>
            <c:if test="${countPageCars > 5}">
                <c:choose>
                    <c:when test="${page < 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    <c:when test="${page > countPageCars - 2}">
                        <c:set var="begin" value="${countPageCars - 4}"/>
                        <c:set var="end" value="${countPageCars}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${page - 2}"/>
                        <c:set var="end" value="${page + 2}"/>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                <c:url value="/search/${searchText}" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <c:set value="current-page" var="current"/>
                <c:set value="" var="perspective"/>
                <a class="${page == i.index ? current : perspective}"
                   href="${url}"> ${i.index} </a>
            </c:forEach>

            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="${countPageCars}"/>
            </c:url>
            <a class="${page == countPageCars ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">последняя</span>
            </a>
        </c:if>
    </div>
</c:if>

<c:if test="${docs.size() != 0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" class="text-center">Машина</th>
            <th scope="col" class="text-center">Название документа</th>
            <th scope="col" class="text-center">Дата начала</th>
            <th scope="col" class="text-center">Дата окончания</th>
        </tr>
        </thead>
        <c:forEach var="doc" items="${docs}">
            <tbody>
            <tr>
                <th scope="row" class="text-center text-info">${doc.car.nameCar}</th>
                <th scope="row" class="text-center text-info">${doc.nameDocument}</th>
                <th scope="row" class="text-center text-info">${doc.beginDate}</th>
                <th scope="row" class="text-center text-info">${doc.endDate}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <div class="container text-center">
        <c:if test="${countPageDocs > 1}">
            <c:set value="disabled" var="disabled"/>
            <c:set value="" var="active"/>
            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="1"/>
            </c:url>

            <a class="${page == 1 ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">первая</span> &nbsp
            </a>

            <c:if test="${countPageDocs <= 5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${countPageDocs}"/>
            </c:if>
            <c:if test="${countPageDocs > 5}">
                <c:choose>
                    <c:when test="${page < 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    <c:when test="${page > countPageDocs - 2}">
                        <c:set var="begin" value="${countPageDocs - 4}"/>
                        <c:set var="end" value="${countPageDocs}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${page - 2}"/>
                        <c:set var="end" value="${page + 2}"/>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                <c:url value="/search/${searchText}" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <c:set value="current-page" var="current"/>
                <c:set value="" var="perspective"/>
                <a class="${page == i.index ? current : perspective}"
                   href="${url}"> ${i.index} </a>
            </c:forEach>

            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="${countPageDocs}"/>
            </c:url>
            <a class="${page == countPageDocs ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">последняя</span>
            </a>
        </c:if>
    </div>
</c:if>

<c:if test="${costs.size() != 0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" class="text-center">Машина</th>
            <th scope="col" class="text-center">Название затрат</th>
            <th scope="col" class="text-center">Дата</th>
            <th scope="col" class="text-center">Стоимость</th>
        </tr>
        </thead>
        <c:forEach var="cost" items="${costs}">
            <tbody>
            <tr>
                <th scope="row" class="text-center text-info">${cost.car.nameCar}</th>
                <th scope="row" class="text-center text-info">${cost.nameOtherCost}</th>
                <th scope="row" class="text-center text-info">${cost.dateCost}</th>
                <th scope="row" class="text-center text-info">${cost.cost}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <div class="container text-center">
        <c:if test="${countPageOtherCosts > 1}">
            <c:set value="disabled" var="disabled"/>
            <c:set value="" var="active"/>
            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="1"/>
            </c:url>

            <a class="${page == 1 ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">первая</span> &nbsp
            </a>

            <c:if test="${countPageOtherCosts <= 5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${countPageOtherCosts}"/>
            </c:if>
            <c:if test="${countPageOtherCosts > 5}">
                <c:choose>
                    <c:when test="${page < 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    <c:when test="${page > countPageOtherCosts - 2}">
                        <c:set var="begin" value="${countPageOtherCosts - 4}"/>
                        <c:set var="end" value="${countPageOtherCosts}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${page - 2}"/>
                        <c:set var="end" value="${page + 2}"/>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                <c:url value="/search/${searchText}" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <c:set value="current-page" var="current"/>
                <c:set value="" var="perspective"/>
                <a class="${page == i.index ? current : perspective}"
                   href="${url}"> ${i.index} </a>
            </c:forEach>

            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="${countPageOtherCosts}"/>
            </c:url>
            <a class="${page == countPageOtherCosts ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">последняя</span>
            </a>
        </c:if>
    </div>
</c:if>

<c:if test="${repairs.size() != 0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" class="text-center">Машина</th>
            <th scope="col" class="text-center">Название ремонта</th>
            <th scope="col" class="text-center">Пробег при ремонте</th>
            <th scope="col" class="text-center">Пробег по гарантии</th>
        </tr>
        </thead>
        <c:forEach var="repair" items="${repairs}">
            <tbody>
            <tr>
                <th scope="row" class="text-center text-info">${repair.car.nameCar}</th>
                <th scope="row" class="text-center text-info">${repair.nameRepair}</th>
                <th scope="row" class="text-center text-info">${repair.beginMileage}</th>
                <th scope="row" class="text-center text-info">${repair.serviceLife}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <div class="container text-center">
        <c:if test="${countPageRepair > 1}">
            <c:set value="disabled" var="disabled"/>
            <c:set value="" var="active"/>
            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="1"/>
            </c:url>

            <a class="${page == 1 ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">первая</span> &nbsp
            </a>

            <c:if test="${countPageRepair <= 5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${countPageRepair}"/>
            </c:if>
            <c:if test="${countPageRepair > 5}">
                <c:choose>
                    <c:when test="${page < 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    <c:when test="${page > countPageRepair - 2}">
                        <c:set var="begin" value="${countPageRepair - 4}"/>
                        <c:set var="end" value="${countPageRepair}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${page - 2}"/>
                        <c:set var="end" value="${page + 2}"/>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                <c:url value="/search/${searchText}" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <c:set value="current-page" var="current"/>
                <c:set value="" var="perspective"/>
                <a class="${page == i.index ? current : perspective}"
                   href="${url}"> ${i.index} </a>
            </c:forEach>

            <c:url value="/search/${searchText}" var="url">
                <c:param name="page" value="${countPageRepair}"/>
            </c:url>
            <a class="${page == countPageRepair ? disabled : active}" href="${url}">
                &nbsp<span class="text-info">последняя</span>
            </a>
        </c:if>
    </div>
</c:if>

    <div id="register-link" class="text-left">
    <br>
    <a href="/" class="text-info">В личный кабинет</a>
    </div>

<%@ include file="include/under.jsp" %>