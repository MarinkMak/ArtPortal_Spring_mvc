<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Art Portal</title>
      	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" /> 
     	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/lightbox.min.js"></script>
     	<link href="${pageContext.request.contextPath}/resources/css/lightbox.css" rel="stylesheet" />
     	
    	
		
</head>
<body>
    <div class="page">
        <tiles:insertAttribute name="header" />
        <div class="content">
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="body" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>

