<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>

<div class="container-fluid h-100">
    <div class="row h-100">
        <aside class="col-12 col-md-2 p-0 bg-light">
            <nav class="navbar navbar-expand navbar-light bg-light flex-md-column flex-row align-items-start py-0">
                <div class="collapse navbar-collapse align-items-start">
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
                        <form:form modelAttribute="registration">
                            <b>Цена авто и затраты на оформление:</b>
                            <br>
                            <c:if test="${registration!=null}">
                                <li class="nav-item">
                                    <small>Цена Авто:<b> ${registration.priceCar} $</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Оформление:<b> ${registration.priceRegistration} $</b></small>
                                    <br>
                                </li>
                                <c:url value="/car/costs/edit/${car.id}" var="editFirst"/>
                                <li class="nav-item">
                                    <a class="nav-link pl-0" href="${editFirst}"><i class="fa fa-list fa-fw"></i> <span
                                            class="d-none d-md-inline"><svg width="1em" height="1em" viewBox="0 0 16 16"
                                                                            class="bi bi-pencil-fill"
                                                                            fill="currentColor"
                                                                            xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd"
              d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
    </svg> Редактировать</span></a>
                                </li>
                            </c:if>

                            <c:if test="${registration==null}">
                                <c:url value="/car/costs/first/${car.id}" var="createFirst"/>
                                <p>Затрат нет
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-frown"
                                         fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd"
                                              d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path fill-rule="evenodd"
                                              d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
                                        <path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
                                    </svg>
                                <li class="nav-item">
                                    <a class="nav-link pl-0" href="${createFirst}"><i class="fa fa-list fa-fw"></i>
                                        <span
                                                class="d-none d-md-inline">добавить?</span></a>
                                </li>
                                </p>
                            </c:if>
                        </form:form>
                        <br>
                        <form:form modelAttribute="parameter">
                            <b>Параметры авто:</b>
                            <br>
                            <c:if test="${parameter!=null}">
                                <li class="nav-item">
                                    <small>Марка:<b> ${parameter.mark}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Модель:<b> ${parameter.model}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Год выпуска:<b> ${parameter.year}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Пробег до:<b> ${parameter.firstMileage} км</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Масса:<b> ${parameter.mass} кг</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Цвет:<b> ${parameter.color}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Средний расход:<b> ${parameter.averageRate} л</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>VIN:<b> ${parameter.vin}</b></small>
                                    <br>
                                </li>
                                <li class="nav-item">
                                    <small>Гос.номер:<b> ${parameter.registrationNumber}</b></small>
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

                            <c:if test="${parameter==null}">
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
            <h2>Выбирете раздел:</h2>
            <br>
            <div class="row">
                <c:url value="/car/documents/${car.id}" var="documents"/>
                <div class="col-md-3">
                    <div class="card border-info mx-auto mb-3" style="max-width: 300px;">
                        <div class="card-header text-center" style="background-color: #77a4ff">
                            <b>Документы</b>
                        </div>
                        <div class="card-body" style="background-color: #d9eeff">
                            <p class="card-text">В разделе, отображены затраты на документы.</p>
                            <a href="${documents}" class="btn btn-info btn-md">Перейти</a>
                        </div>
                    </div>
                </div>

                <c:url value="/car/repairs/${car.id}" var="repairs"/>
                <div class="col-md-3">
                    <div class="card mx-auto border-danger mb-3" style="max-width: 300px;">
                        <div class="card-header text-center" style="background-color: #77a4ff">
                            <b>Ремонты</b>
                        </div>
                        <div class="card-body" style="background-color: #d9eeff">
                            <p class="card-text">В разделе, отображены затраты на ремонт.</p>
                            <a href="${repairs}" class="btn btn-info btn-md">Перейти</a>
                        </div>
                    </div>
                </div>

                <c:url value="/car/fuel/${car.id}" var="fuel"/>
                <div class="col-md-3">
                    <div class="card border-warning mx-auto mb-3" style="max-width: 300px;">
                        <div class="card-header text-center" style="background-color: #77a4ff">
                            <b>Топливо</b>
                        </div>
                        <div class="card-body" style="background-color: #d9eeff">
                            <p class="card-text">В разделе, отображены затраты на топливо.</p>
                            <a href="${fuel}" class="btn btn-info btn-md">Перейти</a>
                        </div>
                    </div>
                </div>

                <c:url value="/car/other/costs/${car.id}" var="costs"/>
                <div class="col-md-3">
                    <div class="card border-success mx-auto mb-3" style="max-width: 300px;">
                        <div class="card-header text-center" style="background-color: #77a4ff">
                            <b>Остальные расходы</b>
                        </div>
                        <div class="card-body" style="background-color: #d9eeff">
                            <p class="card-text" >В разделе отображены остальные расходы.</p>
                            <a href="${costs}" class="btn btn-info btn-md">Перейти</a>
                        </div>
                    </div>
                </div>

            </div>
            <div id="register-link" class="text-left">
                <br>
                <a href="/" class="text-info">В личный кабинет</a>
            </div>
        </main>
    </div>
</div>

<%@ include file="../include/under.jsp" %>