<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title><c:out value="${param.title}"></c:out></title>
	<meta content="application/xhtml+xml; charset=utf-8" http-equiv="Content-Type"/>
	<meta content="The Perfect 2 Column Liquid Layout (left menu): No CSS hacks. SEO friendly. iPhone compatible." name="description"/>
	<meta content="The Perfect 2 Column Liquid Layout (left menu): No CSS hacks. SEO friendly. iPhone compatible." name="keywords"/>
	<meta content="index, follow" name="robots"/>
	<link type="image/x-icon" href="image/simpleblog.png" rel="shortcut icon"/>
	<link rel=StyleSheet type="text/css" href="style/homestyle.css">
	<link rel=StyleSheet type="text/css" href="style/comments.css">
	<link rel=StyleSheet type="text/css" href="style/messageboxcss/messageBoxes.css">
	
	<script type="text/javascript" src="js/bsn.AutoSuggest_2.1.3.js" charset="UTF-8"></script>
	<link rel="stylesheet" href="style/autosuggestcss/autosuggest_inquisitor.css" type="text/css" media="screen" charset="UTF-8" />
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="colmask leftmenu">
	<div class="colleft">
		<!-- using template -->
		<jsp:include page="${param.rightpanel}"></jsp:include>
		<jsp:include page="${param.leftpanel}"></jsp:include>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>