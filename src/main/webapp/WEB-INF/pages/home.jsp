<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
 
 
 
        <div class="body">
        	<span class="fleft">
				<img  src="${pageContext.request.contextPath}/resources/css/images/photoimg.png" title ="png" alt="png"/>
			</span>		
        
        
            <h2><spring:message code="welcome"/> Art<i> Portal</i> -<br /><br />- <spring:message code="portal_for"/></h2>
 			<br />
 			<span class="fright">
				<img  src="${pageContext.request.contextPath}/resources/css/images/paintimg.png" title ="png" alt="png"/>
			</span>
 			
           <br />
            <h2><small>
            <spring:message code="here_you_can"/> 
            <br /><spring:message code="find_new"/>
            <br /> <spring:message code="participate_in"/>
            </small>
            </h2>
        </div>
 
 
 
    </tiles:putAttribute>
</tiles:insertDefinition>