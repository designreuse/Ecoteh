<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(videos_list)}"/>
<c:if test="${length gt 0}">
    <c:if test="${(videos_in_line eq null) or (videos_in_line gt length) or (videos_in_line le 0)}">
        <c:choose>
            <c:when test="${length eq 1}"><c:set var="videos_in_line" value="1"/></c:when>
            <c:otherwise><c:set var="videos_in_line" value="2"/></c:otherwise>
        </c:choose>
    </c:if>
    <c:set var="count" value="${videos_in_line}"/>
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="gallery">
                    <hr>
                    <h3 class="intro-text text-center">Видео</h3>
                    <hr>
                    <c:forEach items="${videos_list}" var="video">
                        <c:if test="${video ne null}">
                            <div class="col-xs-12 col-sm-12
                                            <c:choose>
                                                <c:when test="${videos_in_line eq 1}">col-md-12 col-lg-12 col-xl-12</c:when>
                                                <c:otherwise>col-md-6 col-lg-6 col-xl-6</c:otherwise>
                                            </c:choose>">
                                <div align="center">
                                    <div align="center" class="video-frame">
                                        <iframe src="<c:out value="${video.url}"/>" allowfullscreen
                                                width="100%" height="300" frameborder="0"></iframe>
                                    </div>
                                </div>
                            </div>
                            <c:set var="count" value="${count - 1}"/>
                            <c:if test="${(count le 0) and (length gt videos_in_line)}">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                    <hr>
                                </div>
                                <c:set var="count" value="${videos_in_line}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
