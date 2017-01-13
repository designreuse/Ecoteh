<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(slides_list) gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="gallery">
                    <hr>
                    <h3 class="intro-text text-center">Галерея</h3>
                    <hr>
                    <c:forEach items="${slides_list}" var="slide">
                        <c:if test="${slide ne null}">
                            <a href="<c:url value="/resources/${slide.url}"/>"
                               rel="lightgallery[slides]" title="<c:out value="${slide.title}"/>">
                                <img src="<c:url value="/resources/${slide.url}"/>"
                                     title="<c:out value="${slide.title}"/>" alt=""/>
                            </a>&nbsp;&nbsp;
                        </c:if>
                    </c:forEach>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
