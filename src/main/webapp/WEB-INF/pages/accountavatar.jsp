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
            	<a class="accountActive" href="${loadAvatar}"><spring:message code="change_avatar"/></a>
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
				<br />
				<sf:form  class="formPswView" method="POST" action="submitChangeAvatar"  enctype="multipart/form-data"> 
					<fieldset class="fieldsetView" name="userAvatar">
					<table opasity="1" align="center" width="60%" border="0" cellspacing="4"	cellpadding="4">
					
						<tr >
							<td></td>
							<td><div class="errrorView"><c:out value="${loadMsg}"/></div></td>
						</tr>
						<tr >
							<td><span class="textView"><spring:message code="load_new_avatar"/>:</span></td>
							<td>
								<img id="uploadPreview" style="width: 100px; height: 100px;" />
								<input id="uploadImage" name="image" type="file" onchange="PreviewImage();"/>
								<script type="text/javascript">
 								 function PreviewImage() {
   									 var oFReader = new FileReader();
   									 oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);
   										 oFReader.onload = function(oFREvent) {
   										   document.getElementById("uploadPreview").src = oFREvent.target.result;
									     };
									  };
								</script>
							
							</td>
						</tr>
						
						<tr><td></td><td></td></tr>	
						
						<tr>
							<td></td>
							<td>
								<button class="buttonCancel" formaction="cancelEdit" ><spring:message code="cancel"/></button>
								<button class="buttonApply" type="submit"><spring:message code="apply"/></button>
							</td>
							
						</tr>
						
					</table>
					</fieldset>
				</sf:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>