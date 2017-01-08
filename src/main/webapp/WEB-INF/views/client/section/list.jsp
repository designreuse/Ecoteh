<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(sections_list)}"/>
<c:if test="${length gt 0}">
    <c:set var="desc_length" value="350"/>
    <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
    <c:if test="${(print_sections eq null) or (print_sections gt length) or (print_sections le 0)}">
        <c:set var="print_sections" value="${length}"/>
    </c:if>
    <c:choose>
        <c:when test="${(print_sections % 3 eq 0) or (print_sections % 3 eq 2)}">
            <c:set var="in_line" value="3"/>
        </c:when>
        <c:when test="${(print_sections % 2 eq 0) or (print_sections % 2 eq 1)}">
            <c:set var="in_line" value="2"/>
        </c:when>
        <c:otherwise><c:set var="in_line" value="1"/></c:otherwise>
    </c:choose>
    <c:set var="last_line" value="${print_sections - print_sections % in_line}"/>
    <c:set var="printed_in_line" value="0"/>
    <c:set var="printed" value="0"/>
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${sections_list}" var="section" end="${print_sections - 1}">
                    <c:if test="${(last_line ne print_sections) and (printed eq last_line)}">
                        <c:set var="in_line" value="${print_sections - last_line}"/>
                    </c:if>
                    <div class="col-xs-12 col-sm-12 <c:choose>
                                    <c:when test="${in_line eq 1}">col-md-12 col-lg-12 col-xl-12</c:when>
                                    <c:when test="${in_line eq 2}">col-md-6 col-lg-6 col-xl-6</c:when>
                                    <c:otherwise>col-md-4 col-lg-4 col-xl-4</c:otherwise>
                                    </c:choose>">
                        <a href="<c:url value="${reqmap}/section/${section.url}"/>"
                           title="Перейти к разделу &quot;<c:out value="${section.title}"/>&quot;">
                            <c:if test="${section.photo ne null}">
                                <img class="img-responsive img-in-list" alt="<c:out value="${section.title}"/>"
                                     src="<c:url value="/resources/img/${section.photo.url}"/>">
                            </c:if>
                            <h3 class="text-center"><c:out value="${section.title}"/></h3>
                        </a>
                        <c:if test="${!section.validated}">
                            <p class="little">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${section.description ne null}">
                            <c:choose>
                                <c:when test="${fn:length(section.description) gt desc_length}">
                                    <p><c:out value="${fn:substring(section.description, 0, desc_length)}"/>...</p>
                                </c:when>
                                <c:otherwise><p><c:out value="${section.description}"/></p></c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <c:set var="printed" value="${printed + 1}"/>
                    <c:set var="printed_in_line" value="${printed_in_line + 1}"/>
                    <c:if test="${printed_in_line eq in_line}">
                        <c:if test="${length gt printed}">
                            <div class="hidden-xs hidden-sm col-md-12 col-lg-12 col-xl-12">
                                <hr>
                            </div>
                        </c:if>
                        <c:set var="printed_in_line" value="0"/>
                    </c:if>
                </c:forEach>
                <c:if test="${length gt print_sections}">
                    <div class="col-xs-12 col-sm-12 hidden-md hidden-lg hidden-xl">
                        <hr>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <p align="right">
                            <a href="<c:url value="${reqmap}/section/all"/>" title="Перейти к списку всех разделов">
                                <span class="glyphicon glyphicon-share-alt"
                                      aria-hidden="true"></span>&nbsp;Все разделы...
                            </a>
                        </p>
                    </div>
                </c:if>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
