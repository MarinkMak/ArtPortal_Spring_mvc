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
					<c:out value="${comp.name}"></c:out>
				</h2>
			
			
 		<h4 align="center">
				<spring:url value="/firstcw" var="firstUrl" htmlEscape="true"/>
            	<a class="pagin" href="${firstUrl}">First ...</a>
            	
			<c:if test="${pageNumber!=1}"> 
				<spring:url value="/prevcw" var="prevUrl" htmlEscape="true"/>
            	<a class="pagin" href="${prevUrl}">Prev</a>
            </c:if>	
			<c:if test="${pageNumber==1}"> 
            	<span class="pagin_label"><c:out  value="Prev"></c:out></span>
            </c:if>	
						  Page ${pageNumber} of ${pageCount} 
			<c:if test="${pageNumber!=pageCount}"> 			  
				<spring:url value="/nextcw" var="nextUrl" htmlEscape="true"/>
            	<a class="pagin" href="${nextUrl}">Next</a>
			</c:if>
			<c:if test="${pageNumber==pageCount}"> 
            	<span class="pagin_label"><c:out  value="Next"></c:out></span>
            </c:if>	
				<spring:url value="/lastcw" var="lastUrl" htmlEscape="true"/>
            	<a class="pagin" href="${lastUrl}">... Last</a>
		</h4>
 			
			<sf:form  class="formWorksView" >		
			<fieldset class="fieldsetView" >	
				<c:forEach items="${artWorks}" var="work">
					
				<table class="tableWorksView" border="0">
				<tr>
					<td class="fright" rowspan="5">
						<a href="${pageContext.request.contextPath}/images/Works/${work.path}" data-lightbox="demo" data-title="${work.name}">
							<img class="artwork" src="${pageContext.request.contextPath}/images/Works/${work.path}" title ="${work.name}" alt="${work.name}"/>
						</a>
					</td>
					<td class="tdView">
						<spring:url value="/workInfo/${work.name}" var="workInfo" htmlEscape="true"/>
            			<a  href="${workInfo}">
							<span class="textWorkNameView"><c:out value="${work.name}"></c:out></span>
						</a>
					</td>
				</tr>
				<tr> 
					<td class="tdView"> 
					
						<spring:url value="/userInfo/${work.user.login}" var="workInfo" htmlEscape="true"/>
            			<a  href="${workInfo}">
							<span class="textWorkTypeView"><spring:message code="author"/></span>
							<span class="textView"><c:out value=" ${work.user.login}"></c:out></span>
						</a>
					</td> 
				</tr>
				<tr> <td class="tdView"> <span class="textCompetitionView"><c:out value="${work.competition.name}"></c:out></span>
					<c:if test="${empty work.competition}"> 
						<small><span class="textCompetitionView"><c:out  value="no competition" ></c:out> </span>
					</c:if></td> </tr>
				<tr> <td class="tdView"> 	
					<spring:url value="/workInfo/${work.name}" var="workInfo" htmlEscape="true"/>
            		<a  href="${workInfo}">	
						<span class="textView"><c:out value="${work.comments.size()}"></c:out></span>
						<img class="icon" src="${pageContext.request.contextPath}/resources/css/images/comm.png" title ="comments" alt="comments"/>
					</a>
				</td> </tr>	
				<tr> <td class="tdView"> 
					<c:if test="${not empty work.competition}"> 
						<spring:url value="/workInfo/${work.name}" var="workInfo" htmlEscape="true"/>
            			<a  href="${workInfo}">			
							<span class="textView"><c:out value="${work.voices.size()}"></c:out></span>
							<img class="icon" src="${pageContext.request.contextPath}/resources/css/images/star.png" title ="likes" alt="likes"/>
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