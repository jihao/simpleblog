<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col2">
		<!-- Column 2 start -->
		<h3>
			<c:if test="${user.nickName != null}">
				Welcome, ${user.nickName}
			</c:if>
		</h3>
		<h3></h3>
		<p></p>
		
		<p></p>
		<h3>Tag Cloud</h3>
		<jsp:include page="tagCloud.jsp"></jsp:include>
		<!-- Column 2 end -->
</div>