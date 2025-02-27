<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><fmt:message key="user.edit.title"/></title>
    <style type="text/css" media="screen">@import "${pageContext.request.contextPath}/style.css";</style>
</head>
<body>

<div id="container">

    <%-- banner navigation--%>
    <%@ include file="includes/topNavLimited.jsp" %>

    <%-- the logo banner --%>
    <%--<%@ include file="includes/logo.jsp" %>--%>

    <%-- The main content --%>
    <div id="mainBody">


        <div class="user-management-container">

            <%@ include file="includes/adminLinks.jsp" %>

            <form:form action="./editUser.do" method="post" commandName="userForm">

                <%-- USER DETAILS --%>

                <fieldset>
                    <legend><strong>User Details</strong></legend>

                    <p><fmt:message key="user.edit.details"/></p>

                    <table class="details-table">
                        <tr>
                            <td><strong><fmt:message key="user.username"/></strong></td>
                            <td><form:input path="username" readonly="true"/></td>
                            <td><form:errors path="username"/></td>
                        </tr>
                        <tr>
                            <td><strong><fmt:message key="user.name"/></strong></td>
                            <td><form:input path="name"/></td>
                            <td><form:errors path="name"/></td>
                        </tr>
                        <tr>
                            <td><strong><fmt:message key="user.email"/></strong></td>
                            <td><form:input path="email"/></td>
                            <td><form:errors path="email"/></td>
                        </tr>
                    </table>
                    <p>
                        <input type="submit" name="updateUser" value="Update"/>
                        <input type="submit" value="Cancel"/>
                    </p>
                </fieldset>

                <%-- REMOVE GROUPS --%>

                <c:if test="${not empty userForm.userGroups}">
                    <fieldset>
                        <legend><strong>Groups assigned to this user</strong></legend>
                        <table>
                            <c:forEach var="userGroup" items="${userForm.userGroups}">
                                <tr>
                                    <td><form:radiobutton path="userGroupId"
                                                          value="${userGroup.groupId}"/></td>
                                    <td>${userGroup.groupId}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p>
                            <input type="submit" name="removeGroup" value="Remove"/>
                            <input type="submit" value="Cancel"/>
                        </p>
                    </fieldset>
                </c:if>


                <%-- ADD GROUPS --%>

                <c:if test="${not empty userForm.groups}">
                    <fieldset>
                        <legend><strong>Assign new groups to this user</strong></legend>
                        <p>
                            <form:select path="addGroupId" multiple="false">
                                <c:forEach var="group" items="${userForm.groups}">
                                    <form:option value="${group.groupId}"/>
                                </c:forEach>
                            </form:select>
                            <input type="submit" name="addGroup" value="Add"/>
                            <input type="submit" value="Cancel"/>
                        </p>
                    </fieldset>
                </c:if>

            </form:form>

        </div>

    </div>

    <%-- the logo banner --%>
<%@ include file="includes/footer.jsp" %>