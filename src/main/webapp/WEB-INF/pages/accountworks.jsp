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
            	<a  class="account" href="${editData}"><spring:message code="edit_data"/></a>
				<spring:url value="/account/changePsw" var="changePsw" htmlEscape="true"/>
            	<a  class="account" href="${changePsw}"><spring:message code="change_psw"/></a>
				<spring:url value="/account/userWorks" var="userWorks" htmlEscape="true"/>
            	<a  class="accountActive" href="${userWorks}"><spring:message code="works"/></a>
				<spring:url value="/account/loadWork" var="loadWork" htmlEscape="true"/>
            	<a  class="account" href="${loadWork}"><spring:message code="load_new_work"/></a>
				<hr  color="#5E5974"/>
			</div>
				
	<!--  -->	<h4 align="center"><c:out value="${workLoadMsg}" ></c:out></h4>
				<c:if test="${empty user.works}"> 
					<br />
					<h4 align="center"><spring:message code="no_loaded_works"/></h4>
				</c:if>
			
			<sf:form  class="formWorksView" >		
			<fieldset class="fieldsetView" >	
				<c:forEach items="${user.works}" var="work">
					
				<table class="tableWorksView" >
				<tr>
					<td rowspan="5">
						<a href="${pageContext.request.contextPath}/images/Works/${work.path}" data-lightbox="demo" data-title="${work.name}">
							<img class="artwork" src="${pageContext.request.contextPath}/images/Works/${work.path}" title ="${work.name}" alt="${work.name}"/>
						</a>
					</td>
					<td class="fleft">
						<spring:url value="/workInfo/${work.name}" var="workInfo" htmlEscape="true"/>
            			<a  href="${workInfo}">
							<span class="textWorkNameView"><c:out value="${work.name}"></c:out></span>
						</a>
					</td>
				</tr>
				<tr> <td class="fleft"> <span class="textWorkTypeView"><c:out value="${work.type.name}"></c:out></span></td> </tr>
				<tr> <td class="fleft"> <span class="textCompetitionView"><c:out value="${work.competition.name}"></c:out></span>
					<c:if test="${empty work.competition}"> 
						<small><span class="textCompetitionView"><c:out  value="no competition" ></c:out> </span>
					</c:if></td> </tr>
				<tr> <td class="fleft"> 	
					<spring:url value="/workInfo/${work.name}" var="workInfo" htmlEscape="true"/>
            		<a  href="${workInfo}">				
						<span class="textView"><c:out value="${work.comments.size()}"></c:out></span>
						<img class="icon" src="${pageContext.request.contextPath}/resources/css/images/comm.png" title ="icon" alt="icon"/>
					</a>
				</td> </tr>	
				<tr> <td class="fleft"> 
					<c:if test="${not empty work.competition}"> 
						<spring:url value="/workInfo/${work.name}" var="workInfo" htmlEscape="true"/>
            			<a  href="${workInfo}">		
							<span class="textView"><c:out value="${work.voices.size()}"></c:out></span>
							<img class="icon" src="${pageContext.request.contextPath}/resources/css/images/star.png" title ="icon" alt="icon"/>
						</a>
					</c:if>
				</td> </tr>	
				</table>
					
				</c:forEach>
				
			</fieldset>
			</sf:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>