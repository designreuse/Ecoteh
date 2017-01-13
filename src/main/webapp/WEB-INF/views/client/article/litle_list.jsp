<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(articles_list)}"/>
<c:if test="${length gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <hr>
                <h3 class="intro-text text-center">
                    <a href="<c:url value="${reqmap}/article/all"/>">Последние статьи</a>
                </h3>
                <hr>
                <c:set var="desc_length" value="350"/>
                <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
                <c:if test="${(print_articles eq null) or (print_articles gt length) or (print_articles le 0)}">
                    <c:set var="print_articles" value="${length}"/>
                </c:if>
                <c:choose>
                    <c:when test="${(print_articles % 3 eq 0) or (print_articles % 3 eq 2)}">
                        <c:set var="in_line" value="3"/>
                    </c:when>
                    <c:when test="${(print_articles % 2 eq 0) or (print_articles % 2 eq 1)}">
                        <c:set var="in_line" value="2"/>
                    </c:when>
                    <c:otherwise><c:set var="in_line" value="1"/></c:otherwise>
                </c:choose>
                <c:set var="last_line" value="${print_articles - print_articles % in_line}"/>
                <c:set var="printed_in_line" value="0"/> <c:set var="printed" value="0"/>
                <c:forEach items="${articles_list}" var="article" end="${print_articles - 1}">
                    <c:if test="${(last_line ne print_articles) and (printed eq last_line)}">
                        <c:set var="in_line" value="${print_articles - last_line}"/>
                    </c:if>
                    <div class="col-xs-12 col-sm-12 <c:choose>
                                    <c:when test="${in_line eq 1}">col-md-12 col-lg-12 col-xl-12</c:when>
                                    <c:when test="${in_line eq 2}">col-md-6 col-lg-6 col-xl-6</c:when>
                                    <c:otherwise>col-md-4 col-lg-4 col-xl-4</c:otherwise>
                                    </c:choose>">
                        <a href="<c:url value="${reqmap}/article/${article.url}"/>"
                           title="Перейти к статьи &quot;<c:out value="${article.title}"/>&quot;">
                            <c:if test="${article.mainPhoto ne null}">
                                <img class="img-responsive img-in-list" alt="<c:out value="${article.title}"/>"
                                     src="<c:url value="/resources/${article.mainPhoto.url}"/>">
                            </c:if>
                            <h3 class="text-center"><c:out value="${article.title}"/></h3>
                        </a>
                        <p class="little">
                            <c:if test="${!article.validated}">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </c:if>
                            <c:out value="${article.dateToString}"/>,&nbsp;&nbsp;Артикль:
                            <a href="<c:url value="${reqmap}/article/num_${article.number}"/>">
                                <c:out value="${article.number}"/>
                            </a>
                        </p>
                        <c:choose>
                            <c:when test="${article.description ne null}"><p>${article.description}</p></c:when>
                            <c:when test="${article.text ne null}"><p>${article.text}</p></c:when>
                        </c:choose>
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
                <c:if test="${length gt print_articles}">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr class="hidden-md hidden-lg hidden-xl">
                        <p class="text-right">
                            <a href="<c:url value="${reqmap}/article/all"/>" title="Перейти к списку всех статьям">
                                <span class="glyphicon glyphicon-share-alt"
                                      aria-hidden="true"></span>&nbsp;Все статьи...
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
