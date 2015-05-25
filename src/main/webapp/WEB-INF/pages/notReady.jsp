 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">



		<div class="body">
			<h2>Sorry! This page is not ready yet :)</h2>
			<ul>
				<li><spring:url value="/test" var="homeUrl" htmlEscape="true" />
					<a href="${homeUrl}">Home</a></li>
			</ul>
		</div>



	</tiles:putAttribute>
</tiles:insertDefinition>