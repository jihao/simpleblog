<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
function gotoSelectedPage()
{
	var x = document.getElementById("navigatorForm");
	//alert("Original action: " + x.action)
	x.submit();
}
</script>
<form action="Posts" method="get" id="navigatorForm">
	<input name="method" type="hidden" value="list">
	<a href="Posts?method=list&pageNumber=1">First</a> 
	<c:if test="${pageNumber>1}">
		<a href="Posts?method=list&pageNumber=${pageNumber-1}">Prev</a>
	</c:if> 
	Go To Page <select name="pageNumber" onchange="gotoSelectedPage();">
	<c:forEach begin="1" end="${totalPages}" step="1" var="pageIndex">
		<c:choose>
			<c:when test="${pageIndex eq pageNumber}">
				<option value="${pageIndex}" selected="selected">${pageIndex}</option>
			</c:when>
			<c:otherwise>
				<option value="${pageIndex}">${pageIndex}</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</select> 
	<c:if test="${pageNumber<totalPages}">
		<a href="Posts?method=list&pageNumber=${pageNumber+1}">Next</a>
	</c:if> 
	<a href="Posts?method=list&pageNumber=${totalPages}">Last</a>
</form>

