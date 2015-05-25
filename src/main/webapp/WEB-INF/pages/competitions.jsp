<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<div class=formView>
	
				<h2 align="center">
					<spring:message code="competitions"/>
				</h2>
			
			
			<sf:form  class="formWorksView" >		
			<fieldset class="fieldsetView" >	
				<c:forEach items="${compList}" var="comp">
					
				<table class="tableCompetitionView" border="0">
				<tr>
					<td align="center" colspan="2">
						<span class="textLoginView"><c:out value=" ${comp.name}"></c:out></span>
					</td>
					<td>
						<button class="buttonEdit" name="compId" value="${comp.id}"  formaction="compworks" ><spring:message code="participants"/></button>
					</td>
			
				</tr>
				<tr> 
					<td align="center"> 
						<span class="textView"><spring:message code="main_present"/>:</span>
					</td> 
					<td> 
						<h4><c:out value=" ${comp.presents.get(0).name}"></c:out></h4>
					</td> 
					
					<td class="fright" rowspan="3"> 
							<img class="present" src="${pageContext.request.contextPath}/images/Presents/${comp.presents.get(0).photoPath}" title ="${work.name}" alt="${work.name}"/>
					</td> 
				</tr>
				 <tr> 
					<td class="tdView"> 
						<span class="textView"><spring:message code="date_start"/></span>
					</td> 
					<td> 
						<fmt:formatDate value="${comp.startDate}" var="dateString" pattern="dd.MM.yyyy" />
						<span class="textView"><c:out value=" ${dateString}"></c:out></span>
					</td> 
				 </tr>	
				<tr> 
					<td class="tdView"> 
						<span class="textView"><spring:message code="deadline"/></span>
					</td> 
					<td>
						<fmt:formatDate value="${comp.loadDeadline}" var="dateString" pattern="dd.MM.yyyy" />
						<span class="textView"><c:out value=" ${dateString}"></c:out></span>
					</td> 
				 </tr>	
				
				</table>
					
				</c:forEach>
				
			</fieldset>
			</sf:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>