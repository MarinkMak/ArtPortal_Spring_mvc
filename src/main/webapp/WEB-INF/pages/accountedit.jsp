<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			
			<div class=formView>

				<h2 align="center">
					<spring:message code="account"/>
				</h2>
			<!-- account menu -->
			<div class="bg_color">
				<hr  color="#5E5974"/>
				<spring:url value="/account/view" var="view" htmlEscape="true"/>
            	<a class="account" href="${view}"><spring:message code="view"/></a>
				<spring:url value="/account/loadAvatar" var="loadAvatar" htmlEscape="true"/>
            	<a class="account" href="${loadAvatar}"><spring:message code="change_avatar"/></a>
				<spring:url value="/account/editData" var="editData" htmlEscape="true"/>
            	<a  class="accountActive" href="${editData}"><spring:message code="edit_data"/></a>
				<spring:url value="/account/changePsw" var="changePsw" htmlEscape="true"/>
            	<a  class="account" href="${changePsw}"><spring:message code="change_psw"/></a>
				<spring:url value="/account/userWorks" var="userWorks" htmlEscape="true"/>
            	<a  class="account" href="${userWorks}"><spring:message code="works"/></a>
				<spring:url value="/account/loadWork" var="loadWork" htmlEscape="true"/>
            	<a  class="account" href="${loadWork}"><spring:message code="load_new_work"/></a>

				<hr  color="#5E5974"/>
			</div>
				
				<sf:form accept-charset = "UTF-8" class="formView"  method="POST" action="submitAccountEdit" modelAttribute="user">
				
				<fieldset class="fieldsetView">
				<table opasity="1" align="center" width="80%" border="0" cellspacing="0"
					cellpadding="4">
					
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="login"/>:</span>
							</td>
						<td width="30%"><span class="textLoginView"><c:out  value="${sessionScope.login}"></c:out></span></td>
						<td align="right" width="30%">
						
							<spring:url value="/account/accountDelete" var="deleteAccount" htmlEscape="true"/>
            				<a  class="account" href="${deleteAccount}">[ <spring:message code="delete_account"/> ]</a>
						
            				
						</td>
					</tr>
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="name"/>:</span></td>
						<td  width="30%"><sf:input path="name" type="text"></sf:input></td>
						<td align="left" width="30%"><div class="errrorView"><sf:errors path="name" cssClass="error" /></div> </td>
					</tr>
					
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="surname"/>:</span></td>
						<td  width="30%"><sf:input path="surname" type="text"></sf:input></td>
						<td align="left" width="30%"><div class="errrorView"><sf:errors path="surname" cssClass="error" /></div></td>
					</tr>
					
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="email"/>:</span></td>
						<td  width="30%"><sf:input path="email" type="text"></sf:input></td>
						<td align="left" width="30%"><div class="errrorView"><sup>*</sup>  <sf:errors path="email" cssClass="error" /></div> </td>
					</tr>
				
					<tr>
						<td></td>
						<td  width="30%">
							<button class="buttonCancel" formaction="cancelEdit"><spring:message code="cancel"/></button>
							<button class="buttonApply" type="submit"><spring:message code="apply"/></button>
						</td>
						<td align="right">
							
						</td>
					</tr>
				</table>
				</fieldset>
			</sf:form>
				
				
				
			
			</div>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>