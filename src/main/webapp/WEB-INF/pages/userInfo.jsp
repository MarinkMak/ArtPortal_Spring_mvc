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
					<c:out value="${userInfo.login}" default="User Information"></c:out>
				</h2>
			
				<hr  color="#5E5974"/>
			
			<sf:form  class="formAccountView" >	
				
				<table class="tableUserInfoView" cellpadding="">
					<tr>
						<td rowspan="3">
							<span class="fleft">
								<img class="avatar" src="${pageContext.request.contextPath}/images/Avatars/${userInfo.photoPath}" title ="avatar" alt="avatar"/>
							</span>	
						</td>
						<td align="right" width="15%"><span class="textView"><c:out value="${name}" default="Name:"></c:out></span></td>
						<td  class="fleft" width="15%"><big><c:out  value="${userInfo.name}"></c:out>
							<c:if test="${empty userInfo.name}"> <big><c:out  value="_____" ></c:out> </c:if>
						</td>
					</tr>
					
					<tr>
						<td align="right" width="15%"><span class="textView"><c:out value="${surname}" default="Surname:"></c:out></span></td>
						<td class="fleft" width="15%"><big><c:out  value="${userInfo.surname}"></c:out>
							<c:if test="${empty userInfo.surname}"> <big><c:out  value="_____" ></c:out> </c:if>
						</td>
					</tr>
					
					<tr>
						<td align="right" width="15%"><span class="textView"><c:out value="${email}"	default="Email:"></c:out></span></td>
						<td class="fleft" width="15%"><big><c:out  value="${userInfo.email}"></c:out></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><span class="textView"><c:out value="Works:" ></c:out></span></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			<fieldset class="fieldsetUIWorksView" >	
			<div class="wd">		
				<c:forEach items="${userInfo.works}" var="work">
				<table class="tableUIWorksView">
				 <!-- <table class="tableWorksView">-->
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
			</div>
			</fieldset>
			</sf:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>