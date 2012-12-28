<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="_manage_template.jsp">
	<jsp:param name="title" value="Simple Blog About Page"/>
	<jsp:param name="rightpanel" value="about_rightpanel.jsp"/>
	<jsp:param name="leftpanel" value="leftpanel.jsp"/>
</jsp:include>