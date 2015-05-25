 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<h2><spring:message code="contact_us"/></h2>
			
			<span class="tableAdminView">
				<h2><small>(066)245-45-45</small></h2>
				<h2><small>ArtPortal@gmail.com</small></h2>
				<h2><small><spring:message code="address"/></small></h2>
				<iframe src="https://www.google.com/maps/embed?pb=!1m29!1m12!1m3!1d10165.165651819334!2d30.483177451098086!3d50.43567243751525!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m14!1i0!3e2!4m5!1s0x40d4ce8d4c3ce43b%3A0x9547f5e50a6f2f4!2z0JLQvtC60LfQsNC70YzQvdCw0Y8sINCa0LjQtdCyLCDQs9C-0YDQvtC0INCa0LjQtdCy!3m2!1d50.44139!2d30.489041!4m5!1s0x40d4cee9e88c9d2f%3A0xd0f3c8358d5dcf16!2z0LLRg9C7LiDQmtGD0LTRgNGP0YjQvtCy0LAsIDE4LCDQmtC40ZfQsg!3m2!1d50.430516999999995!2d30.4872445!5e0!3m2!1sru!2sua!4v1431414764587"
					 width="600" height="450" frameborder="0" style="border:0"></iframe>
			</span>
			
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>