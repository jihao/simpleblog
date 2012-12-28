<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- TODO: change this title value to the entry's title -->
<jsp:include page="_manage_template.jsp">
	<jsp:param name="title" value="Simple Blog Show Post Page"/>
	<jsp:param name="rightpanel" value="myshowpost.jsp"/>
	<jsp:param name="leftpanel" value="leftpanel.jsp"/>
</jsp:include>