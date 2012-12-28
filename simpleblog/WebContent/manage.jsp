<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="logincontrol.jsp"></jsp:include>
<jsp:include page="_manage_template.jsp">
	<jsp:param name="title" value="Simple Blog Management Page"/>
	<jsp:param name="rightpanel" value="listposts.jsp"/>
	<jsp:param name="leftpanel" value="manageleftpanel.jsp"/>
</jsp:include>