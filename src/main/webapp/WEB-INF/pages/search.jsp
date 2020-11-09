<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${carsList.size() != 0}">
    <h1>Here Result!</h1>
    <table>
        <tr>
            <th>name</th>
        </tr>
        <c:forEach var="car" items="${carsList}">
            <tr>
                <td>${car.nameCar}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${carsList.size() == 0}">
    <h1>Here Nothing!</h1>
</c:if>

<c:url value="/" var="cabinet"/>
<a href="${cabinet}">My cabinet</a>