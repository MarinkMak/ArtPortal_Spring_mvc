 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			
			<sf:form class="formView"  method="POST" action="submitregister" modelAttribute="user">
				<h2 align="center">
					<spring:message code="register_please"/>
				</h2>
				<hr />
				<fieldset class="fieldsetView">
				<table class="formView" opasity="1" align="right" width="80%" border="0" cellspacing="7"
					cellpadding="4">
					
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="name"/>:</span></td>
						<td width="15%"><sf:input path="name" type="text"></sf:input></td>
						<td align="left" width="30%"><div class="errrorView"><sf:errors  path="name" cssClass="error" /></div> </td>
					</tr>
					
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="surname"/>:</span></td>
						<td width="15%"><sf:input path="surname" type="text"></sf:input></td>
						<td align="left" width="30%"><div class="errrorView"><sf:errors path="surname" cssClass="error" /></div></td>
					</tr>
					
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="email"/>:</span></td>
						<td width="15%"><sf:input path="email" type="text"></sf:input></td>
						<td align="left" width="30%"><div class="errrorView"><sup>* </sup> <sf:errors path="email" cssClass="error" /></div> </td>
					</tr>
					
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="login"/>:</span></td>
						<td width="15%"><sf:input path="login" type="text" ></sf:input></td>
						<td align="left" width="30%"> <div class="errrorView"><sup>* </sup>  <c:out value="${checkLoginUniqueMsg}"/>
																<sf:errors path="login" cssClass="error" /></div> </td>
					</tr>
					
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="psw"/>:</span></td>
						<td width="15%"><sf:input path="password" type="password" ></sf:input></td>
						<td align="left" width="70%"><div class="errrorView"><sup>*  </sup> <sf:errors path="password" cssClass="error" />    </div></td>
					</tr>
			
					<tr>
						<td align="right" width="15%">
							<span class="textView"><spring:message code="confirm_psw"/>:</span></td>
						<td width="15%"><sf:input path="confirmPassword" type="password" name="confirmPassword"></sf:input></td>
						<td align="left" width="30%"> <div class="errrorView"><sup>* </sup> <c:out value="${confirmPswMsg}"/></div></td>
					</tr>
					
					<tr>
						<td></td>
						<td align="left" width="15%">
							
							<button class="buttonCancel" formaction="test" ><spring:message code="cancel"/></button>
							<button class="buttonApply" type="submit"><spring:message code="apply"/></button>
						</td>
						<td></td>
					</tr>
				</table>
				</fieldset>
			</sf:form>


		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>