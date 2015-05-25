<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			
			
			
				<c:if test="${not empty exception.errCode}">
					<h2><c:out value="${exception.errCode} : System Errors"></c:out></h2>
				</c:if>
				<c:if test="${empty exception.errCode}">
					<h2><c:out value="System Errors"></c:out></h2>
				</c:if>
				<br />
			<h1 align="center">
 				<small><c:out value="${exception.errMsg}"></c:out>
			</h1>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>