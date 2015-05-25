<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="header">

	<h1> 
		Art <i>Portal</i>
		
	</h1>
	
		<div class="fright">
			 <a href="?lang=ru"><img class="lang" src="${pageContext.request.contextPath}/resources/css/images/ru.jpg" title ="ru" alt="ru"/></a> 
			 <a href="?lang=en"><img class="lang" src="${pageContext.request.contextPath}/resources/css/images/en.jpg" title ="en" alt="en"/></a>
		</div>
	<hr />
	<h5> 
		<spring:message code="you_came" text="You came us " />
		<c:if test="${login != null}">
			<b><c:out value=" ${sessionScope.login}" /></b>
		</c:if>
		<c:if test="${login == null}">
			<b><spring:message code="guest"/></b>
		</c:if>
		
		<div class="fright">
		
		<c:if test="${login == null}">
			<a href="loginForm" ><spring:message code="login_menu"/></a> |
			<a href="registerForm" ><spring:message code="register_menu"/></a>  
		</c:if>
		<c:if test="${login != null}">
			<a href="logout" ><spring:message code="logout_menu"/></a> |
			<a href="account" ><spring:message code="account_menu"/></a>  
		</c:if>
			
		
			
		 </div>
	</h5>	
</div>
