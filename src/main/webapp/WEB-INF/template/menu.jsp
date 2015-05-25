<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="menu">

    <ul>
        <li>
            <spring:url value="/test" var="homeUrl" htmlEscape="true"/>
            <a class="menuView" href="${homeUrl}"><spring:message code="home"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/about" var="aboutUrl" htmlEscape="true"/>
            <a class="menuView" href="${aboutUrl}"><spring:message code="about"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/artworks" var="artworks" htmlEscape="true"/>
            <a class="menuView" href="${artworks}"><spring:message code="artworks"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/competitions" var="competitions" htmlEscape="true"/>
            <a class="menuView" href="${competitions}"><spring:message code="competitions"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/contacts" var="contacts" htmlEscape="true"/>
            <a class="menuView" href="${contacts}"><spring:message code="contacts"/></a>
        </li>
    </ul>
    <br />
    <c:if test="${user.role.name=='admin'}"> 
    <hr />
     <h2><small><spring:message code="administration"/></small></h2>
       <ul>
        <li>
            <spring:url value="/adminUsers" var="adminUsers" htmlEscape="true"/>
            <a class="menuView" href="${adminUsers}"><spring:message code="users"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/adminUsersFind" var="adminUsersFind" htmlEscape="true"/>
            <a class="menuView" href="${adminUsersFind}"><spring:message code="find_user"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/notReady" var="notReady" htmlEscape="true"/>
            <a class="menuView" href="${notReady}"><spring:message code="works"/></a>
        </li>
        <br />
        <li>
            <spring:url value="/notReady" var="notReady" htmlEscape="true"/>
            <a class="menuView" href="${notReady}"><spring:message code="competitions"/></a>
        </li>
       </ul>
     </c:if>
     
     
</div></html>