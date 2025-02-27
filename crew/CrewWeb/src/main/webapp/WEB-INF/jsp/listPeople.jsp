<%-- dtd and xml declaration --%>
<%@ include file="includes/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="crew" uri="http://www.crew_vre.net/taglib" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><spring:message code="people.list.title"/></title>
    <style type="text/css" media="screen">@import "./style.css";</style>
</head>
<body>

<div id="container">

<%-- banner navigation--%>
<%@ include file="includes/topNavAll.jsp" %>


<%-- the logo banner --%>
<%--<%@ include file="includes/logo.jsp" %>--%>

<%-- The main content --%>
<div id="mainBody">

<%-- The left column: navigation --%>
<div id="leftColumn">

    <%-- quick links --%>
    <%-- <%@ include file="includes/quickLinks.jsp" %> -->

    <%-- the facets ---%>
    <div class="bl">
        <div class="br">
            <div class="tl">
                <div class="tr">
                    <div class="box" id="facetNavigation">
                        <h4 class="box-header"><spring:message code="facet.title"/></h4>
                        <c:forEach var="facet" items="${facets}">
                            <crew:facet facet="${facet}" showEmpty="false" url="${url}"
                                        parameters="${parameters}"/>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- The right column: RSS Feeds etc --%>
<div id="rightColumn">

    <%-- quick links --%>
    <%@ include file="includes/box-aboutCrew.jsp" %>

</div>

<!-- Middle column: main content -->
<div id="middleColumn">


    <%-- display info about the number of results --%>
    <div class="resultDetails">
        <p>
            <c:choose>
                <c:when test="${total == 1}">
                    <spring:message code="people.total.one"/>
                </c:when>
                <c:otherwise>
                    <spring:message code="people.total" arguments="${total}"/>
                </c:otherwise>
            </c:choose>

            <%-- show navigation stuff? --%>
            <c:choose>
            <c:when test="${total > 0}">
                <%-- navigation message --%>
            <%@ include file="includes/navMessage.jsp" %>
        </p>
            <%-- provide navigation if there is more than one page --%>
        <c:if test="${nav.totalPages > 1}">
            <crew:nav navHelper="${nav}" params="${parameters}" className="pagination"/>
        </c:if>
        </c:when>
        <c:otherwise>
            </p>
        </c:otherwise>
        </c:choose>
    </div>

    <%-- the results table --%>
    <c:if test="${not empty listPeople}">
        <c:forEach var="person" items="${listPeople}" varStatus="rowNum">
            <c:choose>
                <c:when test="${rowNum.count % 2 == 0}">
                    <div class="rowEven">
                 </c:when>
                <c:otherwise>
                    <div class="rowOdd">
                </c:otherwise>
             </c:choose>
             <c:choose>
                 <c:when test="${not empty person.id}">
                     <h5 class="resultHeader"><a href="./displayPerson.do?personId=<crew:uri uri='${person.id}'/>">${person.name}</a></h5>
                 </c:when>
                 <c:otherwise>
                     <h5 class="resultHeader">Sorry, you are not authorized to view the person details.</h5>
                 </c:otherwise>
             </c:choose>

            </div>
        </c:forEach>
    </c:if>

    <%-- provide navigation if there is more than one page --%>
    <c:if test="${nav.totalPages > 1}">
        <div class="pagination-bottom">
            <crew:nav navHelper="${nav}" params="${parameters}" className="pagination"/>
        </div>
    </c:if>

</div>
</div>


<%-- the logo banner --%>
<%@ include file="includes/footer.jsp" %>