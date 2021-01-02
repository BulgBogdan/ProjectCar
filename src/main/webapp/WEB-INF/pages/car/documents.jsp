<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/head.jsp" %>


<div class="container-fluid h-100">
    <div class="row h-100">
        <aside class="col-12 col-md-2 p-0 bg-light">
            <nav class="navbar navbar-expand navbar-light bg-light flex-md-column flex-row align-items-start py-0">
                <div class="collapse navbar-collapse align-items-start" style="height: 100vh">
                    <ul class="flex-md-column flex-row navbar-nav w-100 justify-content-between">
                        <form:form modelAttribute="car">
                            <b>${car.nameCar}</b>

                            <li class="nav-item">
                                <small>Пробег:<b> ${car.mileage} км.</b></small>
                                <br>
                            </li>
                            <c:url value="/car/edit/${car.id}" var="editCar"/>
                            <li class="nav-item">
                                <a class="nav-link pl-0" href="${editCar}"><i class="fa fa-list fa-fw"></i> <span
                                        class="d-none d-md-inline"><svg width="1em" height="1em" viewBox="0 0 16 16"
                                                                        class="bi bi-pencil-fill" fill="currentColor"
                                                                        xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd"
              d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
    </svg> Редактировать</span></a>
                            </li>
                        </form:form>
                        <br>

                        <form:form modelAttribute="parameters">
                            <b>Параметры авто:</b>
                            <br>
                            <c:if test="${parameters!=null}">
                                <li class="nav-item">
                                    <small>Марка:<b> ${parameters.mark}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Модель:<b> ${parameters.model}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Год выпуска:<b> ${parameters.year}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Пробег до:<b> ${parameters.firstMileage} км</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Масса:<b> ${parameters.mass} кг</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Цвет:<b> ${parameters.color}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Средний расход:<b> ${parameters.averageRate} л</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>VIN:<b> ${parameters.vin}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Гос.номер:<b> ${parameters.registrationNumber}</b></small>
                                    <br>
                                </li>
                                <c:url value="/car/parameters/edit/${car.id}" var="editParameter"/>
                                <li class="nav-item">
                                    <a class="nav-link pl-0" href="${editParameter}"><i class="fa fa-list fa-fw"></i>
                                        <span
                                                class="d-none d-md-inline"><svg width="1em" height="1em"
                                                                                viewBox="0 0 16 16"
                                                                                class="bi bi-pencil-fill"
                                                                                fill="currentColor"
                                                                                xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd"
              d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
    </svg> Редактировать</span></a>
                                </li>
                            </c:if>

                            <c:if test="${parameters==null}">
                                <c:url value="/car/parameters/${car.id}" var="createParameter"/>
                                <p>Параметров нет
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-frown"
                                         fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd"
                                              d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path fill-rule="evenodd"
                                              d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
                                        <path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
                                    </svg>
                                <li class="nav-item">
                                    <a class="nav-link pl-0" href="${createParameter}"><i class="fa fa-list fa-fw"></i>
                                        <span
                                                class="d-none d-md-inline">добавить?</span></a>
                                </li>
                                </p>
                            </c:if>
                        </form:form>
                    </ul>
                </div>
            </nav>
        </aside>
        <main class="col offset-md-0 bg-faded py-0">
            <br>
            <c:url value="/car/documents/create/${car.id}" var="createDoc"/>
            <c:if test="${documents.size() != 0}">
                <div class="container">
                    <div class="row col-md-12 col-md-offset-0">
                        <table class="table table-striped">

                            <thead style="background-color: #77a4ff">
                            <tr>
                                <th class="text-center">Название</th>
                                <th class="text-center">Стоимость</th>
                                <th class="text-center">Дата начала</th>
                                <th class="text-center">Дата окончания</th>
                                <th class="text-center">Дней до окончания</th>
                                <th class="text-center">Месяцев до окончания</th>
                                <th class="text-center"></th>
                            </tr>
                            </thead>
                            <c:forEach items="${documents}" var="docs">
                                <tr>
                                    <td class="text-center">${docs.nameDocument}</td>
                                    <c:if test="${car.user.currency.id == 1}">
                                        <td class="text-center">
                                            <fmt:formatNumber value="${docs.documentCost}" maxFractionDigits="2"/> BYN
                                        </td>
                                    </c:if>
                                    <c:if test="${car.user.currency.id == 2}">
                                        <td class="text-center">
                                            <fmt:formatNumber value="${docs.documentCost / 2.6}" maxFractionDigits="2"/>
                                            USD
                                        </td>
                                    </c:if>
                                    <td class="text-center">${docs.beginDate}</td>
                                    <td class="text-center">${docs.endDate}</td>
                                    <td class="text-center">${docs.numberOfDays} дня(ей)</td>
                                    <td class="text-center">${docs.numberOfMonth} месяц(ев)</td>
                                    <c:url value="/car/documents/edit/${docs.id}" var="editDoc"/>
                                    <td class="text-center"><a class='btn btn-info btn-xs' href="${editDoc}">
                                        <svg width="1em" height="1em"
                                             viewBox="0 0 16 16"
                                             class="bi bi-pencil-fill"
                                             fill="currentColor"
                                             xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
                                                  d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                        </svg>
                                    </a>
                                        <c:url value="/car/documents/delete/${docs.id}" var="docDel"/>
                                        <a href="${docDel}" class="btn btn-danger btn-xs">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash"
                                                 fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                <path fill-rule="evenodd"
                                                      d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                            </svg>
                                        </a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <a href="${createDoc}" class="btn btn-primary btn-xs pull-right"><b>+</b> Добавить документ</a>
                        <span class="text-info text-right"
                              style="margin-left: 70px; font-size: 120%">Количество документов: ${documentsCount}</span>
                        <div class="container text-center">
                            <c:if test="${pagesCount > 1}">
                                <c:set value="disabled" var="disabled"/>
                                <c:set value="" var="active"/>
                                <c:url value="/car/documents/${car.id}" var="url">
                                    <c:param name="page" value="1"/>
                                </c:url>
                                <a class="${page == 1 ? disabled : active}" href="${url}">
                                    &nbsp<span class="text-info">первая</span> &nbsp
                                </a>

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
                                    <c:url value="/car/documents/${car.id}" var="url">
                                        <c:param name="page" value="${i.index}"/>
                                    </c:url>
                                    <c:set value="current-page" var="current"/>
                                    <c:set value="" var="perspective"/>
                                    <a class="${page == i.index ? current : perspective}"
                                       href="${url}"> ${i.index} </a>
                                </c:forEach>

                                <c:url value="/car/documents/${car.id}" var="url">
                                    <c:param name="page" value="${pagesCount}"/>
                                </c:url>
                                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                                    &nbsp<span class="text-info">последняя</span>
                                </a>
                            </c:if>
                        </div>
                    </div>
                    <div class="dropdown text-right">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle"
                           title="Нажмите, чтобы развернуть и посмотреть">
                            Сумма затрат на документы
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu text-center">
                            <b class="text-danger">${allDocumentsCosts} ${car.user.currency.title}</b>
                        </ul>
                    </div>
                </div>
            </c:if>
            <c:if test="${(parameters != null) && (documents.size() == 0)}">
                <br>
                <p>Документы небыли добавлены, <a href="${createDoc}"> добавить?</a></p>
            </c:if>
            <c:if test="${parameters == null}">
                <p>Вы не заполнили параметры автомобиля, <a href="/car/parameters/${car.id}"> заполнить?</a></p>
            </c:if>
            <div id="register-link">
                <a href="/car/view/${car.id}" class="text-info text-right">На страницу авто</a>
            </div>
            <div>&nbsp;</div>
        </main>
    </div>
</div>

<%@include file="../include/under.jsp" %>