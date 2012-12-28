<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${user eq null}">
	<jsp:forward page="login.jsp"></jsp:forward>
</c:if>