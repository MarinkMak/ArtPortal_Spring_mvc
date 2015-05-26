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
            	<a class="accountActive" href="${view}"><spring:message code="view"/></a>
				<spring:url value="/account/loadAvatar" var="loadAvatar" htmlEscape="true"/>
            	<a class="account" href="${loadAvatar}"><spring:message code="change_avatar"/></a>
				<spring:url value="/account/editData" var="editData" htmlEscape="true"/>
            	<a  class="account" href="${editData}"><spring:message code="edit_data"/></a>
				<spring:url value="/account/changePsw" var="changePsw" htmlEscape="true"/>
            	<a  class="account" href="${changePsw}"><spring:message code="change_psw"/></a>
				<spring:url value="/account/userWorks" var="userWorks" htmlEscape="true"/>
            	<a  class="account" href="${userWorks}"><spring:message code="works"/></a>
				<spring:url value="/account/loadWork" var="loadWork" htmlEscape="true"/>
            	<a  class="account" href="${loadWork}"><spring:message code="load_new_work"/></a>
				
				<hr  color="#5E5974"/>
			</div>
				<h4 align="center">
				<c:if test="${accountDataEditMsg}">
					<spring:message code="data_changed"/>
				</c:if>
				<c:if test="${accountPswEditMsg}">
					<spring:message code="psw_changed"/>
				</c:if>
				<c:if test="${accountAvatarLoadedMsg}">
					<spring:message code="avatar_loaded"/>
				</c:if>
				
				
				</h4>
			
			<sf:form  class="formAccountView" >	
			<span class="fleft">
				
				<img class="avatar" src="${pageContext.request.contextPath}/images/Avatars/${user.photoPath}" title ="avatar" alt="avatar"/>
		<!--  		<img src="${pageContext.request.contextPath}/resources/avatars/noavatar.jpg" title ="no avatar" alt="no avatar"/>
		 		<img alt="" src="<spring:url value="/images/Avatars/oleg2.jpg" htmlEscape="true"/>"/>
		-->		
			</span>		
			
			<fieldset class="fieldsetView">
			
				<table class="tableAccountView" cellpadding="">
					<tr>
						<td align="right" width="15%"><span class="textView"><spring:message code="login"/></span></td>
						<td class="fleft" width="15%"><span class="textLoginView"><c:out  value="${sessionScope.user.login}"></c:out></span></td>
					</tr>
					
					<tr>
						<td align="right" width="15%"><span class="textView"><spring:message code="name"/></span></td>
						<td  class="fleft" width="15%"><big><c:out  value="${user.name}"></c:out>
							<c:if test="${empty user.name}"> <big><c:out  value="_____" ></c:out> </c:if>
						</td>
					</tr>
					
					<tr>
						<td align="right" width="15%"><span class="textView"><spring:message code="surname"/></span></td>
						<td class="fleft" width="15%"><big><c:out  value="${user.surname}"></c:out>
							<c:if test="${empty user.surname}"> <big><c:out  value="_____" ></c:out> </c:if>
						</td>
					</tr>
					
					<tr>
						<td align="right" width="15%"><span class="textView"><spring:message code="email"/></span></td>
						<td class="fleft" width="15%"><big><c:out  value="${user.email}"></c:out></td>
					</tr>
				</table>
			</fieldset>
					
				
			</sf:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>