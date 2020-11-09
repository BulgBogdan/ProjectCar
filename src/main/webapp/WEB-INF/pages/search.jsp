<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${carsList.size() != 0}">
    <h1>Here Result!</h1>
    <table>
        <tr>
            <th>name CARs</th>
        </tr>
        <c:forEach var="car" items="${carsList}">
            <tr>
                <td>${car.nameCar}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${docs.size() != 0}">
    <table>
        <tr>
            <th>name DOCUMENTs</th>
        </tr>
        <c:forEach var="doc" items="${docs}">
            <tr>
                <td>${doc.nameDocument}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${costs.size() != 0}">
    <table>
        <tr>
            <th>name COSTs</th>
        </tr>
        <c:forEach var="cost" items="${costs}">
            <tr>
                <td>${cost.nameOtherCost}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${repairs.size() != 0}">
    <table>
        <tr>
            <th>name REPAIRs</th>
        </tr>
        <c:forEach var="repair" items="${repairs}">
            <tr>
                <td>${repair.nameRepair}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<c:url value="/" var="cabinet"/>
<a href="${cabinet}">My cabinet</a>