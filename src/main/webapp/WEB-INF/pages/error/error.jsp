<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			
			<h2 align="center">
					<c:out value="Some exception here :"></c:out>
					<c:out value="${exception.toString()}"></c:out>
			</h2>
			
			<h2 align="center">
 				<c:out value="${exception.printStackTrace()}"></c:out>
			</h2>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>