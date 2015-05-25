<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
		
	<h3><spring:message code="find_user"/>:</h3>
<sf:form  class="formWorksView" method="POST" action="findUser" >	

	<span class="textCommentTimeView"><spring:message code="input_letters"/></span>
	<input type="text" name="userLogin"/>
	<button class="buttonFind" type="submit" ><spring:message code="find"/></button>
		
		
<br />		
<br />		
<c:if test="${not empty users}">	
	<table class="tableAdminView" border="1"  cellpadding="5" width="50%">
		<tr>
			<th>№</th>
			<th><spring:message code="login"/></th>
			<th><spring:message code="name"/></th>
			<th><spring:message code="surname"/></th>
			<th><spring:message code="email"/></th>
			<th><spring:message code="comment_able"/></th>
			<th><spring:message code="active"/></th>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="status">
			
			<tr>
				<td>${status.index+1}</td>
				<td>
					<spring:url value="/userInfo/${user.login}" var="userInfo" htmlEscape="true"/>
            		<a  href="${userInfo}">
						<span class="textWorkNameView"><c:out value=" ${user.login}"></c:out></span>
					</a>
				</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.email}</td>
				<td>
					<c:if test="${user.login!=sessionScope.user.login}">
						<c:if test="${user.commentAble}"><span class="green"> <spring:message code="yes"/></span>
							<button class="buttonEdit" name="userId" value="${user.id}"  formaction="editFoundUserCommentAble" ><spring:message code="change"/></button>
						</c:if>
						<c:if test="${not user.commentAble}"> <span class="red"><spring:message code="no"/></span>
							<button class="buttonEdit2" name="userId" value="${user.id}"  formaction="editFoundUserCommentAble" ><spring:message code="change"/></button>
						</c:if>
					</c:if>
					<c:if test="${user.login==sessionScope.user.login}">
						<c:if test="${user.commentAble}"><span class="green"> <spring:message code="yes"/></span>
						</c:if>
						<c:if test="${not user.commentAble}"> <span class="red"><spring:message code="no"/></span>
						</c:if>
					</c:if>
				</td>
				<td>
					<c:if test="${user.login!=sessionScope.user.login}">
						<c:if test="${user.active}"><span class="green"> <spring:message code="yes"/></span>
							<button class="buttonEdit" name="userId" value="${user.id}" formaction="editFoundUserActive"><spring:message code="change"/></button>
						</c:if>
						<c:if test="${not user.active}"> <span class="red"><spring:message code="no"/></span>
							<button class="buttonEdit2" name="userId" value="${user.id}" formaction="editFoundUserActive"><spring:message code="change"/></button>
						</c:if>
					</c:if>
					<c:if test="${user.login==sessionScope.user.login}">
						<c:if test="${user.active}"><span class="green"> <spring:message code="yes"/></span>
						</c:if>
						<c:if test="${not user.active}"> <span class="red"><spring:message code="no"/></span>
						</c:if>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
	<br />
<c:if test="${empty users}">
	<span class="textView"><spring:message code="no_user_found"/></span>
</c:if>	
</sf:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>