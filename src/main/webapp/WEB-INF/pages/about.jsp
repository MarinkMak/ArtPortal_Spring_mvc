<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
 
 
 
        <div class="body">
            <h2><spring:message code="please_login"/></h2>
 			 <h2><small>
           <spring:message code="also_you_need"/></small>
            </h2>
           
        </div>
 
 
 
    </tiles:putAttribute>
</tiles:insertDefinition>