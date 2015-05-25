<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
            	<a  class="account" href="${userWorks}"><spring:message code="works"/></a>
				<spring:url value="/account/loadWork" var="loadWork" htmlEscape="true"/>
            	<a  class="accountActive" href="${loadWork}"><spring:message code="load_new_work"/></a>

				<hr  color="#5E5974"/>
			</div>

				<sf:form  accept-charset = "UTF-8" class="formPswView" method="POST" action="submitLoadWork"  enctype="multipart/form-data"> 
					<fieldset class="fieldsetView">
					<table opasity="1" align="center" width="100%" border="0" cellspacing="4"	cellpadding="4">
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="works_name"/>:</span></td>
						<td  width="30%"><input name="workName" type="text" value="${work.name}"></input></td>
						<td align="left" width="30%"><div class="errrorView"><c:out value="${errNameMsg}"></c:out></div> </td>
					</tr>
					<tr><td></td><td></td><td></td></tr>	
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="type_of"/>:</span></td>
						<td  width="30%">
								<c:forEach items="${artTypes}" var="type">
									<c:if test="${type.name==work.type.name}"> 
										<span class="textWorkTypeView"><p><input name="workTypeName" type="radio" checked value="${type.name}"> ${type.name}</p></span>
									</c:if>
									<c:if test="${type.name!=work.type.name}"> 
										<span class="textWorkTypeView"><p><input name="workTypeName" type="radio" value="${type.name}"> ${type.name}</p></span>
   									</c:if>
   								</c:forEach>
						</td>
						<td align="left" width="30%"><div class="errrorView"><c:out value="${errTypeMsg}"></c:out></div> </td>
					</tr>
					<tr><td></td><td></td><td></td></tr>	
					<tr><td></td><td></td><td></td></tr>	
					<tr>
						<td align="right" width="30%">
							<span class="textView"><spring:message code="join_comp"/>:</span></td>
						<td  width="30%">
							<select  name="competitionName">
								<option value=""><spring:message code="not_now"/></option>
								<c:forEach items="${competitions}" var="competition">
									<c:if test="${competition.name==work.competition.name}"> 
   								 		<option selected value="${competition.name}">${competition.name}</option>
   								 	</c:if>
									<c:if test="${competition.name!=work.competition.name}"> 
   								 		<option value="${competition.name}">${competition.name}</option>
   								 	</c:if>
   								</c:forEach>
  							 </select>
						</td>
						<td align="left" width="30%"> </td>
					</tr>
					<tr><td></td><td></td><td></td></tr>	
					<tr><td></td><td></td><td></td></tr>		
					<tr >
						<td></td>
						<td><div class="errrorView"><c:out value="${loadMsg}"/></div></td>
						<td></td>
					</tr>
					<tr >
						<td align="right"><span class="textView"><spring:message code="load_new_work"/>:</span></td>
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
						<td></td>
					</tr>
					<tr><td></td><td></td><td></td></tr>	
					<tr>
						<td></td>
						<td>
							<button class="buttonCancel" formaction="cancelEdit" ><spring:message code="cancel"/></button>
							<button class="buttonApply" type="submit"><spring:message code="apply"/></button>
						</td>
						<td></td>
					</tr>
					</table>
					</fieldset>
				</sf:form>

			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>