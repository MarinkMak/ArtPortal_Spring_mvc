<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			
			<form class="formView" name="loginForm" method="POST" action="login">
				<h2 align="center">
					<spring:message code="login_please"/>
				</h2>
				<hr />
				<table  align="center" width="20%" border="0" cellspacing="7" cellpadding="4">
					<tr>
						<td colspan="2" align="right" width="30%">
							<div class="errrorView"><c:out value="${failLoginMsg}"></c:out></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="login"/></span></td>
						<td width="15%"><input path="login" type="text" name="login" value=""></input>
						</td>
						
					</tr>
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="psw"/></span></td>
						<td width="15%"><input path="password" type="password" name="password"	value=""></input>
						</td>
					</tr>
					<tr>
						<td> </td>
						<td align="right" width="15%">
							<button class="buttonOk"  type="submit">
								<spring:message code="go_in"/>
							</button>
						</td>
					</tr>
				</table>
			</form>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>