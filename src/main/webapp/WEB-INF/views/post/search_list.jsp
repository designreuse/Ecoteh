<%--
Posts list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="length" value="${fn:length(posts_list)}"/>
<c:if test="${length gt 0}">
    <%-- How many posts in the each line --%>
    <c:choose>
        <c:when test="${(length % 3 eq 0) or (length % 3 eq 2)}">
            <c:set var="in_line" value="3"/>
        </c:when>
        <c:when test="${(length % 2 eq 0) or (length % 2 eq 1)}">
            <c:set var="in_line" value="2"/>
        </c:when>
        <c:otherwise>
            <c:set var="in_line" value="1"/>
        </c:otherwise>
    </c:choose>
    <%-- How many posts in the last line --%>
    <c:set var="last_line" value="${length - length % in_line}"/>
    <c:set var="printed_in_line" value="0"/>
    <c:set var="printed" value="0"/>

    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <c:forEach items="${posts_list}" var="post">
                        <%-- Articles in the last line --%>
                        <c:if test="${(last_line ne length ) and (printed eq last_line)}">
                            <c:set var="in_line" value="${length - last_line}"/>
                        </c:if>
                        <%-- Div CSS class and price class --%>
                        <c:choose>
                            <c:when test="${in_line eq 1}">
                                <c:set var="div_class" value="col-xs-12 col-sm-12 col-md-12 col-lg-12"/>
                                <c:set var="price_class" value="price-col-12"/>
                            </c:when>
                            <c:when test="${in_line eq 2}">
                                <c:set var="div_class" value="col-xs-12 col-sm-12 col-md-6 col-lg-6"/>
                                <c:set var="price_class" value="price-col-6"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="div_class" value="col-xs-12 col-sm-12 col-md-4 col-lg-4"/>
                                <c:set var="price_class" value="price-col-4"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="${div_class}">
                            <c:choose>
                                <c:when test="${not empty post.description}">
                                    <c:set var="title" value="${post.title} - ${post.description}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="title" value="${post.title}"/>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${not empty post.logo.url}">
                                <a href="<c:url value="/post/${post.url}"/>" title="<c:out value="${title}"/>">
                                    <img class="img-in-list" alt="<c:out value="${post.title}"/>"
                                         src="<c:url value="${post.logo.url}"/>" onerror="this.src='<c:url
                                            value="/resources/img/static/default_file.gif"/>'">
                                </a>
                            </c:if>
                            <h6 class="little text-center">
                                <c:if test="${!post.validated}">
                                    <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                          title="Не отображается для клиентов"></span>&nbsp;
                                </c:if>
                                <c:out value="${post.dateToString}"/>
                            </h6>
                            <a href="<c:url value="/post/${post.url}"/>" title="<c:out value="${title}"/>">
                                <h4 class="text-center">
                                    <c:out value="${post.title}"/>
                                </h4>
                            </a>
                                <%-- Administrator actions --%>
                            <c:if test="${authorized_user ne null}">
                                <h3 class="text-center">
                                    <a href="<c:url value="/admin/post/edit/${post.url}"/>"
                                       title="Редактировать статью &quot;<c:out value="${post.title}"/>&quot;">
                                        <button class="btn btn-default">
                                            <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        </button>
                                    </a>
                                    &nbsp;&nbsp;
                                    <a href="<c:url value="/admin/post/delete/${post.url}"/>"
                                       title="Удалить пост &quot;<c:out value="${post.title}"/>&quot;"
                                       onclick="if(confirm('Вы точно хотите удалить пост &quot;<c:out
                                               value="${post.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                        <button class="btn btn-default">
                                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        </button>
                                    </a>
                                </h3>
                            </c:if>
                        </div>
                        <c:set var="printed" value="${printed + 1}"/>
                        <c:set var="printed_in_line" value="${printed_in_line + 1}"/>
                        <c:if test="${printed_in_line eq in_line}">
                            <c:if test="${length gt printed}">
                                <div class="hidden-xs hidden-sm col-md-12 col-lg-12">&nbsp;</div>
                            </c:if>
                            <c:set var="printed_in_line" value="0"/>
                        </c:if>
                    </c:forEach>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</c:if>
